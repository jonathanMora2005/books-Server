package com.uvic.teknos.book.repositorys;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import com.uvic.teknos.book.models.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})

class JdbcGenreRepositoryTest {
    Connection connection;
    public JdbcGenreRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    void get() {
        var repository = new JdbcGenreRepository(connection);
        assertNotNull(repository.get(1));
    }

    @Test
    @DisplayName("Given an exsisting genre whith modifield fields,when save,then Genre table is update")
    void save() {

            Genre proba = new Genre();
            proba.setid(1);
            proba.setdescription("dsfsfsf");
            var repository = new JdbcGenreRepository(connection);
            repository.save(proba);
            assertTrue(proba.getId() > 0);





    }

    @Test
    void getAll() {
        var repository = new JdbcGenreRepository(connection);
        assertNotNull(repository.getAll());

    }

    @Test
    void delete() {
            Genre proba = new Genre();
            proba.setid(4);
            var repository = new JdbcGenreRepository(connection);
            repository.delete(proba);
            assertTrue(proba.getId() == 0);

    }
}