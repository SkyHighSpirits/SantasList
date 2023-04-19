package com.example.santaslist.utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector databaseConnector;


    String DB_URL; //= "jdbc:mysql://santalistdatabase.mysql.database.azure.com:3306/santalistdb";
    String UID; //= "santaroot";
    String PWD; //= "#Danmark2300";

    private DatabaseConnector(String DB_URL, String UID, String PWD){
        this.DB_URL = DB_URL;
        this.UID = UID;
        this.PWD = PWD;
    }

    public static DatabaseConnector getInstance(String DB_URL, String UID, String PWD){
        if(databaseConnector == null){
            databaseConnector = new DatabaseConnector(DB_URL, UID, PWD);
        }

        return databaseConnector;
    }
    
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, UID, PWD);
        return conn;
    }

    
    
    
}
