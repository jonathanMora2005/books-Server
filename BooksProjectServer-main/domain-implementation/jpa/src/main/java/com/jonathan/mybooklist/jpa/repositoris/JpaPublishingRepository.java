package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.Publishing;
import com.jonathan.mybooklist.repositories.PublishingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaPublishingRepository implements PublishingRepository {
    private final EntityManagerFactory entityManagerFactory;
    public JpaPublishingRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void save(Publishing model) {
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
    public void delete(Publishing model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.Publshing publshing = entityManager.find(com.jonathan.mybooklist.jpa.models.Publshing.class, model.getpublishingid());
            if (publshing != null) {
                entityManager.remove(publshing);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting publshing", e);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public Publishing get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            com.jonathan.mybooklist.jpa.models.Publshing publshing =  entityManager.find(com.jonathan.mybooklist.jpa.models.Publshing.class, id);
            return publshing;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<Publishing> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Publishing> query = entityManager.createQuery("SELECT g FROM Publshing g", Publishing.class);
            List<Publishing> publishings = query.getResultList();
            return new HashSet<>(publishings);
        } finally {
            entityManager.close();
        }
    }
}
