package com.example.santaslist.model;

public class User {

    private int userID;
    private String email;
    private String userPassword;

    private String firstName;

    private String lastName;

    public User()
    {

    }
    public User(int userID, String email, String userPassword, String firstName, String lastName)
    {
        this.userID = userID;
        this.email = email;
        this.userPassword = userPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", userPassword=" + "******" +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }

}
