package com.uvic.teknos.book.models;


import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.models.Publishing;

public class JdbcModelFactory implements ModelFactory {
    @Override
    public Author crateAuthor() {
        return new com.uvic.teknos.book.models.Author();
    }

    @Override
    public Genre newGenre() {
        return new com.uvic.teknos.book.models.Genre();
    }

    @Override
    public Publishing newPublishing() {
        return new Publshing();
    }
    @Override
    public ReadBook newReadBook() {
        return new ReadBook();
    }

    @Override
    public PersonalInformation newPersonalInformation() {
        return new com.uvic.teknos.book.models.PersonalInformation();
    }
}
