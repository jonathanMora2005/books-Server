package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaPersonalInformationRepository implements PersonalinformationRepository {
    private final EntityManagerFactory entityManagerFactory;
    public JpaPersonalInformationRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }
    @Override
    public void save(PersonalInformation model) {
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
    public void delete(PersonalInformation model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            com.jonathan.mybooklist.jpa.models.PersonalInformation personalInformation = entityManager.find(com.jonathan.mybooklist.jpa.models.PersonalInformation.class, "tutyututy");
            entityManager.remove(personalInformation);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error deleting PersonalInformation", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public PersonalInformation get(Integer id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT p FROM PersonalInformation p WHERE p.author.id = :id";
            return entityManager.createQuery(jpql, PersonalInformation.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<PersonalInformation> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<PersonalInformation> query = entityManager.createQuery("SELECT g FROM PersonalInformation g", PersonalInformation.class);
            List<PersonalInformation> personalInformations = query.getResultList();
            return new HashSet<>(personalInformations);
        } finally {
            entityManager.close();
        }
    }
}
