package com.uvic.teknos.book.models;

import com.jonathan.mybooklist.models.Genre;

import java.time.LocalDate;
import java.util.Set;

public class PeandingBook implements com.jonathan.mybooklist.models.PendingBook {
    int id;
    String name;
    LocalDate data;
    int pages;
    String description;
    int plublishId;

    int authorId;
    int genres;


    @Override
    public int getidBookPending() {
        return id;
    }

    @Override
    public void setid_bookPending(int idBookPending) {
        id = idBookPending;

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
    public LocalDate getpublicationDate() {
        return data;
    }

    @Override
    public void setpublicationDate(LocalDate PublicationDate) {
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
