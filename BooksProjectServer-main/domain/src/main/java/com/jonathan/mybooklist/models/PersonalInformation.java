package com.jonathan.mybooklist.models;

import java.sql.Date;
import java.time.LocalDate;

public interface PersonalInformation {
    String getDNI();
    void setDNI(String DNI);
    Author getauthorId();
    void setAuthorId(Author AuthorId );
    String getNationality();
    void setNationality(String Nationality);
    Date getDateOfBirth();
    void  setDateOfBirth(Date DateOfBirth);
    int getPhone();
    void setPhone(int Phone);
    String getAddress();
    void setAddress(String Address);

}
