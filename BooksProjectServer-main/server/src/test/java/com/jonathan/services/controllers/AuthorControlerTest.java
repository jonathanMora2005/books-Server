package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ModelFactory;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AuthorControlerTest {

    private AuthorControler authorControler;

    @BeforeEach
    void setUp() throws SQLException {
        RepositoryFactory repositoryFactory = new JdbcRepositoryFactory();
        ModelFactory modelFactory = new JdbcModelFactory();
        authorControler = new AuthorControler(repositoryFactory, modelFactory);

    }
    @Test
    void get() {
        authorControler.put(1,"Updated/Updated");
        String result = authorControler.get(1);
        String expectedJson = "{\"id\":1,\"name\":\"Updated\"\"surname\":\"Updated\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void testGetAll() {
        assertNotNull( authorControler.get());

    }

    @Test
    void post() {
        authorControler.post("john/Doe");
        String result = authorControler.get(8);
        String expectedJson = "{\"id\":8,\"name\":\"john\"\"surname\":\"Doe\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void put() {
        authorControler.put(1,"Updated/Updated");
        String result = authorControler.get(1);
        String expectedJson = "{\"id\":1,\"name\":\"Updated\"\"surname\":\"Updated\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void delete() {
    }
}
