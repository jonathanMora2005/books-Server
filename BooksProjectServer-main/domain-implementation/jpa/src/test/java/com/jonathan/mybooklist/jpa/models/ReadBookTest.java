package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.sql.Date;

public class ReadBookTest {

    private static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void setUpBeforeClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");
    }

    @AfterAll
    static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @Test
    void insertTest() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Crear una instancia de ReadBook
            ReadBook readBook = new ReadBook();
            readBook.setname("Nombre del libro");
            readBook.setpublicationDate(new Date(System.currentTimeMillis())); // Fecha actual
            readBook.setpages(200);
            readBook.setdescription("Descripción del libro");
            readBook.setauthorId(1); // ID del autor
            readBook.publishingId(1); // ID de la editorial
            readBook.setgenreCode(1); // Código del género

            // Persistir la entidad
            entityManager.persist(readBook);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
