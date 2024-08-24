package it.must.be.funny.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private  String jdbc ="";
    private  String username = "";
    private  String password = "";

    private static DatabaseConnection databaseConnection;

    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            // Đăng ký driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tạo kết nối
            connection = DriverManager.getConnection(jdbc, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (databaseConnection == null){
            return new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection(){
        return this.connection;
    }


}
