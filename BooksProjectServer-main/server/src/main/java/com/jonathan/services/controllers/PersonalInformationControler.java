package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.*;
import com.jonathan.mybooklist.repositories.GenreRepository;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

public class PersonalInformationControler implements Controller{
    PersonalinformationRepository repository;
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;
    public PersonalInformationControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    @Override
    public String get(int k) {
        PersonalInformation genre = repositoryFactory.getPersonalInformationRepository().get(k);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"phone\":").append(genre.getPhone()).append(",")
                .append("\"address\":\"").append(genre.getAddress()).append("\"")
                .append("\"DNI\":\"").append(genre.getDNI()).append("\"")
                .append("\"nationality\":\"").append(genre.getNationality()).append("\"")
                .append("\"nationality\":\"").append(genre.getDateOfBirth()).append("\"")
                .append("}");
        return jsonBuilder.toString();
    }

    @Override
    public String get() {
        Set<PersonalInformation> genres = repositoryFactory.getPersonalInformationRepository().getAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int count = 0;
        int size = genres.size();

        for (PersonalInformation genre : genres) {
            jsonBuilder.append("{")
                    .append("\"phone\":").append(genre.getPhone()).append(",")
                    .append("\"address\":\"").append(genre.getAddress()).append("\"")
                    .append("\"DNI\":\"").append(genre.getDNI()).append("\"")
                    .append("\"nationality\":\"").append(genre.getNationality()).append("\"")
                    .append("\"nationality\":\"").append(genre.getDateOfBirth()).append("\"");
            if (++count < size) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    @Override
    public void post(String value) {
        PersonalInformation author =new com.uvic.teknos.book.models.PersonalInformation();
        author.setPhone(0);
        author.setAddress(value.split(".")[0]);
        author.setDNI(value.split(".")[1]);
        author.setNationality(value.split(".")[2]);
        try {
            author.setDateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(value.split("\\.")[2]).getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        repositoryFactory.getPersonalInformationRepository().save(author);
    }

    @Override
    public void put(int key, String value) {
        PersonalInformation author =new com.uvic.teknos.book.models.PersonalInformation();
        author.setPhone(key);
        author.setAddress(value.split(".")[0]);
        author.setDNI(value.split(".")[1]);
        author.setNationality(value.split(".")[2]);
        try {
            author.setDateOfBirth(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(value.split("\\.")[2]).getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        repositoryFactory.getPersonalInformationRepository().save(author);

    }

    @Override
    public void delete(int key) {
        PersonalInformation author =new com.uvic.teknos.book.models.PersonalInformation();
        author.setPhone(key);
        repositoryFactory.getPersonalInformationRepository().delete(author) ;
    }
}
