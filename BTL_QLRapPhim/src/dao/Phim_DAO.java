package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.Phim;

public class Phim_DAO {
	
	public boolean createPhim(Phim phim) {
		Database.getInstance();
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			PreparedStatement stmt = con.prepareStatement("insert into Phim values (?,?,?,?,?)");
			
			stmt.setString(1, phim.getMaPhim());
			stmt.setString(2, phim.getTenPhim());
			stmt.setString(3, phim.getTheloai());
			stmt.setString(4, phim.getQuocGia());
			stmt.setInt(5, phim.getThoiLuong());
			
			n = stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public ArrayList<Phim> getAllPhim(){

		ArrayList<Phim> dsPhim = new ArrayList<Phim>();
		try {
			String query = "select *from Phim";
			Connection con = Database.getInstance().getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			while (rs.next()) {
				String maP = rs.getString(1);
				String tenP = rs.getString(2);
				String theL = rs.getString(3);
				String qg = rs.getString(4);
				int tl = rs.getInt(5);
				
				Phim ph = new Phim(maP, tenP, theL, qg, tl);
				dsPhim.add(ph);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsPhim;
	}
	public Phim getPhimTheoMa(String maPhim) {
	    Phim phim = null;
	    try {
	        String sql = "SELECT * FROM Phim WHERE maPhim = ?";
	        Connection con = Database.getInstance().getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, maPhim);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            phim = new Phim(
	                rs.getString("maPhim"),
	                rs.getString("tenPhim"),
	                rs.getString("theLoai"),
	                rs.getString("quocGia"),
	                rs.getInt("thoiLuong")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return phim;
	}

	public boolean xoaPhim(String maPhim) {
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0 ;
		try {
			stmt = con.prepareStatement("delete from Phim where maPhim = ?");
			stmt.setString(1, maPhim);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public boolean suaPhim(Phim p) {
		Database.getInstance();
		Connection con = Database.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
				    "update Phim set tenPhim = ?, theLoai = ?, quocGia = ?, thoiLuong = ? where maPhim = ?");
			stmt.setString(1, p.getTenPhim());
			stmt.setString(2,p.getTheloai());
			stmt.setString(3, p.getQuocGia());
			stmt.setInt(4, p.getThoiLuong());
			stmt.setString(5, p.getMaPhim());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n >0 ;
	}

}
