package com.jonathan.mybooklist.models;

import java.util.Set;

public interface Genre {
    int getId();
    void setid(int id);
    String getdescription();
    void setdescription(String description);
     Set<ReadBook> getReadBook();
    void setReadBook(Set<ReadBook>readBook);
     Set<PendingBook> getPendingBook();
    void setPendingBook(Set<PendingBook>pendingBook);
}
