package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.jpa.models.Author;
import com.jonathan.mybooklist.jpa.models.Genre;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaAuthorRepositoryTest {
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
        Author a = new Author();
        a.setnom("test");
        a.setsurname("test");
        JpaAuthorRepository b = new JpaAuthorRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(a.getId()));
    }

    @Test
    void delete() {
        Author a = new Author();
        a.setId(1);
        JpaAuthorRepository b = new JpaAuthorRepository(entityManagerFactory);
        b.save(a);
        b.delete(a);
        assertNull(b.get(1));
    }

    @Test
    void get() {
        Author a = new Author();
        a.setnom("test");
        a.setsurname("test");
        JpaAuthorRepository b = new JpaAuthorRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(a.getId()));
    }

    @Test
    void getAll() {
        JpaAuthorRepository b = new JpaAuthorRepository(entityManagerFactory);
        var c = b.getAll();
        assertNotNull(c.getClass());
    }
}