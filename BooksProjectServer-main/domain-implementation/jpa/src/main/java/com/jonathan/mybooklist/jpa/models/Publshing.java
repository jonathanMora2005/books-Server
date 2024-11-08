package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.*;

import java.awt.print.Book;
import java.util.List;
@Entity
@Table(name = "publshing")
public class Publshing implements com.jonathan.mybooklist.models.Publishing{
    @Id
    @Column(name = "ID")
    int id;
    @OneToMany(mappedBy = "publisher")
    private List<PeandingBook> peandingBook;
    @OneToMany(mappedBy = "publisher")
    private List<ReadBook> readBook;
    @Column(name = "Name")
    String name;
    @Column(name = "Email")
    String email;
    @Column(name = "Country")
    String country;


    @Override
    public int getpublishingid() {
        return id;
    }

    @Override
    public void setpublishingid(int id) {
        this.id = id;
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
    public String getemail() {
        return email;
    }

    @Override
    public void setemail(String email) {
        this.email = email;
    }

    @Override
    public String getcountry() {
        return country;
    }

    @Override
    public void setcountry(String country) {
        this.country = country;
    }
}
