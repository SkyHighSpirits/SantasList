package com.example.santaslist.repository;

import com.example.santaslist.model.User;
import com.example.santaslist.model.Wish;
import com.example.santaslist.utility.DatabaseConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {


    @Value("${spring.datasource.url}")
    private String DB_URL;// = "jdbc:mysql://santalistdatabase.mysql.database.azure.com:3306/santalistdb";

    @Value("${spring.datasource.username}")
    private String UID;// = "santaroot";

    @Value("${spring.datasource.password}")
    private String PWD;// = "#Danmark2300";
    
    
    DatabaseConnector database;

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        try{
            // Connect to database
            //Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            database = DatabaseConnector.getInstance(DB_URL, UID, PWD);
            Connection connection = database.getConnection();
            
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
    public void addUser(User user){
        try{
            // Connect to database
            //Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            database = DatabaseConnector.getInstance(DB_URL, UID, PWD);
            Connection connection = database.getConnection();
            
            final String CREATE_QUERY ="INSERT INTO users(email, userPassword, firstName, lastName) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            preparedStatement.executeUpdate();

        } catch(SQLException e){
            System.out.println("Could not create user");
            e.printStackTrace();

        }
    }

}
