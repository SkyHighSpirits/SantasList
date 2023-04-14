package com.example.santaslist.repository;

import com.example.santaslist.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Value("$spring.datasosurce.url")
    private String DB_URL;

    @Value("$spring.datasource.username")
    private String UID;

    @Value("$spring.datasource.password")
    private String PWD;

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        try{
            // Connect to database
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();

            // Create preparedStatement query
            final String SQL_QUERY = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            // Get data from database
            while(resultSet.next()){
                int userId = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);

                User user = new User (userId, email, userPassword, firstName, lastName);
                userList.add(user);
            }
        } catch(SQLException e) {
            System.out.println("Could not query database.");
            e.printStackTrace();

        }
        return userList;
    }

}
