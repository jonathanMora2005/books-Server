-- PHPMYADMIN SQL DUMP
-- VERSION 5.0.1
-- HTTPS://WWW.PHPMYADMIN.NET/
--
-- HOST: 127.0.0.1
-- GENERATION TIME: MAR 24, 2020 AT 06:55 PM
-- SERVER VERSION: 10.4.11-MARIADB
-- PHP VERSION: 7.4.3
DROP DATABASE IF EXISTS MY_BOOK_LIST;

CREATE DATABASE MY_BOOK_LIST;

USE MY_BOOK_LIST;



CREATE TABLE Author(
                       Author_id INT(10) AUTO_INCREMENT PRIMARY KEY,
                       Name VARCHAR(50),
                       Surname VARCHAR(50)
);

CREATE TABLE Personal_information(
                                     DNI VARCHAR(50)  PRIMARY KEY,
                                     Author_id INT UNIQUE,
                                     Nationality VARCHAR(50),
                                     Date_of_Birth VARCHAR(50),
                                     Phone INT(10),
                                     Address VARCHAR(50),
                                     FOREIGN KEY (Author_id) REFERENCES Author(Author_id)
);

CREATE TABLE Publishing(
                           Publishing_id INT(10) AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(50),
                           Email VARCHAR(50),
                           Country VARCHAR(50)
);


CREATE TABLE Genre(
                      Code INT(10) AUTO_INCREMENT PRIMARY KEY,
                      Descripcion VARCHAR(50)
);





















CREATE TABLE Book_read (
                           ID_book_read INT(10) AUTO_INCREMENT PRIMARY KEY,
                           Name VARCHAR(50),
                           Publication_date DATE,
                           Pages INT(10),
                           Description VARCHAR(50),
                           Author_id INT(10),
                           Publishing_id INT(10),
                           Genre_Code INT(10),
                           FOREIGN KEY (Author_id) REFERENCES Author(Author_id),
                           FOREIGN KEY (Publishing_id) REFERENCES Publishing(Publishing_id),
                           FOREIGN KEY (Genre_Code) REFERENCES Genre(Code)
);

CREATE TABLE Book_pending  (
                               ID_book_read INT(10) AUTO_INCREMENT PRIMARY KEY,
                               Name VARCHAR(50),
                               Publication_date DATE,
                               Pages INT(10),
                               Description VARCHAR(50),
                               Author_id INT(10),
                               Publishing_id INT(10),
                               Genre_Code INT(10),
                               FOREIGN KEY (Author_id) REFERENCES Author(Author_id),
                               FOREIGN KEY (Publishing_id) REFERENCES Publishing(Publishing_id),
                               FOREIGN KEY (Genre_Code) REFERENCES Genre(Code)
);



