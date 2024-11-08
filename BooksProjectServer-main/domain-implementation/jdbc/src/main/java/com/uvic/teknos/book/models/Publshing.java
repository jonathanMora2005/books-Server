package com.uvic.teknos.book.models;

public class Publshing implements com.jonathan.mybooklist.models.Publishing{
    int id;
    String name;
    String email;
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
