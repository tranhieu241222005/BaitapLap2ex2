package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_DAO;
import dao.Ve_DAO;
import entity.VeXemPhim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FormInVe extends JPanel {
    private JTable tableVe;
    private DefaultTableModel tableModel;
    private JComboBox<String> cboNgay, cboPhim, cboSuatChieu;
    private JLabel lblMaVe, lblMaPhim, lblMaSuatChieu, lblMaGhe, lblNgayDat, lblGiaVe;
    private JButton btnInVe;
    
    private List<VeXemPhim> danhSachVe = new ArrayList<>();
    private Ve_DAO veDao;
    
    public FormInVe() {
        initUI();
        loadSampleData();
        updateTable();
    }
    
    private void initUI() {
    	veDao = new Ve_DAO();
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("QUẢN LÝ VÉ XEM PHIM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        this.add(lblTitle, BorderLayout.NORTH);
        
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new CompoundBorder(
            new SoftBevelBorder(BevelBorder.RAISED),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(createTitledBorder("Danh sách vé"));
        
        tableModel = new DefaultTableModel(new Object[]{"Mã Vé", "Phim", "Suất Chiếu", "Ghế", "Ngày Đặt"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableVe = new JTable(tableModel);
        tableVe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableVe.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableVe.setRowHeight(25);
        tableVe.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableVe.getTableHeader().setBackground(new Color(70, 130, 180));
        tableVe.getTableHeader().setForeground(Color.WHITE);
        tableVe.setSelectionBackground(new Color(173, 216, 230));
        tableVe.setSelectionForeground(Color.BLACK);
        
        tableVe.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateSelectedVeInfo();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableVe);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Menu bên phải 
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuPanel.setPreferredSize(new Dimension(300, getHeight())); // Kích thước cố định
        
        // Phần lọc
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBorder(createTitledBorder("Lọc vé"));
        filterPanel.setMaximumSize(new Dimension(300, 150)); // Giới hạn chiều cao
        
        addFilterComponent(filterPanel, "Theo ngày:", cboNgay = new JComboBox<>());
        addFilterComponent(filterPanel, "Theo phim:", cboPhim = new JComboBox<>());
        addFilterComponent(filterPanel, "Theo suất chiếu:", cboSuatChieu = new JComboBox<>());
        
        ActionListener filterListener = e -> filterVe();
        cboNgay.addActionListener(filterListener);
        cboPhim.addActionListener(filterListener);
        cboSuatChieu.addActionListener(filterListener);
        
        menuPanel.add(filterPanel);
        menuPanel.add(Box.createVerticalStrut(15));
        
        // Phần thông tin vé 
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(createTitledBorder("Thông tin vé"));
        infoPanel.setMaximumSize(new Dimension(300, 250)); 
        
        lblMaVe = createInfoLabel();
        lblMaPhim = createInfoLabel();
        lblMaSuatChieu = createInfoLabel();
        lblMaGhe = createInfoLabel();
        lblNgayDat = createInfoLabel();
        lblGiaVe = createInfoLabel();
        lblGiaVe.setVisible(false);
        
        infoPanel.add(lblMaVe);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblMaPhim);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblMaSuatChieu);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblMaGhe);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblNgayDat);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblGiaVe);
        
        menuPanel.add(infoPanel);
        menuPanel.add(Box.createVerticalGlue());
        
        // button
        btnInVe = new JButton("IN VÉ");
        styleButton(btnInVe);
        btnInVe.addActionListener(e -> inVe());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(btnInVe);
        menuPanel.add(buttonPanel);
        
    
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.EAST);
        
        this.add(mainPanel, BorderLayout.CENTER);
    }
    
    private Border createTitledBorder(String title) {
        return BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)), 
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            new Color(0, 102, 204)
        );
    }
    
    private void addFilterComponent(JPanel panel, String labelText, JComboBox<String> comboBox) {
        JPanel rowPanel = new JPanel(new BorderLayout(5, 5));
        rowPanel.setBorder(new EmptyBorder(3, 5, 3, 5));
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBox.getPreferredSize().height));
        
        rowPanel.add(label, BorderLayout.WEST);
        rowPanel.add(comboBox, BorderLayout.CENTER);
        
        panel.add(rowPanel);
    }
    
    private JLabel createInfoLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 100, 150)),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 150, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
    }
    
    private void loadSampleData() {
        veDao = new Ve_DAO();
        List<VeXemPhim> dsVe = veDao.layTatCaVe();
        danhSachVe.clear();
        danhSachVe.addAll(dsVe);
        updateFilterComboboxes();
    }
    
    private void updateFilterComboboxes() {
        cboNgay.removeAllItems();
        cboPhim.removeAllItems();
        cboSuatChieu.removeAllItems();
        
        cboNgay.addItem("Tất cả");
        cboPhim.addItem("Tất cả");
        cboSuatChieu.addItem("Tất cả");
        
        danhSachVe.stream()
            .map(v -> v.getNgayDat().toLocalDate().toString())
            .distinct()
            .forEach(cboNgay::addItem);
            
        danhSachVe.stream()
            .map(VeXemPhim::getMaPhim)
            .distinct()
            .forEach(cboPhim::addItem);
            
        danhSachVe.stream()
            .map(VeXemPhim::getMaSuatChieu)
            .distinct()
            .forEach(cboSuatChieu::addItem);
    }
    
    private void updateTable() {
        tableModel.setRowCount(0);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (VeXemPhim ve : danhSachVe) {
            tableModel.addRow(new Object[]{
                ve.getMaVe(),
                ve.getMaPhim(),
                ve.getMaSuatChieu(),
                ve.getMaGhe(),
                ve.getNgayDat().format(formatter)
            });
        }
    }
    
    private void filterVe() {
        String ngay = cboNgay.getSelectedItem() == null ? "Tất cả" : cboNgay.getSelectedItem().toString();
        String phim = cboPhim.getSelectedItem() == null ? "Tất cả" : cboPhim.getSelectedItem().toString();
        String suatChieu = cboSuatChieu.getSelectedItem() == null ? "Tất cả" : cboSuatChieu.getSelectedItem().toString();
        
        tableModel.setRowCount(0);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (VeXemPhim ve : danhSachVe) {
            boolean matchNgay = ngay.equals("Tất cả") || 
                              ve.getNgayDat().toLocalDate().toString().equals(ngay);
            boolean matchPhim = phim.equals("Tất cả") || ve.getMaPhim().equals(phim);
            boolean matchSuatChieu = suatChieu.equals("Tất cả") || ve.getMaSuatChieu().equals(suatChieu);
            
            if (matchNgay && matchPhim && matchSuatChieu) {
                tableModel.addRow(new Object[]{
                    ve.getMaVe(),
                    ve.getMaPhim(),
                    ve.getMaSuatChieu(),
                    ve.getMaGhe(),
                    ve.getNgayDat().format(formatter)
                });
            }
        }
    }
    
    private void updateSelectedVeInfo() {
        int selectedRow = tableVe.getSelectedRow();
        if (selectedRow >= 0) {
            String maVe = tableModel.getValueAt(selectedRow, 0).toString();
            VeXemPhim ve = danhSachVe.stream()
                .filter(v -> v.getMaVe().equals(maVe))
                .findFirst()
                .orElse(null);
                
            if (ve != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                
                lblMaVe.setText("Mã vé: " + ve.getMaVe());
                lblMaPhim.setText("Phim: " + ve.getMaPhim());
                lblMaSuatChieu.setText("Suất chiếu: " + ve.getMaSuatChieu());
                lblMaGhe.setText("Ghế: " + ve.getMaGhe());
                lblNgayDat.setText("Ngày đặt: " + ve.getNgayDat().format(formatter));
                lblGiaVe.setText("Giá vé: " + VeXemPhim.GIA_VE + " VNĐ");
                lblGiaVe.setVisible(true); // Hiển thị giá vé khi chọn
            }
        } else {
            lblGiaVe.setVisible(false); // Ẩn giá vé khi không chọn
        }
    }
    
    private void inVe() {
        int selectedRow = tableVe.getSelectedRow();
        if (selectedRow >= 0) {
            String maVe = tableModel.getValueAt(selectedRow, 0).toString();
            VeXemPhim ve = danhSachVe.stream()
                .filter(v -> v.getMaVe().equals(maVe))
                .findFirst()
                .orElse(null);
                
            if (ve != null) {
                JOptionPane.showMessageDialog(this, 
                    "In vé thành công!\nMã vé: " + ve.getMaVe() + 
                    "\nPhim: " + ve.getMaPhim() + 
                    "\nSuất chiếu: " + ve.getMaSuatChieu() + 
                    "\nGhế: " + ve.getMaGhe() + 
                    "\nGiá vé: " + VeXemPhim.GIA_VE + " VNĐ",
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn vé cần in", 
                "Cảnh báo", 
                JOptionPane.WARNING_MESSAGE);
        }
    }
}