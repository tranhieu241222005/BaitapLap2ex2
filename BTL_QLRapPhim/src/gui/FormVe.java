package gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.*;
import dao.Ghe_DAO;
import dao.Phim_DAO;
import dao.Phong_DAO;
import dao.SuatChieu_DAO;
import dao.Ve_DAO;
import entity.Ghe;
import entity.Phim;
import entity.Phong;
import entity.SuatChieu;
import entity.VeXemPhim;

public class FormVe extends JPanel {

    // Các thành phần giao diện
    private JLabel lblTenPhong, lblThoiLuong, lblTheLoai, lblGheDaChon, lblTenPhim, lblStart;
    private JComboBox<String> cboPhim, cboPhong;
    private JComboBox<SuatChieu> cboSuatChieu;
    private JButton btnChonGhe, btnXacNhan;
    private Ghe_DAO gheDao;
    private Phim_DAO phimDao;
    private Phong_DAO phongDao;
    private Ve_DAO veDAO;
    private SuatChieu_DAO suatChieuDao;
    private SuatChieu suatChieu;
    
    private List<Ghe> dsGheDangChon = new ArrayList<>();
    private List<Ghe> dsGhe = new ArrayList<>();
    private List<Phim> dsPhim = new ArrayList<Phim>();
    private List<Phong> dsPhong = new ArrayList<Phong>();
    private List<SuatChieu> dsSuatChieu = new ArrayList<SuatChieu>();
    private DefaultComboBoxModel<String> modelPhim, modelPhong, modelSuatChieu;
    
    // Màu sắc
    private static final Color MAU_DO = new Color(200, 40, 40);
    private static final Color MAU_NEN = new Color(30, 30, 30);
    private static final Color MAU_PANEL = new Color(50, 50, 50 );

