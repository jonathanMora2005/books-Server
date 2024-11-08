package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ModelFactory;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PublishingControlerTest {

    private PublishingControler publishingControler;

    @BeforeEach
    void setUp() throws SQLException {
        RepositoryFactory repositoryFactory = new JdbcRepositoryFactory();
        ModelFactory modelFactory = new JdbcModelFactory();
        publishingControler = new PublishingControler(repositoryFactory, modelFactory);

    }

    @Test
    void get() {
        String result = publishingControler.get(1);
        String expectedJson = "{\"name\":\"Penguin Books\"\"country\":\"United Kingdom\"\"email\":\"info@penguinbooks.com\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void testGet() {
        assertNotNull( publishingControler.get());

    }

    @Test
    void post() {
    }

    @Test
    void put() {
    }

    @Test
    void delete() {

    }
}