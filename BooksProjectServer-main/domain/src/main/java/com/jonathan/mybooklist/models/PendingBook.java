package com.jonathan.mybooklist.models;

import java.time.LocalDate;
import java.util.Set;

public interface PendingBook {
    int getidBookPending();
    void setid_bookPending(int idBookPending);
    String getname();
    void setname(String name);
    LocalDate getpublicationDate();
    void setpublicationDate(LocalDate PublicationDate);
    int getpages();
    void  setpages(int pages);
    String getdescription();
    void setdescription(String description );
    int getauthorId();
    void setauthorId(int authorId);
    int getpublishingId();
    void publishingId(int publishingId );
    int getgenreCode();
    void  setgenreCode(int genreCode);


}

