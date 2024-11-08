package com.jonathan.mybooklist.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


public interface ReadBook {


    int getidBookRead();
    void setidBookRead(int idBookRead);
    String getname();
    void setname(String name);
    Date getpublicationDate();
    void setpublicationDate(Date PublicationDate);
    int getpages();
    void  setpages(int pages);
    String getdescription();
    void setdescription(String description );
    int getauthorId();
    void setauthorId(int author_id);
    int getpublishingId();
    void publishingId(int publishingId );
    int getgenreCode();
    void  setgenreCode(int genreCode);


}
