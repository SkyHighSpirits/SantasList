package com.example.santaslist.repository;

import com.example.santaslist.model.Wish;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.nimbus.State;
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
    private String PWD;// = "#Danmark2300";

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

    public void addWish(Wish wish) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO wishes(userID, wishName, price, priority, wishDescription, url, reserved) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setInt(1, wish.getUserID());
            preparedStatement.setString(2, wish.getWishName());
            preparedStatement.setFloat(3, wish.getPrice());
            preparedStatement.setInt(4, wish.getPriority());
            preparedStatement.setString(5, wish.getWishDescription());
            preparedStatement.setString(6, wish.getUrl());
            preparedStatement.setBoolean(7, wish.isReserved());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("Could not create a wish");
            e.printStackTrace();
        }
    }

    public void deleteWish(Wish wish){
    try{
        Connection connection = DriverManager.getConnection(DB_URL, UID,PWD);
        final String DELETE_QUERY = "DELETE FROM wishes WHERE wishID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

        preparedStatement.setInt(1, wish.getWishID());

        preparedStatement.executeUpdate();
    }catch(SQLException e){
        System.out.println("Could not Delete wish");
        e.printStackTrace();
    }
    }
}