    public FormVe() {
        gheDao = new Ghe_DAO();
        phimDao = new Phim_DAO();
        phongDao = new Phong_DAO();
        suatChieuDao = new SuatChieu_DAO();
        veDAO = new Ve_DAO();
        setPreferredSize(new Dimension(1000, 650));
        setLayout(new BorderLayout(15, 15));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // PHẦN TRÊN - CHỌN PHIM VÀ SUẤT CHIẾU
        JPanel pnlChonPhim = new JPanel(new GridBagLayout());
        pnlChonPhim.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(Color.GRAY), "CHỌN PHIM VÀ SUẤT CHIẾU"), 
            new EmptyBorder(10, 15, 15, 15)));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Hàng 1 - Chọn phim
        gbc.gridx = 0; gbc.gridy = 0;
        pnlChonPhim.add(new JLabel("Phim:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1;
        cboPhim = new JComboBox<>();
        dsPhim = phimDao.getAllPhim();
        modelPhim = new DefaultComboBoxModel<>();
        for (Phim phim : dsPhim) {
            modelPhim.addElement(phim.getTenPhim());
        }
        cboPhim.setModel(modelPhim);
        cboPhim.insertItemAt("-- Chọn phim --", 0);
        cboPhim.setSelectedIndex(0);
        pnlChonPhim.add(cboPhim, gbc);
        
        // Hàng 2 - Chọn phòng
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        pnlChonPhim.add(new JLabel("Phòng:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        cboPhong = new JComboBox<>();
        dsPhong = phongDao.getAllPhong();
        modelPhong = new DefaultComboBoxModel<>();
        for (Phong phong : dsPhong) {
            modelPhong.addElement(phong.getTenPhong());
        }
        cboPhong.setModel(modelPhong);
        cboPhong.insertItemAt("-- Chọn Phòng --", 0);
        cboPhong.setSelectedIndex(0);
        cboPhong.setEnabled(false);
        pnlChonPhim.add(cboPhong, gbc);
        
        // Hàng 3 - Chọn suất chiếu
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        pnlChonPhim.add(new JLabel("Suất chiếu:"), gbc);
        
        gbc.gridx = 1; gbc.weightx = 1;
        cboSuatChieu = new JComboBox<SuatChieu>();
        cboSuatChieu.setEnabled(false);
        pnlChonPhim.add(cboSuatChieu, gbc);
        
        // Nút chọn ghế
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnChonGhe = new JButton("CHỌN GHẾ");
        styleButton(btnChonGhe);
        btnChonGhe.setEnabled(false);
        btnChonGhe.setPreferredSize(new Dimension(120, 40));
        pnlChonPhim.add(btnChonGhe, gbc);

        // PHẦN GIỮA - THÔNG TIN PHÒNG
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new BoxLayout(pnlThongTin, BoxLayout.Y_AXIS));
        pnlThongTin.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(Color.GRAY), "THÔNG TIN VÉ"), 
            new EmptyBorder(15, 15, 15, 15)));

        lblTenPhim = new JLabel("Tên phim: ");
        lblTenPhong = new JLabel("Tên phòng: ");
        lblThoiLuong = new JLabel("Thời lượng: ");
        lblTheLoai = new JLabel("Thể loại: ");
        lblStart = new JLabel("Thời gian bắt đầu: ");
        lblGheDaChon = new JLabel("Ghế đã chọn: Chưa chọn ghế");

        // Căn chỉnh font và khoảng cách
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 14);
        Font fontValue = new Font("Segoe UI", Font.PLAIN, 14);
        
        lblTenPhim.setFont(fontLabel);
        lblTenPhong.setFont(fontLabel);
        lblThoiLuong.setFont(fontLabel);
        lblTheLoai.setFont(fontLabel);
        lblStart.setFont(fontLabel);
        lblGheDaChon.setFont(fontValue);

        pnlThongTin.add(lblTenPhim);
        pnlThongTin.add(Box.createVerticalStrut(12));
        pnlThongTin.add(lblTenPhong);
        pnlThongTin.add(Box.createVerticalStrut(12));
        pnlThongTin.add(lblThoiLuong);
        pnlThongTin.add(Box.createVerticalStrut(12));
        pnlThongTin.add(lblTheLoai);
        pnlThongTin.add(Box.createVerticalStrut(12));
        pnlThongTin.add(lblStart);
        pnlThongTin.add(Box.createVerticalStrut(12));
        pnlThongTin.add(lblGheDaChon);

        // PHẦN DƯỚI - NÚT XÁC NHẬN
        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        btnXacNhan = new JButton("XÁC NHẬN ĐẶT VÉ");
        styleButton(btnXacNhan);
        btnXacNhan.setPreferredSize(new Dimension(180, 45));
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 14));
        pnlNut.add(btnXacNhan);

        // THÊM CÁC PANEL VÀO FORM CHÍNH
        add(pnlChonPhim, BorderLayout.NORTH);
        add(pnlThongTin, BorderLayout.CENTER);
        add(pnlNut, BorderLayout.SOUTH);

        // XỬ LÝ SỰ KIỆN
        cboPhim.addActionListener(e -> {
            if (cboPhim.getSelectedIndex() > 0) {
                cboPhong.setEnabled(true);
            } else {
                cboPhong.setEnabled(false);
                cboSuatChieu.setEnabled(false);
                btnChonGhe.setEnabled(false);
                cboPhong.setSelectedIndex(0);
                cboSuatChieu.setSelectedIndex(0);
            }
            dsGheDangChon.clear();
            capNhatGheDaChon();
        });
        
        cboPhong.addActionListener(e -> {
            if (cboPhong.getSelectedIndex() > 0 && cboPhim.getSelectedIndex() > 0) {
                loadSuatChieu();
                cboSuatChieu.setEnabled(true);
            } else {
                cboSuatChieu.setEnabled(false);
                btnChonGhe.setEnabled(false);
                cboSuatChieu.setSelectedIndex(0);
            }
        });
        
        cboSuatChieu.addActionListener(e -> {
            if (cboSuatChieu.getSelectedIndex() > 0) {
                btnChonGhe.setEnabled(true);
                capNhatThongTinPhong((SuatChieu)cboSuatChieu.getSelectedItem());
            } else {
                btnChonGhe.setEnabled(false);
            }
        });
        
        btnChonGhe.addActionListener(e -> moManHinhChonGhe());
        btnXacNhan.addActionListener(e -> xuLyXacNhan());
    }
    
    private void styleButton(JButton button) {
        button.setBackground(MAU_DO);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(MAU_DO.darker(), 1),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    private void loadSuatChieu() {
        cboSuatChieu.removeAllItems();
        
        String tenPhim = cboPhim.getSelectedItem().toString();
        String maPhim = null;
        for (Phim p : dsPhim) {
            if (p.getTenPhim().equals(tenPhim)) {
                maPhim = p.getMaPhim();
                break;
            }
        }

        String tenPhong = cboPhong.getSelectedItem().toString();
        String maPhong = null;
        for (Phong p : dsPhong) {
            if (p.getTenPhong().equals(tenPhong)) {
                maPhong = p.getMaPhong();
                break;
            }
        }

        dsSuatChieu = suatChieuDao.getSuatChieuTheoPhim_Phong(maPhim, maPhong);

        DefaultComboBoxModel<SuatChieu> model = new DefaultComboBoxModel<>();
        model.addElement(null); // Cho dòng "-- Chọn Suất Chiếu --"

        for (SuatChieu s : dsSuatChieu) {
            model.addElement(s);
        }

        cboSuatChieu.setModel(model);
        cboSuatChieu.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof SuatChieu) {
                    SuatChieu s = (SuatChieu) value;
                    setText(s.getThoiGian().toString());
                } else {
                    setText("-- Chọn Suất Chiếu --");
                }
                return this;
            }
        });
    }
    
    private void capNhatThongTinPhong(SuatChieu sc) {

    	String tenPhong = null;
    	for(Phong p: dsPhong) {
    		if(p.getMaPhong().equals(sc.getMaPhong())) {
    			tenPhong = p.getTenPhong();
    		}
    	}
    	
    	String tenPhim = null;
    	int thoiLuong = 0;
    	String theLoai = null;
    	for(Phim p: dsPhim) {
    		if(p.getMaPhim().equals(sc.getMaPhim())) {
    			tenPhim = p.getTenPhim();
    			thoiLuong = p.getThoiLuong();
    			theLoai = p.getTheloai();
    		}
    	}
    	String thoiGianBatDau = null;
    	thoiGianBatDau = sc.getThoiGian().toString();
    	
    	lblTenPhim.setText("Tên phim: " + tenPhim);
        lblTenPhong.setText("Tên phòng: " + tenPhong);
        lblThoiLuong.setText("Thời lượng: " + thoiLuong);
        lblTheLoai.setText("Thể loại: " + theLoai);
        lblStart.setText("Thời gian bắt đầu: " + thoiGianBatDau);
    }
    
    private void moManHinhChonGhe() {
    	
    	SuatChieu sc = (SuatChieu)cboSuatChieu.getSelectedItem();
    	suatChieu = sc;
        String maPhong = suatChieu.getMaPhong();
        JDialog dialog = new JDialog();
        dialog.setTitle("Chọn ghế - " + maPhong);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        JPanel pnlChinh = new JPanel(new BorderLayout(10, 10));
        pnlChinh.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Màn hình
        JPanel pnlManHinh = new JPanel();
        pnlManHinh.setBackground(new Color(20, 20, 20));
        pnlManHinh.setPreferredSize(new Dimension(0, 40));
        pnlManHinh.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.GRAY, 2),
            new EmptyBorder(5, 5, 5, 5)));
        JLabel lblManHinh = new JLabel("MÀN HÌNH", JLabel.CENTER);
        lblManHinh.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblManHinh.setForeground(Color.WHITE);
        pnlManHinh.add(lblManHinh);

        // Các ghế
        JPanel pnlGhe = new JPanel(new GridLayout(0, 10, 8, 8));
        pnlGhe.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Lấy danh sách ghế từ CSDL
        dsGhe = gheDao.getAllGheTheoSuatChieu(suatChieu.getMaSuat());
        
     // Tạo danh sách ghế mặc định
        String[] hang = {"A", "B", "C", "D", "E"};
        int soCot = 10;

        Set<String> gheDaDat = dsGhe.stream()
                .map(Ghe::getMaGhe)
                .collect(Collectors.toSet());

        for (String h : hang) {
            for (int c = 1; c <= soCot; c++) {
                String soCotStr = String.format("%02d", c); // 01, 02, ..., 10
                String maGhe = h + soCotStr;

                JButton btnGhe = new JButton(maGhe);
                btnGhe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                btnGhe.setFocusPainted(false);
                btnGhe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnGhe.setForeground(Color.BLACK);
                btnGhe.setOpaque(true);
                btnGhe.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                Ghe ghe = new Ghe(maGhe, suatChieu.getMaSuat());
                if (gheDaDat.contains(maGhe)) {
                    btnGhe.setBackground(new Color(255, 70, 70)); // Ghế đã đặt
                    btnGhe.setEnabled(false);
                }else if (dsGheDangChon.contains(ghe)){
                	btnGhe.setBackground(new Color(50, 150, 50));
                }
                else {
                    btnGhe.setBackground(new Color(200, 200, 200)); // Ghế trống
                }

                // Tạo đối tượng Ghế giả định (nếu cần lưu vào danh sách ghế đang chọn)
                
                
                btnGhe.addActionListener(e -> xuLyChonGhe(btnGhe, ghe));
                pnlGhe.add(btnGhe);
            }
        }


        // Chú thích
        JPanel pnlChuThich = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        pnlChuThich.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        pnlChuThich.add(taoChuThich("Ghế trống", new Color(200, 200, 200), Color.BLACK));
        pnlChuThich.add(taoChuThich("Ghế đã chọn", new Color(50, 150, 50), Color.BLACK));
        pnlChuThich.add(taoChuThich("Ghế đã đặt", new Color(255, 70, 70), Color.WHITE));

        // Nút bấm
        JPanel pnlNut = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        JButton btnHuy = new JButton("HỦY");
        styleButton(btnHuy);
        btnHuy.addActionListener(e -> dialog.dispose());
        
        JButton btnXacNhanGhe = new JButton("XÁC NHẬN");
        styleButton(btnXacNhanGhe);
        btnXacNhanGhe.addActionListener(e -> {
            capNhatGheDaChon();
            dialog.dispose();
        });
        
        pnlNut.add(btnHuy);
        pnlNut.add(btnXacNhanGhe);

        // Thêm các panel vào dialog
        pnlChinh.add(pnlManHinh, BorderLayout.NORTH);
        pnlChinh.add(pnlGhe, BorderLayout.CENTER);
        
        JPanel pnlDuoi = new JPanel(new BorderLayout());
        pnlDuoi.add(pnlChuThich, BorderLayout.CENTER);
        pnlDuoi.add(pnlNut, BorderLayout.SOUTH);
        
        pnlChinh.add(pnlDuoi, BorderLayout.SOUTH);
        dialog.add(pnlChinh);
        dialog.setVisible(true);
    }
    
    private JLabel taoChuThich(String text, Color mauNen, Color mauChu) {
        JLabel lbl = new JLabel(text, JLabel.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(mauNen.darker(), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        lbl.setBackground(mauNen);
        lbl.setForeground(mauChu);
        lbl.setOpaque(true);
        return lbl;
    }
    
    private void xuLyChonGhe(JButton btnGhe, Ghe ghe) {
        if (dsGheDangChon.contains(ghe)) {
            dsGheDangChon.remove(ghe);
            btnGhe.setBackground(new Color(200, 200, 200));
        } else {
            dsGheDangChon.add(ghe);
            btnGhe.setBackground(new Color(50, 150, 50));
        }
        btnGhe.setForeground(Color.BLACK);
    }
    
    private void capNhatGheDaChon() {
        if (dsGheDangChon.isEmpty()) {
            lblGheDaChon.setText("Ghế đã chọn: Chưa chọn ghế");
        } else {
            List<String> maGhe = new ArrayList<>();
            for (Ghe ghe : dsGheDangChon) {
                maGhe.add(ghe.getMaGhe());
            }
            lblGheDaChon.setText("Ghế đã chọn: " + String.join(", ", maGhe) + 
                " (" + maGhe.size() + " ghế)");
        }
    }
    
    private void xuLyXacNhan() {
    	if (suatChieu == null) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn suất chiếu trước khi đặt vé", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (dsGheDangChon.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn ít nhất một ghế trước khi xác nhận", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int luaChon = JOptionPane.showConfirmDialog(this, 
            "Xác nhận đặt " + dsGheDangChon.size() + " ghế?",
            "Xác nhận đặt vé",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
            
        if (luaChon == JOptionPane.YES_OPTION) {
            int soThanhCong = 0;
            String maPhim = suatChieu.getMaPhim();
            String maSuat = suatChieu.getMaSuat();
            List<VeXemPhim> dsVeDaThem = new ArrayList<>();
            
            try {
                for (Ghe g : dsGheDangChon) {
                    // 1. Thêm ghế trước
                    boolean themGheThanhCong = gheDao.addGhe(g);
                    
                    if (themGheThanhCong) {
                        // 2. Nếu thêm ghế thành công thì thêm vé
                        VeXemPhim ve = new VeXemPhim(maPhim, maSuat, g.getMaGhe());
                        boolean themVeThanhCong = veDAO.themVe(ve);
                        
                        if (themVeThanhCong) {
                            dsVeDaThem.add(ve);
                            soThanhCong++;
                        } else {
                            // Nếu thêm vé thất bại thì xóa ghế vừa thêm
                            gheDao.xoaGhe(g);
                        }
                    }
                }

                if (soThanhCong == dsGheDangChon.size()) {
                    JOptionPane.showMessageDialog(this,
                        "Đặt vé thành công cho " + soThanhCong + " ghế!",
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Đặt vé thành công " + soThanhCong + "/" + dsGheDangChon.size() + " ghế. " +
                        "Một số ghế không thể đặt do lỗi hệ thống.",
                        "Cảnh báo",
                        JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception e) {
                // Rollback tất cả các thao tác đã thực hiện nếu có lỗi
                for (VeXemPhim ve : dsVeDaThem) {
                    try {
                        veDAO.xoaVe(ve.getMaVe());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                
                // Xóa các ghế đã thêm nhưng vé chưa thêm thành công
                for (Ghe g : dsGheDangChon) {
                    try {
                        gheDao.xoaGhe(g);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                
                JOptionPane.showMessageDialog(this,
                    "Có lỗi xảy ra trong quá trình đặt vé. Đã hủy tất cả thao tác.",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            } finally {
                dsGheDangChon.clear();
                capNhatGheDaChon(); // Cập nhật lại giao diện
            }
        }
    }
}