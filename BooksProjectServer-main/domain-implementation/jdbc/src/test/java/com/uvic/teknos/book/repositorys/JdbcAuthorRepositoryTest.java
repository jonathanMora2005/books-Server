package com.uvic.teknos.book.repositorys;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import com.uvic.teknos.book.models.Author;
import com.uvic.teknos.book.models.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})

class JdbcAuthorRepositoryTest {
    Connection connection;
    public JdbcAuthorRepositoryTest(Connection connection) throws SQLException {
        this.connection =connection;
    }
    @Test
    void delete() {
        try  {
            Author proba = new Author();
            proba.setId(1);
            var repository = new JdbcAuthorRepository(connection);
            repository.delete(proba);
            assertTrue(proba.getId() == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void save() {
        try {
            Author proba = new Author();
            proba.setId(0);
            proba.setnom("dsfsfsf");
            proba.setsurname("euyhl");
            var repository = new JdbcAuthorRepository(connection);
            repository.save(proba);
            assertTrue(proba.getId() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void get() {
        var repository = new JdbcAuthorRepository(connection);
        assertNotNull(repository.get(2));
    }
    @Test
    void getAll() {
        var repository = new JdbcAuthorRepository(connection);
        assertNotNull(repository.getAll());

    }
}