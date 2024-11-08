package com.fcardara.dbtestutils.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.fcardara.dbtestutils.exceptions.ScriptExecutorException;

public class DbUtils {
    private static Connection connectToServer(Path connectionProperties) {
        var properties = new Properties();
            try {
                InputStream input = ScriptExecutor.class.getClassLoader()
                        .getResourceAsStream(connectionProperties.toString());
                properties.load(input);

                return connect(getServerConnectionString(properties), properties.getProperty("user"), properties.getProperty("password"));
            } catch (IOException e) {
                throw new ScriptExecutorException(e);
            }
    }
    
    private static Connection connectToDb(Path connectionProperties) {
        var properties = new Properties();
            try {
                InputStream input = ScriptExecutor.class.getClassLoader()
                        .getResourceAsStream(connectionProperties.toString());
                properties.load(input);

                return connect(getDatabaseConnectionString(properties), properties.getProperty("user"), properties.getProperty("password"));
            } catch (IOException e) {
                throw new ScriptExecutorException(e);
            }
    }
    private static Connection connect(String url, String user, String password) {
        try {  
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new ScriptExecutorException(e);
        }
    }

    public static Connection connectToServer() {
        return connectToServer(Path.of("datasource.properties"));
    }

    public static Connection connectToDb() {
        return connectToDb(Path.of("datasource.properties"));
    }

    private static String getServerConnectionString(Properties properties) {
        return String.format("%s:%s://%s",
            properties.getProperty("protocol"),
            properties.getProperty("subprotocol"),
            properties.getProperty("url"));
    }
    
    private static String getDatabaseConnectionString(Properties properties) {
        return String.format("%s:%s://%s/%s",
            properties.getProperty("protocol"),
            properties.getProperty("subprotocol"),
            properties.getProperty("url"),
            properties.getProperty("database"));
    }
}
