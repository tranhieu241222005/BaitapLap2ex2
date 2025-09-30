package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.Phong;

public class Phong_DAO {
    public Phong_DAO() {

    }

    public boolean themPhong(Phong phong) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO Phong (maPhong, tenPhong) VALUES (?, ?)");
            stmt.setString(1, phong.getMaPhong());
            stmt.setString(2, phong.getTenPhong());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean capNhatPhong(Phong phong) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE Phong SET tenPhong = ? WHERE maPhong = ?");
            stmt.setString(1, phong.getTenPhong());
            stmt.setString(2, phong.getMaPhong());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean xoaPhong(String maPhong) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM Phong WHERE maPhong = ?");
            stmt.setString(1, maPhong);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public Phong timPhongTheoMa(String maPhong) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Phong phong = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM Phong WHERE maPhong = ?");
            stmt.setString(1, maPhong);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String tenPhong = rs.getString("tenPhong");
                phong = new Phong(maPhong, tenPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phong;
    }

    public List<Phong> getAllPhong() {
        List<Phong> dsPhong = new ArrayList<Phong>();
        try {
            String sql = "SELECT * FROM Phong";
            Connection con = Database.getInstance().getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");

                Phong phong = new Phong(maPhong, tenPhong);
                dsPhong.add(phong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsPhong;
    }
}
