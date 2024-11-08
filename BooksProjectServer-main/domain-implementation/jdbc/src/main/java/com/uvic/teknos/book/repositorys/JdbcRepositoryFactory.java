package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.repositories.*;
import com.jonathan.mybooklist.RepositoryFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcRepositoryFactory implements RepositoryFactory {
    private Connection connection ;

    public JdbcRepositoryFactory() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/MY_BOOK_LIST"; // updated port
        String user = "root";  // MySQL username
        String password = "Teknos01.";  // MySQL password
        try {
            // Establishing the connection

             connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");

            // You can now interact with the database (execute queries, etc.)

        } catch (SQLException e) {
            // Handling SQL errors

            e.printStackTrace();
        }
       /* var properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("datasource.properties"));
            connection = DriverManager.getConnection(String.format("%s:%s://%s/%s",
                    properties.getProperty("protocol"),
                    properties.getProperty("subprotocol"),
                    properties.getProperty("url"),
                    properties.getProperty("database")), properties.getProperty("user"), properties.getProperty("password"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public AuthorRepository getAuthorRepository() {
        return new JdbcAuthorRepository(connection);
    }

    @Override
    public GenreRepository getGenreRepository() {
        return new JdbcGenreRepository(connection);
    }

    @Override
    public BookreadRepository getReadRepositori() {
        return new JdbcReadBookRepository(connection);
    }

    @Override
    public PublishingRepository getPublishingRepository() {
        return new JdbcPublshingRepository(connection);
    }

    @Override
    public PersonalinformationRepository getPersonalInformationRepository() {
        return new JdbcPersonalInformationRepository(connection);
    }

}
