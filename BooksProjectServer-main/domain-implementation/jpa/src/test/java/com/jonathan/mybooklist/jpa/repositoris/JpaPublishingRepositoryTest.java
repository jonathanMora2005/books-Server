package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.jpa.models.Publshing;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaPublishingRepositoryTest {

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
        Publshing a = new com.jonathan.mybooklist.jpa.models.Publshing();
        a.setcountry("MANLLEU");
        a.setemail("proba@gmail.com");
        a.setname("proba");
        JpaPublishingRepository b = new JpaPublishingRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(0));
    }
    @Test
    void delete() {
        Publshing a = new com.jonathan.mybooklist.jpa.models.Publshing();
        a.setpublishingid(1);
        JpaPublishingRepository b = new JpaPublishingRepository(entityManagerFactory);
        b.save(a);
        b.delete(a);
        assertNull(b.get(1));
    }
    @Test
    void get() {
        JpaPublishingRepository b = new JpaPublishingRepository(entityManagerFactory);
        assertNotNull(b.get(0));
    }
    @Test
    void getAll() {
        JpaPublishingRepository b = new JpaPublishingRepository(entityManagerFactory);
        assertNotNull(b.getAll());
    }
}