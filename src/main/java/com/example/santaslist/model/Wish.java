package com.example.santaslist.model;

public class Wish {

    private int wishID;
    private int userID;
    private String wishName;
    private float price;
    private int priority;
    private String wishDescription;
    private String url;
    private boolean reserved;

    public Wish()
    {

    }
    public Wish(int wishID, int userID, String wishName, float price, int priority, String wishDescription, String url, boolean reserved) {
        this.wishID = wishID;
        this.userID = userID;
        this.wishName = wishName;
        this.price = price;
        this.priority = priority;
        this.wishDescription = wishDescription;
        this.url = url;
        this.reserved = reserved;
    }

    public int getWishID() {
        return wishID;
    }

    public void setWishID(int wishID) {
        this.wishID = wishID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }


    @Override
    public String toString() {
        return "Wish{" +
                "wishID=" + wishID +
                ", userID=" + userID +
                ", wishName='" + wishName + '\'' +
                ", price=" + price +
                ", priority=" + priority +
                ", wishDescription='" + wishDescription + '\'' +
                ", url='" + url + '\'' +
                ", reserved=" + reserved +
                '}';
    }
}
