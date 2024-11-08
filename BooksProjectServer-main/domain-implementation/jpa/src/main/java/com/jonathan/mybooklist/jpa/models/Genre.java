package com.jonathan.mybooklist.jpa.models;

import com.jonathan.mybooklist.models.PendingBook;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre implements com.jonathan.mybooklist.models.Genre  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Code")
    private int id;

    @Column(name = "Descripcion")
    private String description;

    @OneToMany(mappedBy = "genre")
    private List<ReadBook> books;



    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setid(int id) {
        this.id = id;
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
    public Set<com.jonathan.mybooklist.models.ReadBook> getReadBook() {
        return null;
    }

    @Override
    public void setReadBook(Set<com.jonathan.mybooklist.models.ReadBook> readBook) {

    }

    @Override
    public Set<PendingBook> getPendingBook() {
        return null;
    }

    @Override
    public void setPendingBook(Set<PendingBook> pendingBook) {

    }


}
