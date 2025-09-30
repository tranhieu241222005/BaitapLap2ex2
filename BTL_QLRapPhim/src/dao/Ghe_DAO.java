package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import connectDB.Database;
import entity.Ghe;
import entity.NhanVien;

public class Ghe_DAO {

    public Ghe_DAO() {}

    public List<Ghe> getAllGheTheoSuatChieu(String maSuatChieu) {
        List<Ghe> dsGhe = new ArrayList<>();
        String sql = "SELECT * FROM Ghe WHERE maSuatChieu = ?";

        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maSuatChieu);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maGhe = rs.getString("maGhe");
                Ghe ghe = new Ghe(maGhe, maSuatChieu);
                dsGhe.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGhe;
    }
    public boolean addGhe(Ghe ghe) {
        String sql = "INSERT INTO Ghe(maGhe, maSuatChieu) VALUES(?, ?)";
        
        try (Connection con = Database.getInstance().getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, ghe.getMaGhe());
            stmt.setString(2, ghe.getMaSuatChieu());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean xoaGhe(Ghe ghe) {
        String sql = "DELETE FROM Ghe WHERE maGhe = ? AND maSuatChieu = ?";
        try (Connection con = Database.getInstance().getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {

               stmt.setString(1, ghe.getMaGhe());
               stmt.setString(2, ghe.getMaSuatChieu());

               int rowsInserted = stmt.executeUpdate();
               return rowsInserted > 0;
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return false;
        // Thực hiện xóa
    }
}
