package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connectDB.Database;
import dao.NhanVien_DAO;
import dao.Phim_DAO;
import entity.NhanVien;
import entity.Phim;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class FormPhim extends JPanel implements ActionListener, MouseListener{
    private JTextField txtMaPhim, txtTenPhim, txtQuocGia, txtThoiLuong;
    private JComboBox<String> cboTheLoai;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnXoa, btnSua, btnLuu;
	private JButton btnXoaRong;
	private Phim_DAO phim_DAO;
	private JTextField txtTim;
	private JButton btnTim;


    public FormPhim() {
        try {
            Database.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        phim_DAO = new Phim_DAO();
    	
    	
        setPreferredSize(new Dimension(900, 700));
        setLayout(new BorderLayout(10, 10));
        // Panel nhập liệu
        JPanel pnlInput = new JPanel(new GridLayout(3, 1, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Thông tin phim"));

        // Dòng 1: Mã phim + Tên phim
        JPanel row1 = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel maPhimPanel = new JPanel(new BorderLayout());
        maPhimPanel.add(new JLabel("Mã phim:"), BorderLayout.NORTH);
        txtMaPhim = new JTextField();
        maPhimPanel.add(txtMaPhim, BorderLayout.CENTER);
        JPanel tenPhimPanel = new JPanel(new BorderLayout());
        tenPhimPanel.add(new JLabel("Tên phim:"), BorderLayout.NORTH);
        txtTenPhim = new JTextField();
        tenPhimPanel.add(txtTenPhim, BorderLayout.CENTER);
        row1.add(maPhimPanel);
        row1.add(tenPhimPanel);

        // Dòng 2: Thể loại + Quốc gia
        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel theLoaiPanel = new JPanel(new BorderLayout());
        theLoaiPanel.add(new JLabel("Thể loại:"), BorderLayout.NORTH);
        cboTheLoai = new JComboBox<>(new String[]{"Chọn thể loại","Hành động", "Hài hước", "Tình cảm", "Kinh dị", "Phiêu lưu"});
        theLoaiPanel.add(cboTheLoai, BorderLayout.CENTER);
        JPanel quocGiaPanel = new JPanel(new BorderLayout());
        quocGiaPanel.add(new JLabel("Quốc gia:"), BorderLayout.NORTH);
        txtQuocGia = new JTextField();
        quocGiaPanel.add(txtQuocGia, BorderLayout.CENTER);
        row2.add(theLoaiPanel);
        row2.add(quocGiaPanel);

        // Dòng 3: Thời lượng
        JPanel row3 = new JPanel(new BorderLayout());
        row3.add(new JLabel("Thời lượng (phút):"), BorderLayout.NORTH);
        txtThoiLuong = new JTextField();
        row3.add(txtThoiLuong, BorderLayout.CENTER);

        pnlInput.add(row1);
        pnlInput.add(row2);
        pnlInput.add(row3);

        add(pnlInput, BorderLayout.NORTH);

        // Table - ScrollPane
        String[] columnNames = {"Mã phim", "Tên phim", "Thể loại", "Quốc gia", "Thời lượng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách phim"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        JLabel lblTim = new JLabel("Nhập mã phim cần tìm");
        txtTim = new JTextField(10);
        btnTim = new JButton("Tìm");
        searchPanel.add(lblTim);
        searchPanel.add(txtTim);
        searchPanel.add(btnTim);
        // Panel nút 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnThem = new JButton("Thêm");
        btnXoaRong = new JButton("Xóa rỗng");
        btnXoa = new JButton("Xóa");
        btnSua = new JButton("Sửa");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoaRong);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnSua);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, searchPanel, buttonPanel);
        add(splitPane,BorderLayout.SOUTH);
        // Sự kiện button
        btnTim.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaRong.addActionListener(this);
        btnSua.addActionListener(this);
        table.addMouseListener(this);
        loadData();

    }
    public void loadData() {
    	
    	List<Phim> list = phim_DAO.getAllPhim();
    	for (Phim p : list) {
    		tableModel.addRow(new Object[] {
    				p.getMaPhim(),
    				p.getTenPhim(),
    				p.getTheloai(),
    				p.getQuocGia(),
    				p.getThoiLuong()
    		});
    	}
    }
    
    
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
        if (row >= 0) {
            txtMaPhim.setText(tableModel.getValueAt(row, 0).toString());
            txtTenPhim.setText(tableModel.getValueAt(row, 1).toString());
            cboTheLoai.setSelectedItem(tableModel.getValueAt(row, 2).toString());
            txtQuocGia.setText(tableModel.getValueAt(row, 3).toString());
            txtThoiLuong.setText(tableModel.getValueAt(row, 4).toString());
        }	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnThem)
			actThem();
		if (obj == btnXoaRong)
			actXoaRong();
		if (obj == btnXoa)
			actXoa();
		if (obj == btnSua)
			actSua();
		if (obj == btnTim)
			actTim();
		
	}
	
	private void actTim() {
	    String maTim = txtTim.getText().trim();
	    tableModel.setRowCount(0); 

	    if (maTim.isEmpty()) {
	        loadData();
	    } else {
	        Phim phim = phim_DAO.getPhimTheoMa(maTim);
	        if (phim != null) {
	            tableModel.addRow(new Object[]{
	                phim.getMaPhim(),
	                phim.getTenPhim(),
	                phim.getTheloai(),
	                phim.getQuocGia(),
	                phim.getThoiLuong()
	            });
	        } else {
	            JOptionPane.showMessageDialog(this, "Không tìm thấy phim có mã: " + maTim);
	            loadData();
	            txtTim.setText("");
	            txtTim.requestFocus();
	        }
	    }
	}



	private void actSua() {
		int row = table.getSelectedRow();
		if (row >= 0) {
        	try {
        	 if (isValidata()) {
        		 String maPhim = txtMaPhim.getText();
       	   	     String tenPhim = txtTenPhim.getText();
       	   	     String theLoai = cboTheLoai.getSelectedItem().toString();
       	   	     String quocGia = txtQuocGia.getText();
       	   	     int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
       	   	     Phim p = new Phim(maPhim, tenPhim, theLoai, quocGia, thoiLuong);
       	   	     if (phim_DAO.suaPhim(p)) {
                       tableModel.setValueAt(tenPhim, row, 1);
                       tableModel.setValueAt(theLoai, row, 2);
                       tableModel.setValueAt(quocGia, row, 3);
                       tableModel.setValueAt(thoiLuong, row, 4);

                   }
       	   	     JOptionPane.showMessageDialog(this, "Sửa thành công");
        	 }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }else {
        	JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa!");
        }
        	
		
	}

	private void actXoa() {
	    int row = table.getSelectedRow();
	    if (row >= 0) {
	        String maPhim = tableModel.getValueAt(row, 0).toString();
	        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            try {
	                if (phim_DAO.xoaPhim(maPhim)) {
	                    tableModel.removeRow(row);
	                    actXoaRong();
	                    JOptionPane.showMessageDialog(this, "Xóa thành công!");
	                } else {
	                    JOptionPane.showMessageDialog(this, "Không xóa được phim do đang có suất chiếu!");
	                }
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Không thể xóa do phim đang có suất chiếu");
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim để xóa!");
	    }
	}


	private void actXoaRong() {
        txtMaPhim.setText("");
        txtTenPhim.setText("");
        cboTheLoai.setSelectedIndex(0);
        txtQuocGia.setText("");
        txtThoiLuong.setText("");	
        txtTim.setText("");
	}
	private boolean isValidata() {
		String maPhim = txtMaPhim.getText();
	    String tenPhim = txtTenPhim.getText();
	    String theLoai = cboTheLoai.getSelectedItem().toString();
	    String quocGia = txtQuocGia.getText();
	    String thoiLuong = txtThoiLuong.getText();
	    //maphim
	    if (maPhim.length() <= 0) {
	    	JOptionPane.showMessageDialog(this, "Mã phim không được để trống!");
	    	txtMaPhim.requestFocus();
	    	return false;
	    }else {
	    	if (!maPhim.matches("^P[0-9]{3}$")) {
	    		JOptionPane.showMessageDialog(this, "Mã phim phải bắt đầu bằng ký tự P theo sau là 3 ký số!");
	    		txtMaPhim.setText("P");
	    		txtMaPhim.requestFocus();
	    		return false;
	    	}
	    }
	    //ten phim
	    if ( tenPhim.length() <= 0) {
	    	JOptionPane.showMessageDialog(this, "Tên phim không được để trống!");
	    	txtTenPhim.requestFocus();
	    	return false;
	    }else {
	    	if ( !tenPhim.matches("^[A-Za-z0-9']+(\\s[A-Za-z0-9']+)*$")) {
	    		JOptionPane.showMessageDialog(this, "Tên phim bao gồm các ký tự chữ cái, ký số và dấu '");
	    		txtTenPhim.setText("");
	    		txtTenPhim.requestFocus();
	    		return false;
	    	}
	    }
	    if (theLoai.equalsIgnoreCase("Chọn thể loại")) {
	    	JOptionPane.showMessageDialog(this, "Bạn chưa chọn thể loại cho phim");
	    	return false;
	    }
	    if ( quocGia.length() <= 0) {
	    	JOptionPane.showMessageDialog(this, "Tên Quốc gia không được để trống!");
	    	txtQuocGia.requestFocus();
	    	return false;
	    }else {
	    	if (!quocGia.matches("^[A-Z][A-Za-z]+")) {
	    		JOptionPane.showMessageDialog(this, "Tên Quốc gia bao gồm các ký tự chữ cái và viết hoa chữ cái đầu");
	    		txtQuocGia.setText("");
	    		txtQuocGia.requestFocus();
	    		return false;
	    	}
	    }
	    if ( thoiLuong.length() <= 0) {
	    	JOptionPane.showMessageDialog(this, "Thời lượng phim không được để trống!");
	    	txtThoiLuong.requestFocus();
	    }else {
	    	int tl = Integer.parseInt(thoiLuong);
		    if ( tl <= 0) {
		    	JOptionPane.showMessageDialog(this, "Thời lượng phim phải lớn hơn 0");
		    	txtThoiLuong.setText("");
		    	txtThoiLuong.requestFocus();
		    	return false;
		    }
	    }
	    
	    
	    return true;
	}
	private void actThem() {
		 if ( isValidata()) {
			 String maPhim = txtMaPhim.getText();
		     String tenPhim = txtTenPhim.getText();
		     String theLoai = cboTheLoai.getSelectedItem().toString();
		     String quocGia = txtQuocGia.getText();
		     int thoiLuong = Integer.parseInt(txtThoiLuong.getText());
		     Phim p = new Phim(maPhim, tenPhim, theLoai, quocGia, thoiLuong);
		     try {
				if ( phim_DAO.createPhim(p)) {
					tableModel.addRow(new Object[] {maPhim, tenPhim, theLoai, quocGia, Integer.toString(thoiLuong)});
					actXoaRong();	
				}else {
					JOptionPane.showMessageDialog(this, "Mã bị trùng!");
					txtMaPhim.requestFocus();
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
	     	
	}

}
