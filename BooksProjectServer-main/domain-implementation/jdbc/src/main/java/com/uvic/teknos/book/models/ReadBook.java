package com.uvic.teknos.book.models;

import com.jonathan.mybooklist.models.Genre;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

public class ReadBook implements com.jonathan.mybooklist.models.ReadBook {
    int id;
    String name;
    java.sql.Date data;
    int pages;
    String description;
    int plublishId = 1;

    int authorId;
    int genres ;
    @Override
    public int getidBookRead() {
        return id;
    }

    @Override
    public void setidBookRead(int idBookRead) {
        id = idBookRead;
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public void setname(String name) {
        this.name = name;
    }

    @Override
    public java.sql.Date getpublicationDate() {
        return data;
    }

    @Override
    public void setpublicationDate(java.sql.Date PublicationDate) {
        data = PublicationDate;

    }



    @Override
    public int getpages() {
        return pages;
    }

    @Override
    public void setpages(int pages) {
        this.pages = pages;
    }

    @Override
    public String getdescription() {
        return description;
    }

    @Override
    public void setdescription(String description) {
        this.description = description;
    }

    @Override
    public int getauthorId() {
        return authorId;
    }

    @Override
    public void setauthorId(int author_id) {
        this.authorId = author_id;
    }

    @Override
    public int getpublishingId() {
        return plublishId;
    }



    @Override
    public void publishingId(int publishingId) {
        this.plublishId = plublishId;
    }

    @Override
    public int getgenreCode() {
        return genres;
    }

    @Override
    public void setgenreCode(int genreCode) {
        genres = genreCode;
    }
}
