package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.repositories.GenreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaGenreRepository implements GenreRepository {
    private final EntityManagerFactory entityManagerFactory;

    public JpaGenreRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void save(Genre model) {
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
    public void delete(Genre model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.Genre genre = entityManager.find(com.jonathan.mybooklist.jpa.models.Genre.class, model.getId());
            if (genre != null) {
                entityManager.remove(genre);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting genre", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Genre get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            com.jonathan.mybooklist.jpa.models.Genre genre = entityManager.find(com.jonathan.mybooklist.jpa.models.Genre.class, id);
            return genre;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<Genre> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g", Genre.class);
            List<Genre> genres = query.getResultList();
            return new HashSet<>(genres);
        } finally {
            entityManager.close();
        }

    }

    }



