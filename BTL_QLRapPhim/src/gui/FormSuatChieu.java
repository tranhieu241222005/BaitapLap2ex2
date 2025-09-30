package gui;

import connectDB.Database;
import dao.Phim_DAO;
import dao.Phong_DAO;
import dao.SuatChieu_DAO;
import entity.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FormSuatChieu extends javax.swing.JPanel implements ActionListener,MouseListener, TreeSelectionListener {

    private JComboBox comboPhong;
    private DefaultTableModel mTable;
    private JScrollPane scrollPaneTree;
    private JTree treeSuatChieu;
    private JPanel pHead3;
    private JTextField txtNgay;
    private JButton btnXoaRong;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton btnTim;
    private JTextField txtTim;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JTextField txtMa;
    private JTextField txtTG;
    private JTextField txtPhong;
    private JComboBox comboPhim;
    private JPanel pWin;
    private JPanel pHead;
    private JPanel pHead1;
    private JPanel pHead2;
    private JPanel pDong3;
    private JPanel pCen;
    private JPanel pTable;
    private JPanel pCongCu;
    private JPanel pDong1;
    private JPanel pDong2;
    private JPanel pCongCu1;
    private JPanel pCongCu2;
    private JLabel lblTitle;
    private JTree tree1;
    private SuatChieu_DAO suatChieu_dao;
    private Phong_DAO phong_dao;
    private Phim_DAO phim_dao;
    private List<SuatChieu> dssc ;
    private List<Phim> dsphim;
    private List<Phong> dsphong;
    public FormSuatChieu() {
        try {
            Database.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        suatChieu_dao = new SuatChieu_DAO();
        phim_dao=new Phim_DAO();
        phong_dao = new Phong_DAO();

        this.setLayout(new BorderLayout());

        pHead = new JPanel(new BorderLayout());
        pHead1 = new JPanel();
        lblTitle = new JLabel("Quản Lý Suất Chiếu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pHead1.add(lblTitle);

        pHead2 = new JPanel(new GridLayout(3, 1, 5, 5));
        pDong1 = new JPanel(new GridLayout(1, 4, 10, 0));
        pDong2 = new JPanel(new GridLayout(1, 4, 10, 0));
        pDong3 = new JPanel(new GridLayout(1, 2, 10, 0));

        String[] temp = {"1","2"};
        txtMa = new JTextField();
        comboPhim = new JComboBox(temp);
        comboPhong = new JComboBox(temp);
        txtTG = new JTextField();
        txtNgay = new JTextField();
        tree1 = new JTree();

        JPanel panelMa = new JPanel(new BorderLayout(5, 0));
        JLabel lblMa = new JLabel("Mã suất:");
        lblMa.setPreferredSize(new Dimension(80, 25));
        panelMa.add(lblMa, BorderLayout.WEST);
        panelMa.add(txtMa, BorderLayout.CENTER);

        JPanel panelPhim = new JPanel(new BorderLayout(5, 0));
        JLabel lblPhim = new JLabel("Phim:");
        lblPhim.setPreferredSize(new Dimension(80, 25));
        panelPhim.add(lblPhim, BorderLayout.WEST);
        panelPhim.add(comboPhim, BorderLayout.CENTER);

        JPanel panelPhong = new JPanel(new BorderLayout(5, 0));
        JLabel lblPhong = new JLabel("Phòng:");
        lblPhong.setPreferredSize(new Dimension(80, 25));
        panelPhong.add(lblPhong, BorderLayout.WEST);
        panelPhong.add(comboPhong, BorderLayout.CENTER);

        JPanel panelTG = new JPanel(new BorderLayout(5, 0));
        JLabel lblTG = new JLabel("Thời gian:");
        lblTG.setPreferredSize(new Dimension(80, 25));
        panelTG.add(lblTG, BorderLayout.WEST);
        panelTG.add(txtTG, BorderLayout.CENTER);


        JPanel panelNgay = new JPanel(new BorderLayout(5, 0));
        JLabel lblNgay = new JLabel("Ngày chiếu:");
        lblNgay.setPreferredSize(new Dimension(80, 25));
        panelNgay.add(lblNgay, BorderLayout.WEST);
        panelNgay.add(txtNgay, BorderLayout.CENTER);

        pDong1.add(panelMa);
        pDong1.add(panelPhim);

        pDong2.add(panelPhong);
        pDong2.add(panelTG);

        pDong3.add(panelNgay);
        pDong3.add(new JLabel());

        pHead2.add(pDong1);
        pHead2.add(pDong2);
        pHead2.add(pDong3);

        pHead3 = new JPanel();
        treeSuatChieu = new JTree();
        scrollPaneTree = new JScrollPane(treeSuatChieu);
        scrollPaneTree.setPreferredSize(new Dimension(150, 100));

        pHead3.add(scrollPaneTree);

        JSplitPane pSlHead= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pHead3, pHead2);

        pHead.add(pHead1, BorderLayout.NORTH);
        pHead.add(pSlHead, BorderLayout.CENTER);

        // Table center
        pCen = new JPanel(new BorderLayout());
        String[] columnNames = {"Mã suất chiếu", "Phim", "Phòng", "Giờ chiếu", "Ngày"};
        Object[][] data = {};
        mTable = new DefaultTableModel(data, columnNames);
        table = new JTable(mTable);
        scrollPane = new JScrollPane(table);
        pCen.add(scrollPane, BorderLayout.CENTER);

        pCongCu = new JPanel(new GridLayout(1, 1));
        pCongCu1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pCongCu2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnTim = new JButton("Tìm kiếm");
        btnXoaRong = new JButton("Xóa Rỗng");

        btnThem.setPreferredSize(new Dimension(100, 30));
        btnSua.setPreferredSize(new Dimension(100, 30));
        btnXoa.setPreferredSize(new Dimension(100, 30));

        txtTim = new JTextField();
        txtTim.setPreferredSize(new Dimension(100, 30));

        pCongCu2.add(btnThem);
        pCongCu2.add(btnSua);
        pCongCu2.add(btnXoa);
        pCongCu2.add(btnXoaRong);
        pCongCu1.add(btnTim);
        pCongCu1.add(txtTim);

        JSplitPane pSl= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pCongCu1, pCongCu2);
        pCongCu.add(pSl);

        capNhatJTree();

        add(pHead, BorderLayout.NORTH);
        add(pCen, BorderLayout.CENTER);
        add(pCongCu, BorderLayout.SOUTH);

        btnSua.addActionListener(this);
        btnThem.addActionListener( this);
        btnXoa.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoaRong.addActionListener(this);
        table.addMouseListener(this);
        treeSuatChieu.addTreeSelectionListener(this);
        loadDataToTable();
        capNhatComboPhim();
        capNhatComboPhong();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnThem) {
            if(isValidata())
                themActions();
        } else if (obj == btnXoaRong) {
            xoaRongActions();
        } else if (obj == btnXoa) {
            xoaActions();
        } else if (obj == btnSua) {
            if(isValidata())
                suaActions();
        } else if (obj == btnTim) {
            timActions();
        }
        capNhatJTree();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        TreePath path = treeSuatChieu.getSelectionPath();
        if (row >= 0) {
            txtMa.setText(mTable.getValueAt(row, 0).toString());
            String maPhim = mTable.getValueAt(row, 1).toString();
            String maPhong = mTable.getValueAt(row, 2).toString();
            String thoiGian = mTable.getValueAt(row, 3).toString();
            String ngay = mTable.getValueAt(row, 4).toString();
            for (int i = 0; i < comboPhim.getItemCount(); i++) {
                if (comboPhim.getItemAt(i).toString().equalsIgnoreCase(maPhim)) {
                    comboPhim.setSelectedIndex(i);
                    break;
                }
            }
            for (int i = 0; i < comboPhong.getItemCount(); i++) {
                if (comboPhong.getItemAt(i).toString().equalsIgnoreCase(maPhong)) {
                    comboPhong.setSelectedIndex(i);
                    break;
                }
            }
            txtTG.setText(thoiGian);
            txtNgay.setText(ngay);

        }




    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {


    }
    private void capNhatComboPhong() {
        comboPhong.removeAllItems();
        dsphong = phong_dao.getAllPhong();
        for (Phong p : dsphong) {
            comboPhong.addItem(p.getMaPhong());
        }
    }

    private void capNhatComboPhim() {
        comboPhim.removeAllItems();
        dsphim = phim_dao.getAllPhim();
        for (Phim p : dsphim) {
            comboPhim.addItem(p.getMaPhim());
        }
    }
    private void loadDataToTable() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        mTable.setRowCount(0);
        dssc = suatChieu_dao.getAllSuatChieu();

        for (SuatChieu sc: dssc) {
            LocalDate ngay = sc.getNgay();
            LocalDateTime thoiGian = sc.getThoiGian();
            String ngayStr = ngay.format(dateFormatter);
            String thoiGianStr = thoiGian.format(dateTimeFormatter);
            mTable.addRow(new Object[]{sc.getMaSuat(), sc.getMaPhim(), sc.getMaPhong(),
                   thoiGianStr, ngayStr});
        }
    }
    public void themActions() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            String ma = txtMa.getText().trim();
            for(SuatChieu sc : dssc){
                if(sc.getMaSuat().equalsIgnoreCase(ma)){
                    JOptionPane.showMessageDialog(this, "Mã trùng");
                    return;
                }
            }
            String ngayStr = txtNgay.getText().trim();
            String thoiGianStr = txtTG.getText().trim();
            String phong = (String) comboPhong.getSelectedItem();
            String phim = (String) comboPhim.getSelectedItem();
            if (ma.isEmpty() || phong == null || phim == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            LocalDate ngay = LocalDate.parse(ngayStr, dateFormatter);
            LocalDateTime thoiGian = LocalDateTime.parse(thoiGianStr, dateTimeFormatter);
            SuatChieu suatChieu = new SuatChieu(ma, thoiGian, ngay, phim, phong);
            try {
                suatChieu_dao.createSuatChieu(suatChieu);
                loadDataToTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Mã bị trùng hoặc lỗi dữ liệu.");
            }
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Định dạng ngày hoặc thời gian không hợp lệ (dd/MM/yyyy hoặc dd/MM/yyyy HH:mm)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi không xác định: " + e.getMessage());
            e.printStackTrace();
        }
        capNhatJTree();
    }
    private void xoaRongActions() {
        txtMa.requestFocus();
        txtMa.setText("");
        txtTG.setText("");
        txtNgay.setText("");
        txtTim.setText("");
        comboPhong.setSelectedIndex(0);
        comboPhim.setSelectedIndex(0);
    }
    public void xoaActions() {
        int[] rowIndexs = table.getSelectedRows();
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Chưa có dòng nào được chọn!");
            return;
        } else {
            if (JOptionPane.showConfirmDialog(this, "Chắc chắc muốn xóa dòng này?", "Cảnh báo!",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                for (int i = 0; i < rowIndexs.length; i++) {
                    String ma = mTable.getValueAt(rowIndexs[i], 0).toString();
                    if (suatChieu_dao.deleteSuatChieu(ma)) {
                        mTable.removeRow(rowIndexs[i]);
                    }
                }
                xoaRongActions();
            }

        }
    }
    public void suaActions() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        int selectedIndex = table.getSelectedRow();
        String ma = mTable.getValueAt(selectedIndex, 0).toString();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một cuốn sách!");
            return;
        } else {
            if (!ma.equalsIgnoreCase(txtMa.getText())) {
                JOptionPane.showMessageDialog(this, "Mã sách không thể cập nhât!");
                return;
            }
            String maa = txtMa.getText();
            String ngayStr = txtNgay.getText();
            String thoiGianStr = txtTG.getText();
            String phong = (String) comboPhong.getSelectedItem();
            String phim = (String) comboPhim.getSelectedItem();
            LocalDate ngay = LocalDate.parse(ngayStr, dateFormatter);
            LocalDateTime thoiGian = LocalDateTime.parse(thoiGianStr, dateTimeFormatter);

            SuatChieu suatChieu = new SuatChieu(maa, thoiGian, ngay, phim, phong);
            suatChieu_dao.updateSuatChieu(suatChieu);
            capNhatJTree();
            loadDataToTable();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
        }
    }
    public void capNhatJTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Quản lý suất chiếu");

        Map<LocalDate, List<SuatChieu>> map = new TreeMap<>();
        List<SuatChieu> danhSach = suatChieu_dao.getAllSuatChieu();

        for (SuatChieu sc : danhSach) {
            LocalDate ngay = sc.getNgay();
            map.computeIfAbsent(ngay, k -> new ArrayList<>()).add(sc);
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Map.Entry<LocalDate, List<SuatChieu>> entry : map.entrySet()) {
            LocalDate ngay = entry.getKey();
            DefaultMutableTreeNode ngayNode = new DefaultMutableTreeNode("Ngày chiếu: " + dateFormatter.format(ngay));

            for (SuatChieu sc : entry.getValue()) {
                String chiTiet = String.format("Mã: %s - Phim: %s - Phòng: %s", sc.getMaSuat(), sc.getMaPhim(), sc.getMaPhong());
                ngayNode.add(new DefaultMutableTreeNode(chiTiet));
            }

            root.add(ngayNode);
        }
        if (treeSuatChieu == null) {
            treeSuatChieu = new JTree();
        }
        treeSuatChieu.setModel(new DefaultTreeModel(root));
        treeSuatChieu.expandRow(0);
    }

    private void timActions() {
        String ma = txtTim.getText();
        if (ma.trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Mã trống!");
            txtTim.requestFocus();
            return;
        }
        SuatChieu sc = suatChieu_dao.getSuatChieuByMaSuat(ma);
        table.clearSelection();
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 0).toString().equalsIgnoreCase(ma)) {
                table.setRowSelectionInterval(i, i);
            }
        }
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeSuatChieu.getLastSelectedPathComponent();
        if (selectedNode == null) return;
        String nodeText = selectedNode.toString();
        if (selectedNode.isRoot()) {
            hienThiTatCaSuatChieu();
        } else if (nodeText.startsWith("Ngày chiếu: ")) {
            String dateStr = nodeText.substring("Ngày chiếu: ".length());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate selectedDate = LocalDate.parse(dateStr, formatter);
                filterTableByDate(selectedDate);
            } catch (DateTimeParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void hienThiTatCaSuatChieu() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        mTable.setRowCount(0);
        for (SuatChieu sc : dssc) {
            mTable.addRow(new Object[]{
                    sc.getMaSuat(),
                    sc.getMaPhim(),
                    sc.getMaPhong(),
                    sc.getThoiGian().format(dateTimeFormatter),
                    sc.getNgay().format(dateFormatter)
            });
        }
    }
    private void filterTableByDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        mTable.setRowCount(0);
        for (SuatChieu sc : dssc) {
            if (sc.getNgay().equals(date)) {
                mTable.addRow(new Object[]{
                        sc.getMaSuat(),
                        sc.getMaPhim(),
                        sc.getMaPhong(),
                        sc.getThoiGian().format(dateTimeFormatter),
                        sc.getNgay().format(dateFormatter)
                });
            }
        }
    }
    private boolean isValidata() {
        String ma = txtMa.getText().trim();
        String ngayStr = txtNgay.getText().trim();
        String thoiGianStr = txtTG.getText().trim();

        if (ma.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Mã suất chiếu không được để trống!");
            txtMa.requestFocus();
            return false;
        }else {
            if (!ma.matches("^SC[0-9]{3}$")) {
                JOptionPane.showMessageDialog(this, "Mã suất chiéu phải bắt đầu bằng ký tự SC theo sau là 3 ký số!");
                txtMa.setText("SC");
                txtMa.requestFocus();
                return false;
            }
        }
        if ( ngayStr.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Ngày không được để trống!");
            txtNgay.requestFocus();
            return false;
        }
        if ( thoiGianStr.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Thời gian không được để trống!");
            txtNgay.requestFocus();
            return false;
        }
        return true;
    }
}



