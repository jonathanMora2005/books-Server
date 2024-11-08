package com.jonathan.mybooklist.jpa.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "personalInformation")
public class PersonalInformation implements com.jonathan.mybooklist.models.PersonalInformation{
    @Id
    @Column(name = "DNI", length = 9)
    String DNI;
    @OneToOne
    @JoinColumn(name = "authorId", referencedColumnName = "ID")
    private Author author = new Author();
    @Column(name = "Nationality")

    String Nationality;
    @Column(name = "DateOfBirth")

    private Date DateOfBirth;
    @Column(name = "Phone", length = 9)

    int Phone;
    @Column(name = "Address")

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
        return author;
    }

    @Override
    public void setAuthorId(com.jonathan.mybooklist.models.Author AuthorId) {
        this.author.setId(AuthorId.getId());
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
