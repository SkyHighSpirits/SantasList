DROP database IF exists santalistdb;
CREATE DATABASE santalistdb;
USE santalistdb;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS wishes;

CREATE TABLE users (
                       userID INT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       userPassword varchar(255) NOT NULL,
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL
);

CREATE TABLE wishes (
                        wishID INT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        userID INT NOT NULL,
                        wishName VARCHAR(40) NOT NULL,
                        price FLOAT(255, 2) NOT NULL,
                        priority INT,
                        wishDescription VARCHAR(512),
                        url VARCHAR(512),
                        reserved BOOL,
                        FOREIGN KEY(userID) REFERENCES users(userID)
);

