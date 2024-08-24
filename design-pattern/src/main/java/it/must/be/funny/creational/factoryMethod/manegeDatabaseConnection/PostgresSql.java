package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSql implements Database {

    private static PostgresSql postgresSql;
    private Connection connection;
    private String username = "username";
    private String password = "password";
    private String jdbcUrl = "url";


    private PostgresSql() throws ClassNotFoundException,
            SQLException {
        Class.forName("com.postgres.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcUrl, username, password);
    }

    public static Database getInstance() throws SQLException,
            ClassNotFoundException {
        if (postgresSql == null){
            return new PostgresSql();
        }
        return null;
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return connection;
    }
}
