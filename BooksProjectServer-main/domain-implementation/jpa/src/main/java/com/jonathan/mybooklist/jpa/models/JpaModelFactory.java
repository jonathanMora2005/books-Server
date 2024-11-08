package com.jonathan.mybooklist.jpa.models;


import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.models.*;

public class JpaModelFactory implements ModelFactory {
    @Override
    public Author crateAuthor() {
        return new com.jonathan.mybooklist.jpa.models.Author();
    }

    @Override
    public Genre newGenre() {
        return new com.jonathan.mybooklist.jpa.models.Genre();
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
        return new com.jonathan.mybooklist.jpa.models.PersonalInformation();
    }
}
