package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "peandingBook")
public class PeandingBook implements com.jonathan.mybooklist.models.PendingBook {
    @Id
    @Column(name = "ID")
    int id;
    @Column(name = "Name")
    String name;
    @Column(name = "Data")
    LocalDate data;
    @Column(name = "Pages")
    int pages;
    @Column(name = "Description")
    String description;
    @ManyToOne
    @JoinColumn(name = "publishId", referencedColumnName = "id")
    private Publshing publisher;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

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
        return author.getId();
    }
    @Override
    public void setauthorId(int author_id) {
        this.author.setId(author_id);
    }
    @Override
    public int getpublishingId() {
        return publisher.getpublishingid();
    }
    @Override
    public void publishingId(int publishingId) {
        publisher.setpublishingid(publishingId);
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
