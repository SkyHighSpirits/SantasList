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

/* TEST DATA FOR USERS */
INSERT INTO users (userID, email, userPassword, firstName, lastName)
VALUES
    (1, 'mikkel@localhost.com', '123456', 'Mikkel', 'Mikkelsen'),
    (2, 'simon@localhost.com', '123456', 'Simon', 'Simonsen'),
    (3, 'peter@localhost.com', '123456', 'Peter', 'Parker');

/* TEST DATA FOR WISHES */
INSERT INTO wishes (UserID, wishName, price, priority, wishDescription, url, reserved)
VALUES
    (1, 'Beaver', 200, 4, 'I want a cute beaver', 'https://en.wikipedia.org/wiki/Beaver', true),
    (1, 'Goose', 200, 2, 'I want goose baby', 'https://en.wikipedia.org/wiki/Goose', false),
    (2, 'Justin Bieber', 10000, 5, 'I want Justin biber, cause he´s cute!', 'https://en.wikipedia.org/wiki/Justin_Bieber', true),
    (2, 'Rubber Duck', 500, 5, 'I need a coding buddy', 'https://en.wikipedia.org/wiki/Beaver', true),
    (3, 'Nuclea Submarine', 200000, 3, 'I need a missile platform', 'https://en.wikipedia.org/wiki/Nuclear_submarine', false),
    (3, 'Nuclear Missile', 100000, 4, 'I need nuclear deterence', 'https://www.lockheedmartin.com/en-us/products/icbm.html', true);

