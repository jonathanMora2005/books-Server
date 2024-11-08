package com.jonathan.services.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.repositories.*;

import java.util.List;
import java.util.Set;


public class GenreControler implements Controller{
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;
    public GenreControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

   private GenreRepository repository;

    @Override
    public String get(int k) {
        Genre genre = repositoryFactory.getGenreRepository().get(k);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"id\":").append(genre.getId()).append(",")
                .append("\"name\":\"").append(genre.getdescription()).append("\"")
                .append("}");
        return jsonBuilder.toString();
    }



    @Override
    public String get() {
        Set<Genre> genres = repositoryFactory.getGenreRepository().getAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int count = 0;
        int size = genres.size();

        for (Genre genre : genres) {
            jsonBuilder.append("{")
                    .append("\"id\":").append(genre.getId()).append(",")
                    .append("\"name\":\"").append(genre.getdescription()).append("\"")
                    .append("}");
            if (++count < size) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }


    @Override
    public void post(String value) {
        Genre genre = new com.uvic.teknos.book.models.Genre();
        genre.setdescription(value);
        genre.setid(0);
        repositoryFactory.getGenreRepository().save(genre);

    }

    @Override
    public void put(int key, String value) {
        Genre genre =new com.uvic.teknos.book.models.Genre();
        genre.setid(key);
        genre.setdescription(value);
        repositoryFactory.getGenreRepository().save(genre);
    }

    @Override
    public void delete(int key) {
        Genre genre =new com.uvic.teknos.book.models.Genre();
        genre.setid(key);
        repositoryFactory.getGenreRepository().delete(genre) ;
    }
}