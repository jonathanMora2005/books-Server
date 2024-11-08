package com.uvic.teknos.book.repositorys;

import com.fcardara.dbtestutils.junit.CreateSchemaExtension;
import com.fcardara.dbtestutils.junit.GetConnectionExtension;
import com.jonathan.mybooklist.models.PersonalInformation;
import com.uvic.teknos.book.models.Author;
import com.uvic.teknos.book.models.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
class JdbcPersonalInformationRepositoryTest {
    Connection connection;
    public JdbcPersonalInformationRepositoryTest(Connection connection) {
        this.connection = connection;
    }


    @Test
    void save() {
            PersonalInformation proba = new com.uvic.teknos.book.models.PersonalInformation();
            proba.setDNI("E128121821");
            Author b = new Author();
            b.setId(3);
            proba.setAuthorId(b);
            proba.setNationality("iufdodfgfdg");
            proba.setDateOfBirth(Date.valueOf("2023-05-10"));
            proba.setPhone(2373);
            proba.setAddress("gfdsjksdfsdf");
            var repository = new JdbcPersonalInformationRepository(connection);
            repository.save(proba);


    }
    @Test
    void delete() {
            PersonalInformation proba = new com.uvic.teknos.book.models.PersonalInformation();
            proba.setDNI("123456789A");
            var repository = new JdbcPersonalInformationRepository(connection);
            repository.delete(proba);
            assertTrue(proba.getDNI() == "");

    }
    @Test
    void get() {
        /*
        var repository = new JdbcPersonalInformationRepository(connection);
        assertNotNull(repository.get(3));

         */
    }

}