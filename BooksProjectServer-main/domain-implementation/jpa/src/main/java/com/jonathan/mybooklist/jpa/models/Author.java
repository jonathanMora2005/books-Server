package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "author")
public class Author implements com.jonathan.mybooklist.models.Author {
    @Column(name = "Name")
    private String nom = "";
    @Column(name = "Surname")
    private String surname = "";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id = 0;
    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    private PersonalInformation personalInformation;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    //private List<ReadBook> readBooks;
   @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
   private List<PeandingBook> peandingBook;



    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getnom() {
        return this.nom;
    }

    @Override
    public void setnom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getsurname() {
        return this.surname;
    }

    @Override
    public void setsurname(String surname) {
        this.surname = surname;
    }
}
