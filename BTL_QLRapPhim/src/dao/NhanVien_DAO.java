package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.NhanVien;

public class NhanVien_DAO {
	public NhanVien_DAO() {
		
	}
	
	public List<NhanVien> getallnhanvien(){
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		
		try {
			String sql = "SELECT * FROM NhanVien";
			Connection con = Database.getInstance().getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				String maNhanVien = rs.getString("maNhanVien");
				String hoTen = rs.getString("hoTen");
				String chucVu = rs.getString("chucVu");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				String diaChi = rs.getString("diaChi");
				boolean trangThai = rs.getBoolean("trangThai");
				
				NhanVien nv = new NhanVien(maNhanVien, hoTen, chucVu,gioiTinh, soDienThoai, email, diaChi, trangThai);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	
	public boolean createnv(NhanVien nv) {
		Database.getInstance();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into " + "NhanVien values(?,?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoTen());
			stmt.setString(3, nv.getChucvu());
			stmt.setBoolean(4, nv.getGioiTinh());
			stmt.setString(5, nv.getSoDienThoai());
			stmt.setString(6, nv.getEmail());
			stmt.setString(7, nv.getDiaChi());
			stmt.setBoolean(8, nv.getTrangThai());
			
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	public boolean updatenv (NhanVien nv ) {
		Database.getInstance();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
				    "update NhanVien set hoTen = ?, chucVu = ?, gioiTinh = ?, soDienThoai = ?, email = ?, diaChi = ?, trangThai = ? "
				    + "where maNhanVien = ?");
			stmt.setString(1, nv.getHoTen());
			stmt.setString(2, nv.getChucvu());
			stmt.setBoolean(3, nv.getGioiTinh());
			stmt.setString(4, nv.getSoDienThoai());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getDiaChi());
			stmt.setBoolean(7, nv.getTrangThai());
			stmt.setString(8, nv.getMaNhanVien());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n >0 ;
	}
	
	public boolean deletenv (String manv) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0 ;
		try {
			stmt = con.prepareStatement("delete from NhanVien where maNhanVien = ?");
			stmt.setString(1, manv);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
}
