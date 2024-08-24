package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;

public class TestDatabaseFactory {
    public static void main(String[] args) {
        try {

            //thuc hien khoi tao ket noi den mysql va thuc hien truy van
            DatabaseFactory mysqlFactory = DatabaseFactory.getFactory("mysql");
            Database database =  mysqlFactory.createDatabase();
            Connection mysqlConnection = database.getConnection();
            mysqlConnection.createStatement().executeQuery("select * from users");


        }catch (Exception e){
            System.out.println("do something with exception");
        }
    }
}
