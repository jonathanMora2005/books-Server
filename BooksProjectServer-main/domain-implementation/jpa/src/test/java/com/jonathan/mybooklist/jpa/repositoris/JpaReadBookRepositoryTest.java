package com.jonathan.mybooklist.jpa.repositoris;
import com.jonathan.mybooklist.models.ReadBook;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.Date;

class JpaReadBookRepositoryTest {
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
        ReadBook a = new com.jonathan.mybooklist.jpa.models.ReadBook();
        a.setpublicationDate(Date.valueOf("2023-01-15"));
        a.setdescription("Proba");
        a.setgenreCode(1);
        a.setname("Proba");
        a.setpages(50);
        a.setauthorId(2);
        a.publishingId(1);
        JpaReadBookRepository b = new JpaReadBookRepository(entityManagerFactory);
        b.save(a);
        //assertNotNull(b.get(a.getauthorId()));
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }


}