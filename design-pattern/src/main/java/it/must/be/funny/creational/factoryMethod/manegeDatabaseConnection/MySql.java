package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql implements Database {

    private static MySql mySql;

    private Connection connection;
    private String userName = "username";
    private String password = "password";
    private String jdbcUrl = "url";

    private MySql() throws ClassNotFoundException,
            SQLException {
        Class.forName("com.myql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, userName, password);
    }

    public static Database getInstance() throws SQLException,
            ClassNotFoundException {
        if (mySql == null){
            return new MySql();
        }
        return mySql;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }
}
