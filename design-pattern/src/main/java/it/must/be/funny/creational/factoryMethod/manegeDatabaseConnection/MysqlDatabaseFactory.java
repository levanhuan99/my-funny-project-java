package it.must.be.funny.creational.factoryMethod.manegeDatabaseConnection;

import java.sql.SQLException;

public class MysqlDatabaseFactory extends DatabaseFactory{

    @Override
    public Database createDatabase() throws SQLException,
            ClassNotFoundException {
        return MySql.getInstance();
    }
}
