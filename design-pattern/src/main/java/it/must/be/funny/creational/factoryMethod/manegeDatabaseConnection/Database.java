package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {
//    public Database getDatabase();
//    public  Database getInstance();
//    public void connect();

//    Database getInstance(String jdbcUrl, String username, String password) throws SQLException, ClassNotFoundException;

    public Connection getConnection() throws SQLException, ClassNotFoundException;
}
