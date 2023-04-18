package com.example.santaslist.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Scope("singleton")
public class DatabaseConnector {

    private static DatabaseConnector databaseConnector;

    @Value("${spring.datasource.url}")
    String DB_URL; //= "jdbc:mysql://santalistdatabase.mysql.database.azure.com:3306/santalistdb";

    @Value("${spring.datasource.username}")
    String UID; //= "santaroot";

    @Value("${spring.datasource.password}")
    String PWD; //= "#Danmark2300";

    private DatabaseConnector(){
        
    }

    public static DatabaseConnector getInstance(){
        if(databaseConnector == null){
            databaseConnector = new DatabaseConnector();
        }

        return databaseConnector;
    }
    
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, UID, PWD);
        return conn;
    }

    
    
    
}
