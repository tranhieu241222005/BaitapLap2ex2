package dao;

import connectDB.Database;
import entity.SuatChieu;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SuatChieu_DAO {

    public SuatChieu_DAO() {
    }

    public boolean createSuatChieu(SuatChieu suatChieu) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "INSERT INTO SuatChieu (maSuatChieu, thoiGian, ngay, maPhim, maPhong) VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, suatChieu.getMaSuat());
            stmt.setTimestamp(2, Timestamp.valueOf(suatChieu.getThoiGian()));
            stmt.setDate(3, Date.valueOf(suatChieu.getNgay()));
            stmt.setString(4, suatChieu.getMaPhim());
            stmt.setString(5, suatChieu.getMaPhong());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean updateSuatChieu(SuatChieu suatChieu) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "UPDATE SuatChieu SET thoiGian = ?, ngay = ?, maPhim = ?, maPhong = ? WHERE maSuatChieu = ?";
            stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(suatChieu.getThoiGian()));
            stmt.setDate(2, Date.valueOf(suatChieu.getNgay()));
            stmt.setString(3, suatChieu.getMaPhim());
            stmt.setString(4, suatChieu.getMaPhong());
            stmt.setString(5, suatChieu.getMaSuat());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean deleteSuatChieu(String maSuat) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            String sql = "DELETE FROM SuatChieu WHERE maSuatChieu = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maSuat);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public SuatChieu getSuatChieuByMaSuat(String maSuat) {
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SuatChieu suatChieu = null;

        try {
            String sql = "SELECT * FROM SuatChieu WHERE maSuatChieu = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maSuat);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("maSuatChieu");
                LocalDateTime thoiGian = rs.getTimestamp("thoiGian").toLocalDateTime();
                LocalDate ngay = rs.getDate("ngay").toLocalDate();
                String maPhim = rs.getString("maPhim");
                String maPhong = rs.getString("maPhong");

                suatChieu = new SuatChieu(ma, thoiGian, ngay, maPhim, maPhong);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suatChieu;
    }

    public List<SuatChieu> getAllSuatChieu() {
        List<SuatChieu> ds = new ArrayList<SuatChieu>();

        try {
            String sql = "SELECT * FROM SuatChieu";
            Connection con = Database.getInstance().getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                String ma = rs.getString("maSuatChieu");
                LocalDateTime thoiGian = rs.getTimestamp("thoiGian").toLocalDateTime();
                LocalDate ngay = rs.getDate("ngay").toLocalDate();
                String maPhim = rs.getString("maPhim");
                String maPhong = rs.getString("maPhong");

                SuatChieu suatChieu = new SuatChieu(ma, thoiGian, ngay, maPhim, maPhong);
                ds.add(suatChieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<SuatChieu> getSuatChieuTheoPhim(String maPhim) {
        List<SuatChieu> ds = new ArrayList<SuatChieu>();
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM SuatChieu WHERE maPhim = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhim);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SuatChieu sc = new SuatChieu(
                        rs.getString("maSuatChieu"),
                        rs.getTimestamp("thoiGian").toLocalDateTime(),
                        rs.getDate("ngay").toLocalDate(),
                        rs.getString("maPhim"),
                        rs.getString("maPhong")
                );
                ds.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<SuatChieu> getSuatChieuTheoPhong(String maPhong) {
        List<SuatChieu> ds = new ArrayList<SuatChieu>();
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM SuatChieu WHERE maPhong = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhong);
            rs = stmt.executeQuery();
            while (rs.next()) {
                SuatChieu sc = new SuatChieu(
                        rs.getString("maSuatChieu"),
                        rs.getTimestamp("thoiGian").toLocalDateTime(),
                        rs.getDate("ngay").toLocalDate(),
                        rs.getString("maPhim"),
                        rs.getString("maPhong")
                );
                ds.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    public List<SuatChieu> getSuatChieuTheoPhim_Phong(String maPhim, String maPhong) {
        List<SuatChieu> ds = new ArrayList<SuatChieu>();
        Database.getInstance();
        Connection con = Database.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM SuatChieu WHERE maPhim = ? AND maPhong = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maPhim);
            stmt.setString(2, maPhong);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                SuatChieu sc = new SuatChieu(
                        rs.getString("maSuatChieu"),
                        rs.getTimestamp("thoiGian").toLocalDateTime(),
                        rs.getDate("ngay").toLocalDate(),
                        rs.getString("maPhim"),
                        rs.getString("maPhong")
                );
                ds.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }

}
