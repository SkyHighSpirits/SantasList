package com.example.santaslist.repository;

import com.example.santaslist.model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository
{

    @Value("${spring.datasource.url}")
    private String DB_URL;// = "jdbc:mysql://santalistdatabase.mysql.database.azure.com:3306/santalistdb";
    @Value("${spring.datasource.username}")
    private String UID;// = "santaroot";
    @Value("${spring.datasource.password}")
    private String PWD;// = "#danmark2300";

    public List<Wish> getAll(){
        List<Wish> wishes = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM wishes";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);
            while (resultSet.next()){
                int wishID = resultSet.getInt(1);
                int userID = resultSet.getInt(2);
                String wishName = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int priority = resultSet.getInt(5);
                String wishDescription = resultSet.getString(6);
                String url = resultSet.getString(7);
                boolean reserved = resultSet.getBoolean(8);
                Wish wish = new Wish(wishID, userID, wishName, price, priority, wishDescription, url, reserved);
                wishes.add(wish);
                System.out.println(wish);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return wishes;
    }
}
