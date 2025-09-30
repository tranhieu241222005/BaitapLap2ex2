package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection con = null;
    private static Database instance = new Database();
    
    private Database() {} // constructor private

    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public void connect() {
        String sql = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyRapPhim";
        String user = "sa";
        String password = "sapassword";
        try {
            con = DriverManager.getConnection(sql, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
