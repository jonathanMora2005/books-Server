package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ModelFactory;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ReadBookControlerTest {

    private ReadBookControler readBookControler;


    @BeforeEach
    void setUp() throws SQLException {
        RepositoryFactory repositoryFactory = new JdbcRepositoryFactory();
        ModelFactory modelFactory = new JdbcModelFactory();
        readBookControler = new ReadBookControler(repositoryFactory, modelFactory);

    }

    @Test
    void get() {
        String result = readBookControler.get(1);
        String expectedJson = "{\"pages\":\"350\"\"authorID\":\"1\"\"description\":\"A gripping science fiction adventure\"\"name\":\"The Quantum Paradox\"}";
        assertEquals(expectedJson, result);
    }

    @Test
    void testGet() {
        assertNotNull( readBookControler.get());

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