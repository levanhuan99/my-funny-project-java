package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseFactory {

    public abstract Database createDatabase() throws SQLException, ClassNotFoundException;

    public static DatabaseFactory getFactory(String dbType) throws SQLException,
            ClassNotFoundException {
        switch (dbType){
            case "postgres":
                return new PostgreSQLDatabaseFactory();
            case "mysql":
                return new MysqlDatabaseFactory();
        }
//        Database database = createDatabase(url,username, password);
//        return database.getConnection(url, username, password);
    }
}
