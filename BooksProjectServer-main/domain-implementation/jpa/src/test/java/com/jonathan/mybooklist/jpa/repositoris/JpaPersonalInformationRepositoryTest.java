package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.jpa.models.Author;
import com.jonathan.mybooklist.models.PersonalInformation;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaPersonalInformationRepositoryTest {

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
        PersonalInformation a = new com.jonathan.mybooklist.jpa.models.PersonalInformation();
        a.setDNI("12fgcc");
        a.setAddress("adreza proba");
        a.setNationality("nacionalitat proba");
        a.setPhone(2454545);
        var c = new Author() ;
        c.setId(2);
        a.setAuthorId(c);
        JpaPersonalInformationRepository b = new JpaPersonalInformationRepository(entityManagerFactory);
        b.save(a);
        assertNotNull(b.get(2));




    }

  /*  @Test
    void delete() {
        PersonalInformation a = new com.jonathan.mybooklist.jpa.models.PersonalInformation();
        a.setDNI("tutyututy");
        a.setAddress("adreza proba");
        a.setNationality("nacionalitat proba");
        a.setPhone(2454545);
        a.setAuthorId(3);
        JpaPersonalInformationRepository b = new JpaPersonalInformationRepository(entityManagerFactory);
        b.save(a);
        b.delete(a);
        assertNull(b.get(3));

    }
*/
    @Test
    void get() {
        JpaPersonalInformationRepository b = new JpaPersonalInformationRepository(entityManagerFactory);
        assertNotNull(b.get(2));
    }

    @Test
    void getAll() {
        JpaPersonalInformationRepository b = new JpaPersonalInformationRepository(entityManagerFactory);
        var c = b.getAll();
        assertNotNull(c.getClass());
    }
}