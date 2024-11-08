package com.jonathan.services.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.repositories.AuthorRepository;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;

import java.util.Set;

public class AuthorControler implements Controller{
    AuthorRepository repository;

    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;
    public AuthorControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }
    @Override
    public String get(int k) {
        Author genre = repositoryFactory.getAuthorRepository().get(k);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"id\":").append(genre.getId()).append(",")
                .append("\"name\":\"").append(genre.getnom()).append("\"")
                .append("\"surname\":\"").append(genre.getsurname()).append("\"")
                .append("}");
        return jsonBuilder.toString();
    }

    @Override
    public String get() {
        Set<Author> genres = repositoryFactory.getAuthorRepository().getAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int count = 0;
        int size = genres.size();

        for (Author genre : genres) {
            jsonBuilder.append("{")
                    .append("\"id\":").append(genre.getId()).append(",")
                    .append("\"name\":\"").append(genre.getnom()).append("\"")
                    .append("\"surname\":\"").append(genre.getsurname()).append("\"");

            if (++count < size) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    @Override
    public void post(String value) {
        Author author =new com.uvic.teknos.book.models.Author();
        author.setId(0);


        repositoryFactory.getAuthorRepository().save(author);
    }

    @Override
    public void put(int key, String value) {
        Author author =new com.uvic.teknos.book.models.Author();
        author.setId(key);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String nombre = jsonNode.get("nombre").asText();
        String surname = jsonNode.get("surname").asText();
        author.setnom(nombre);
        author.setsurname(surname);
        repositoryFactory.getAuthorRepository().save(author); ;

    }

    @Override
    public void delete(int key) {
        Author author =new com.uvic.teknos.book.models.Author();
        author.setId(key);
        repositoryFactory.getAuthorRepository().delete(author) ;
    }
}
