package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.Publishing;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookreadRepository;
import com.jonathan.mybooklist.repositories.PublishingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaReadBookRepository implements BookreadRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaReadBookRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(ReadBook model) {
        var entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(model);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(ReadBook model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.ReadBook author = entityManager.find(com.jonathan.mybooklist.jpa.models.ReadBook.class, model.getidBookRead());
            if (author != null) {
                entityManager.remove(author);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting ReadBook", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public ReadBook get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            com.jonathan.mybooklist.jpa.models.ReadBook readBook = entityManager.find(com.jonathan.mybooklist.jpa.models.ReadBook.class, id);
            return readBook;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<ReadBook> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ReadBook> query = entityManager.createQuery("SELECT g FROM ReadBook g", ReadBook.class);
            List<ReadBook> readBooks = query.getResultList();
            return new HashSet<>(readBooks);
        } finally {
            entityManager.close();
        }
    }
}
