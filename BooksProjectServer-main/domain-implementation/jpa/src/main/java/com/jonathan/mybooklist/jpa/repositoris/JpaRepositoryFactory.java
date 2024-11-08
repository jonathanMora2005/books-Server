package com.jonathan.mybooklist.jpa.repositoris;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.repositories.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class JpaRepositoryFactory implements RepositoryFactory {
    private EntityManagerFactory entityManagerFactory ;

    @Override
    public AuthorRepository getAuthorRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");
        return new JpaAuthorRepository(entityManagerFactory);
    }

    @Override
    public GenreRepository getGenreRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");

        return new JpaGenreRepository(entityManagerFactory);
    }

    @Override
    public BookreadRepository getReadRepositori() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");

        return new JpaReadBookRepository(entityManagerFactory);
    }

    @Override
    public PublishingRepository getPublishingRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");

        return new JpaPublishingRepository(entityManagerFactory);
    }

    @Override
    public PersonalinformationRepository getPersonalInformationRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("books-jpa");

        return new JpaPersonalInformationRepository(entityManagerFactory);
    }
}
