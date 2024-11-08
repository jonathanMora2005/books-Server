package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.PendingBook;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookpendingRepository;
import com.jonathan.mybooklist.repositories.BookreadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaPendingBookRepository implements BookpendingRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaPendingBookRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(PendingBook model) {
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
    public void delete(PendingBook model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.PeandingBook peandingBook = entityManager.find(com.jonathan.mybooklist.jpa.models.PeandingBook.class, model.getidBookPending());
            if (peandingBook != null) {
                entityManager.remove(peandingBook);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting PendingBook", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public PendingBook get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            com.jonathan.mybooklist.jpa.models.PeandingBook peandingBook = entityManager.find(com.jonathan.mybooklist.jpa.models.PeandingBook.class, id);
            return peandingBook;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<PendingBook> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<PendingBook> query = entityManager.createQuery("SELECT g FROM PeandingBook g", PendingBook.class);
            List<PendingBook> pendingBooks = query.getResultList();
            return new HashSet<>(pendingBooks);
        } finally {
            entityManager.close();
        }
    }
}
