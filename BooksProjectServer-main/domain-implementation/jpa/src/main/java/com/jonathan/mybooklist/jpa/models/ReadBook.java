package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "readBook")
public class ReadBook implements com.jonathan.mybooklist.models.ReadBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id = 1;
    @Column(name = "Name")
    String name;
    @Column(name = "Data")
    Date data;
    @Column(name = "Pages")
    int pages;
    @Column(name = "Description")
    String description;
    @ManyToOne
    @JoinColumn(name = "publishId", referencedColumnName = "id")
    private Publshing publisher = new Publshing();
    @OneToOne
    @JoinColumn(name = "authorId", referencedColumnName = "ID")
    private Author author =  new Author();
    int genres ;
    @ManyToOne
    private Genre genre = new Genre();
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
    public Date getpublicationDate() {
        return data;
    }

    @Override
    public void setpublicationDate(Date PublicationDate) {
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
