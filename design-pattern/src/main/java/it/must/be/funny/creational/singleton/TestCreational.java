package it.must.be.funny.creational.singleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCreational {
    public static void main(String[] args) throws SQLException {
        //mẫu design pattern
        Singleton singleton = Singleton.getInstance();
        singleton.otherFunc();

        // quan lý kết nối đến database
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        Connection connection =  databaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM users";
        statement.executeQuery(sql);
    }
}
