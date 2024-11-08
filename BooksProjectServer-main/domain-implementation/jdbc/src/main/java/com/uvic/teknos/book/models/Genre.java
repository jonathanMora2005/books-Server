package com.uvic.teknos.book.models;

import com.jonathan.mybooklist.models.PendingBook;
import com.jonathan.mybooklist.models.ReadBook;

import java.io.Serializable;
import java.util.Set;

public class Genre implements com.jonathan.mybooklist.models.Genre  {

    public int id;
    public String description;




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
    public Set<ReadBook> getReadBook() {
        return null;
    }

    @Override
    public void setReadBook(Set<ReadBook> readBook) {

    }

    @Override
    public Set<PendingBook> getPendingBook() {
        return null;
    }

    @Override
    public void setPendingBook(Set<PendingBook> pendingBook) {

    }


}
