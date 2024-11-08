package com.jonathan.services.controllers;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookreadRepository;
import com.jonathan.mybooklist.repositories.GenreRepository;
import com.uvic.teknos.book.repositorys.JdbcReadBookRepository;

import java.util.Date;
import java.util.Set;

public class ReadBookControler implements Controller{
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;

    public ReadBookControler(RepositoryFactory repositoryFactory, ModelFactory modelFactory) {
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    @Override
    public String get(int k) {
        ReadBook genre = repositoryFactory.getReadRepositori().get(k);
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{")
                .append("\"pages\":\"").append(genre.getpages()).append("\"")
                .append("\"authorID\":\"").append(genre.getauthorId()).append("\"")
                .append("\"description\":\"").append(genre.getdescription()).append("\"")
                .append("\"name\":\"").append(genre.getname()).append("\"")
                .append("}");
        return jsonBuilder.toString();
    }

    @Override
    public String get() {
        Set<ReadBook> genres = repositoryFactory.getReadRepositori().getAll();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        int count = 0;
        int size = genres.size();

        for (ReadBook genre : genres) {
            jsonBuilder.append("{")
                    .append("\"pages\":\"").append(genre.getpages()).append("\"")
                    .append("\"authorID\":\"").append(genre.getauthorId()).append("\"")
                    .append("\"description\":\"").append(genre.getdescription()).append("\"")
                    .append("\"name\":\"").append(genre.getname()).append("\"");

            if (++count < size) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    @Override
    public void post(String value) {
        ReadBook author =new com.uvic.teknos.book.models.ReadBook();
        author.setidBookRead(0);
        var a = value.split("/");
        author.setdescription(a[0]);
        author.setauthorId(Integer.parseInt(a[1]));
        author.setname(a[2]);
        author.setpages(Integer.parseInt(a[3]));
        repositoryFactory.getReadRepositori().save(author);
    }

    @Override
    public void put(int key, String value) {

    }

    @Override
    public void delete(int key) {

    }
}
