package com.uvic.teknos.book.models;

import java.sql.Date;
import java.time.LocalDate;

public class PersonalInformation implements com.jonathan.mybooklist.models.PersonalInformation{
    String DNI;
    Author authorId = new Author();
    String Nationality;
    private Date DateOfBirth;

    int Phone;
    private String Address;

    @Override
    public String getDNI() {
        return DNI;
    }

    @Override
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    @Override
    public Author getauthorId() {
        return authorId;
    }

    @Override
    public void setAuthorId(com.jonathan.mybooklist.models.Author AuthorId) {
        authorId.setId(AuthorId.getId());
    }


    @Override
    public String getNationality() {
        return Nationality;
    }

    @Override
    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    @Override
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    @Override
    public void setDateOfBirth(Date DateOfBirth) {
            this.DateOfBirth = DateOfBirth;
    }

    @Override
    public int getPhone() {
        return Phone;
    }

    @Override
    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    @Override
    public String getAddress() {
        return Address;
    }

    @Override
    public void setAddress(String Address) {
        this.Address = Address;
    }
}
