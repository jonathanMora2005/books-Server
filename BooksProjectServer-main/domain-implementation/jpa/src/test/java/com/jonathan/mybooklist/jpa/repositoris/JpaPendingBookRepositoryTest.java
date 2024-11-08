package com.jonathan.mybooklist.jpa.repositoris;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaPendingBookRepositoryTest {
    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");

    }
    @AfterAll
    static void tearDown() {
        entityManagerFactory.close();
    }

    @Test
    void save() {
    }
}