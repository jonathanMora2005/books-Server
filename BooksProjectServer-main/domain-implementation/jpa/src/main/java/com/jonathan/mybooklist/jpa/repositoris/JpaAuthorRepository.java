package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.repositories.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaAuthorRepository implements AuthorRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaAuthorRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Author model) {
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
    public void delete(Author model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.Author author = entityManager.find(com.jonathan.mybooklist.jpa.models.Author.class, model.getId());
            if (author != null) {
                entityManager.remove(author);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting Author", e);
        } finally {
            entityManager.close();
        }

    }
    @Override
    public Author get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            com.jonathan.mybooklist.jpa.models.Author author =  entityManager.find(com.jonathan.mybooklist.jpa.models.Author.class, id);
            return author;
        } finally {
            entityManager.close();
        }
    }
    @Override
    public Set<Author> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Author> query = entityManager.createQuery("SELECT g FROM Author g", Author.class);
            List<Author> authors = query.getResultList();
            return new HashSet<>(authors);
        } finally {
            entityManager.close();
        }
    }
}
