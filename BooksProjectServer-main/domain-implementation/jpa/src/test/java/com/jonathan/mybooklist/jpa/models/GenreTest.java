package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {
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
    void insertTest() {
        var entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            var genre = new  Genre();
            genre.setdescription("Proba");
            entityManager.persist(genre);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }
}