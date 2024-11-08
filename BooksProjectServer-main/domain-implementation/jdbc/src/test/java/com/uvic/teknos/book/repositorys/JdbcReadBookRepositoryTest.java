package com.uvic.teknos.book.repositorys;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import com.uvic.teknos.book.models.Publshing;
import com.uvic.teknos.book.models.ReadBook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})

class JdbcReadBookRepositoryTest {
    Connection connection;
    public JdbcReadBookRepositoryTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    void save() {
            ReadBook proba = new ReadBook();
            proba.setidBookRead(1);
            proba.setname("dsfsfsf");
            proba.setpublicationDate(Date.valueOf("2023-05-10"));
            proba.setpages(1000);
            proba.setdescription("uihfdsuhfdsfudsfysdfysfsufsdff");
            proba.setauthorId(1);
            proba.publishingId(2);
            proba.setgenreCode(1);
            var repository = new JdbcReadBookRepository(connection);
            repository.save(proba);
            assertTrue(proba.getauthorId() > 0);

    }
}