package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.Database;

public class DangNhap_DAO {
	public DangNhap_DAO() {
		
	}
	public boolean checklogin(String username , String password) {
		try {
			String sql="SELECT * FROM TaiKhoan WHERE username ='" +username + "' "
					+ "AND password = '" +password + "'";
			Connection con = Database.getInstance().getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}


