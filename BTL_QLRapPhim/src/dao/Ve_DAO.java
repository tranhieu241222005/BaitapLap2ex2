package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import connectDB.Database;
import entity.VeXemPhim;

public class Ve_DAO {

    public Ve_DAO() {}

    public boolean themVe(VeXemPhim ve) {
        String sql = "INSERT INTO VeXemPhim(maVe, giaVe, maPhim, maSuatChieu, maGhe, ngayDat) "
                   + "VALUES(?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, ve.getMaVe());
            stmt.setDouble(2, ve.getGiaVe());
            stmt.setString(3, ve.getMaPhim());
            stmt.setString(4, ve.getMaSuatChieu());
            stmt.setString(5, ve.getMaGhe());
            stmt.setTimestamp(6, Timestamp.valueOf(ve.getNgayDat()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaVe(String maVe) {
        String sql = "DELETE FROM VeXemPhim WHERE maVe = ?";
        
        try (Connection con = Database.getInstance().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maVe);
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<VeXemPhim> layTatCaVe() {
        List<VeXemPhim> dsVe = new ArrayList<>();
        String sql = "SELECT * FROM VeXemPhim";

        try (Connection con = Database.getInstance().getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String maPhim = rs.getString("maPhim");
                String maSuatChieu = rs.getString("maSuatChieu");
                String maGhe = rs.getString("maGhe");
                
                VeXemPhim ve = new VeXemPhim(maPhim, maSuatChieu, maGhe);
                
                ve.setNgayDat(rs.getTimestamp("ngayDat").toLocalDateTime());
                
                dsVe.add(ve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsVe;
    }

}