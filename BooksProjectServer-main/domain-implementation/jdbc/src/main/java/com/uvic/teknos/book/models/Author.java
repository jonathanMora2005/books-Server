package com.uvic.teknos.book.models;

public class Author implements com.jonathan.mybooklist.models.Author {
    private String nom = "";
    private String surname = "";
    private int id = 0;


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
