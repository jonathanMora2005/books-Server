package com.uvic.teknos.book.repositorys;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import com.uvic.teknos.book.models.Author;
import com.uvic.teknos.book.models.Genre;
import com.uvic.teknos.book.models.Publshing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
class JdbcPublshingRepositoryTest {
    Connection connection;
    public JdbcPublshingRepositoryTest(Connection connection) {
        this.connection = connection;
    }
    @Test
    void save() {
            Publshing proba = new Publshing();
            proba.setpublishingid(1);
            proba.setname("dsfsfsf");
            proba.setemail("euyhl");
            proba.setcountry("Duyoi");
            var repository = new JdbcPublshingRepository(connection);
            repository.save(proba);
            assertTrue(proba.getpublishingid() > 0);

    }
    @Test
    void delete() {
            Publshing proba = new Publshing();
            proba.setpublishingid(2);
            var repository = new JdbcPublshingRepository(connection);
            repository.delete(proba);
            assertTrue(proba.getpublishingid() == 0);

    }
    @Test
    void get() {
        var repository = new JdbcPublshingRepository(connection);
        assertNotNull(repository.get(1));
    }
    @Test
    void getAll() {
        var repository = new JdbcPublshingRepository(connection);
        assertNotNull(repository.getAll());

    }

}