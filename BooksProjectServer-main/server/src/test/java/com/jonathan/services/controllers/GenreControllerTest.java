package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ModelFactory;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenreControllerTest {

    private GenreControler genreController;

    @BeforeEach
    void setUp() throws SQLException {
        RepositoryFactory repositoryFactory = new JdbcRepositoryFactory();
        ModelFactory modelFactory = new JdbcModelFactory();
        genreController = new GenreControler(repositoryFactory, modelFactory);
    }

    @Test
    void testGetById() {
        genreController.put(2,"Updated Genre");
        String result = genreController.get(2);
        String expectedJson = "{\"id\":2,\"name\":\"Updated Genre\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void testGetAll() {
       assertNotNull( genreController.get());
    }

    @Test
    void testPost() {
        genreController.post("Horror");
        String result = genreController.get(8);
        String expectedJson = "{\"id\":8,\"name\":\"Horror\"}";
        assertEquals(expectedJson, result);

    }

    @Test
    void testPut() {
        genreController.put(2,"Updated Genre");
        String result = genreController.get(2);
        String expectedJson = "{\"id\":2,\"name\":\"Updated Genre\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void testDelete() {
    /*    genreController.delete(1);
        assertNull( genreController.get(2));

     */
    }
}
