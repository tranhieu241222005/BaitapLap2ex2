package gui;

import connectDB.Database;
import dao.NhanVien_DAO;
import entity.NhanVien;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Dimension;


public class FormNhanVien extends JPanel implements ActionListener, MouseListener {
    private JTextField txtMaNhanVien, txtHoTen, txtSoDienThoai, txtEmail, txtDiaChi, txtNhapMaCanTim;
    private JComboBox<String> cbChucVu;
    private JRadioButton rdNam, rdNu, rdDangLam, rdNghi;
    private ButtonGroup groupGioiTinh, groupTrangThai;
    private JButton btnThem, btnXoaRong, btnSua, btnXoa, btnTim;
    private JTable table;
    private DefaultTableModel model;
    private NhanVien_DAO nhanVienDAO;

    public FormNhanVien() {
        try {
            Database.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        nhanVienDAO = new NhanVien_DAO();

        setLayout(new BorderLayout());

        JPanel pnNorth = new JPanel();
        pnNorth.setBorder(BorderFactory.createTitledBorder("Chỉnh Sửa Hồ Sơ"));

        Box mainBox = Box.createHorizontalBox();
        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();

        JLabel lblMaNhanVien = new JLabel("Mã nhân viên:");
        JLabel lblHoTen = new JLabel("Họ và Tên:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");

        JLabel lblSoDienThoai = new JLabel("Số điện thoại:");
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        JLabel lblChucVu = new JLabel("Chức vụ:");
        JLabel lblGioiTinh = new JLabel("Giới tính:");

        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setPreferredSize(new Dimension(500, 35));
        txtHoTen = new JTextField();
        txtHoTen.setPreferredSize(new Dimension(500, 35));
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(500, 35));
        txtDiaChi = new JTextField();
        txtDiaChi.setPreferredSize(new Dimension(500, 35));
        txtSoDienThoai = new JTextField();
        txtSoDienThoai.setPreferredSize(new Dimension(200, 35)); 

        rdDangLam = new JRadioButton("Đang làm");
        rdNghi = new JRadioButton("Nghỉ");
        groupTrangThai = new ButtonGroup();
        groupTrangThai.add(rdDangLam);
        groupTrangThai.add(rdNghi);

        String[] chucVu = {"Chọn chức vụ", "Quản Lý", "Nhân Viên"};
        cbChucVu = new JComboBox<>(chucVu);

        rdNam = new JRadioButton("Nam");
        rdNu = new JRadioButton("Nữ");
        groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(rdNam);
        groupGioiTinh.add(rdNu);

        box1.add(createRow(lblMaNhanVien, txtMaNhanVien));
        box1.add(Box.createVerticalStrut(25));
        box1.add(createRow(lblHoTen, txtHoTen));
        box1.add(Box.createVerticalStrut(25));
        box1.add(createRow(lblEmail, txtEmail)); 
        box1.add(Box.createVerticalStrut(25));
        box1.add(createRow(lblDiaChi, txtDiaChi));

        box2.add(createRow(lblSoDienThoai, txtSoDienThoai));
        box2.add(Box.createVerticalStrut(25));
        box2.add(createRow(lblTrangThai, rdDangLam, rdNghi));
        box2.add(Box.createVerticalStrut(25));
        box2.add(createRow(lblChucVu, cbChucVu));
        box2.add(Box.createVerticalStrut(25));
        box2.add(createRow(lblGioiTinh, rdNam, rdNu));

        mainBox.add(box1);
        mainBox.add(Box.createHorizontalStrut(20));
        mainBox.add(box2);

        pnNorth.add(mainBox);
        add(pnNorth, BorderLayout.NORTH);
        
		
		lblHoTen.setPreferredSize(lblMaNhanVien.getPreferredSize());
		lblEmail.setPreferredSize(lblMaNhanVien.getPreferredSize());
		lblDiaChi.setPreferredSize(lblMaNhanVien.getPreferredSize());

        JPanel pCenter = new JPanel(new BorderLayout());
        JPanel pControl = new JPanel();

        btnThem = new JButton("Thêm");
        btnXoaRong = new JButton("Xóa rỗng");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        JLabel lblNhapMa = new JLabel("Nhập mã cần tìm:");
        txtNhapMaCanTim = new JTextField(10);
        btnTim = new JButton("Tìm");

        pControl.add(btnThem);
        pControl.add(btnXoaRong);
        pControl.add(btnSua);
        pControl.add(btnXoa);
        pControl.add(lblNhapMa);
        pControl.add(txtNhapMaCanTim);
        pControl.add(btnTim);



        pCenter.add(pControl, BorderLayout.NORTH);

        String[] columnNames = {"Mã nhân viên", "Họ và Tên", "Chức vụ", "Giới Tính",
                "Số điện thoại", "Email", "Địa chỉ", "Trạng thái"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(250);

        pCenter.add(scrollPane, BorderLayout.CENTER);
        add(pCenter, BorderLayout.CENTER);


        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoaRong.addActionListener(this);
        table.addMouseListener(this);

        loadDataToTable();
    }

    private Box createRow(JComponent label, JComponent... fields) {
        Box row = Box.createHorizontalBox();
        row.add(label);
        for (JComponent field : fields) {
            row.add(Box.createHorizontalStrut(10));
            row.add(field);
        }
        row.add(Box.createVerticalStrut(10));
        return row;
    }

    private void loadDataToTable() {
        model.setRowCount(0);
        List<NhanVien> list = nhanVienDAO.getallnhanvien();
        for (NhanVien nv : list) {
            model.addRow(new Object[]{nv.getMaNhanVien(), nv.getHoTen(), nv.getChucvu(),
                    nv.getGioiTinh() ? "Nam" : "Nữ", nv.getSoDienThoai(), nv.getEmail(),
                    nv.getDiaChi(), nv.getTrangThai() ? "Đang làm" : "Nghỉ"});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnXoaRong) {
            clearForm();
        }else if (source == btnThem) {
            addNhanVien();
        } else if (source == btnXoa) {
            deleteNhanVien();
        } else if (source == btnSua) {
            updateNhanVien();
        } else if (source == btnTim) {
            searchNhanVien();
        }
    }

    private void clearForm() {
        txtMaNhanVien.setText("");
        txtHoTen.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
        txtNhapMaCanTim.setText("");
        cbChucVu.setSelectedIndex(0);
        groupGioiTinh.clearSelection();
        groupTrangThai.clearSelection();
        txtMaNhanVien.requestFocus();
    }
    public boolean isValidata() {
	    String ma = txtMaNhanVien.getText();
        String ten = txtHoTen.getText();
        String chucVu = cbChucVu.getSelectedItem().toString();
        String sdt = txtSoDienThoai.getText();
        String email = txtEmail.getText();
        String diaChi = txtDiaChi.getText();
        
        if (!(ma.length() >0 && ma.matches("^NV[0-9]{3}$"))) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên phải bắt đầu bằng 'NV' và theo sau là 3 chữ số (ví dụ: NV001)");
            return false;
        }
        if (!(ten.length() > 0 && ten.matches("^[\\p{L}]+([ ][\\p{L}]+)*$"))) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên gồm các chữ cái (có thể có dấu) cách nhau bởi khoảng trắng.");
            return false;
        }
        if (!(diaChi.length() > 0 && diaChi.matches("^[\\p{L}0-9\\s,./-]+$"))) {
            JOptionPane.showMessageDialog(this, "Địa chỉ chỉ chứa chữ (có dấu), số, khoảng trắng và các ký tự như , . / -");
            return false;
        }
        if (!(email.length() > 0 && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))) {
	    	JOptionPane.showMessageDialog(this,"Email khong hop le");
	    	return false;
        }
        if (!(sdt.length() > 0 && sdt.matches("^\\d{10}$"))) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải gồm đúng 10 chữ số.");
            return false;
        }
        if (chucVu.equals("Chọn chức vụ")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ.");
            return false;
        }
        if (!rdNam.isSelected() && !rdNu.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính.");
            return false;
        }
        if (!rdDangLam.isSelected() && !rdNghi.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái làm việc.");
            return false;
        }
        return true;
    }
    private void addNhanVien() {
    	if(isValidata()) {
    	    String ma = txtMaNhanVien.getText();
            String ten = txtHoTen.getText();
            String chucVu = cbChucVu.getSelectedItem().toString();
            boolean gioiTinh = rdNam.isSelected();
            String sdt = txtSoDienThoai.getText();
            String email = txtEmail.getText();
            String diaChi = txtDiaChi.getText();
            boolean trangThai = rdDangLam.isSelected();
           
            NhanVien nv = new NhanVien(ma, ten, chucVu, gioiTinh, sdt, email, diaChi, trangThai);
            try {
                if(nhanVienDAO.createnv(nv)) {
                	model.addRow(new Object[]{ma, ten, chucVu, gioiTinh ? "Nam" : "Nữ", sdt, email, diaChi, trangThai ? "Đang làm" : "Nghỉ"});
                }else {
                	JOptionPane.showMessageDialog(this, "Mã không thể trùng!");
                	txtMaNhanVien.requestFocus();
                	return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Mã bị trùng hoặc lỗi dữ liệu.");
            }
    	}
    }
   
    private void deleteNhanVien() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String ma = model.getValueAt(row, 0).toString();
            if (nhanVienDAO.deletenv(ma)) {
                model.removeRow(row);
                clearForm();
            }
        }else {
        	JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng đê xóa");
        }
    }

    private void updateNhanVien() {
        int row = table.getSelectedRow();
        if (row >= 0) {
        	try {
        		if(isValidata()) {
           		 String ma = txtMaNhanVien.getText();
                    String ten = txtHoTen.getText();
                    String chucVu = cbChucVu.getSelectedItem().toString();
                    boolean gioiTinh = rdNam.isSelected();
                    String sdt = txtSoDienThoai.getText();
                    String email = txtEmail.getText();
                    String diaChi = txtDiaChi.getText();
                    boolean trangThai = rdDangLam.isSelected();

                    NhanVien nv = new NhanVien(ma, ten, chucVu, gioiTinh, sdt, email, diaChi, trangThai);
                    if (nhanVienDAO.updatenv(nv)) {
                        model.setValueAt(ten, row, 1);
                        model.setValueAt(chucVu, row, 2);
                        model.setValueAt(gioiTinh ? "Nam" : "Nữ", row, 3);
                        model.setValueAt(sdt, row, 4);
                        model.setValueAt(email, row, 5);
                        model.setValueAt(diaChi, row, 6);
                        model.setValueAt(trangThai ? "Đang làm" : "Nghỉ", row, 7);
                    }
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else {
        	JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
        }
    }

    private void searchNhanVien() {
        String ma = txtNhapMaCanTim.getText().trim();
        model.setRowCount(0);
        boolean found = false;

        List<NhanVien> list = nhanVienDAO.getallnhanvien();
        for (NhanVien nv : list) {
            if (ma.isEmpty() || nv.getMaNhanVien().equalsIgnoreCase(ma)) {
                model.addRow(new Object[]{nv.getMaNhanVien(), nv.getHoTen(), nv.getChucvu(),
                        nv.getGioiTinh() ? "Nam" : "Nữ", nv.getSoDienThoai(), nv.getEmail(),
                        nv.getDiaChi(), nv.getTrangThai() ? "Đang làm" : "Nghỉ"});
                found = true;
            }
        }
        if (!found && !ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã: " + ma);
            txtNhapMaCanTim.setText("");
            txtNhapMaCanTim.requestFocus();
            loadDataToTable();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaNhanVien.setText(model.getValueAt(row, 0).toString());
            txtHoTen.setText(model.getValueAt(row, 1).toString());
            String chucvu = model.getValueAt(row, 2).toString().trim();
    		for (int i = 0; i < cbChucVu.getItemCount(); i++) {
    		    if (cbChucVu.getItemAt(i).toString().equalsIgnoreCase(chucvu)) {
    		        cbChucVu.setSelectedIndex(i);
    		        break;
    		    }
    		}
            (model.getValueAt(row, 3).toString().equals("Nam") ? rdNam : rdNu).setSelected(true);
            txtSoDienThoai.setText(model.getValueAt(row, 4).toString());
            txtEmail.setText(model.getValueAt(row, 5).toString());
            txtDiaChi.setText(model.getValueAt(row, 6).toString());
            (model.getValueAt(row, 7).toString().equals("Đang làm") ? rdDangLam : rdNghi).setSelected(true);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}