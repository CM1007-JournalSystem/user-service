CREATE DATABASE IF NOT EXISTS user-service;
USE user-service;

CREATE TABLE Users (
                        user_id INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        social_security VARCHAR(50) NOT NULL,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        gender VARCHAR(50) NOT NULL,
                        pass VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL ,
                        phoneNr VARCHAR(100),
                        user_role VARCHAR(50) DEFAULT ("PATIENT"),
);
