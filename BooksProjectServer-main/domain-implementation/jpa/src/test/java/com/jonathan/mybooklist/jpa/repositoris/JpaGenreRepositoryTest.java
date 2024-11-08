package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.jpa.models.Genre;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaGenreRepositoryTest {
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
        Genre a = new Genre();
        a.setdescription("test");
        JpaGenreRepository b = new JpaGenreRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(a.getId()));
    }

    @Test
    void delete() {
        Genre a = new Genre();
        a.setid(1);
        JpaGenreRepository b = new JpaGenreRepository(entityManagerFactory);
        b.save(a);
        b.delete(a);
        assertNull(b.get(1));
    }

    @Test
    void get() {
        Genre a = new Genre();
        a.setdescription("test");
        JpaGenreRepository b = new JpaGenreRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(a.getId()));
    }

    @Test
    void getAll() {
        JpaGenreRepository b = new JpaGenreRepository(entityManagerFactory);
        var c = b.getAll();
        assertNotNull(c.getClass());
    }
}