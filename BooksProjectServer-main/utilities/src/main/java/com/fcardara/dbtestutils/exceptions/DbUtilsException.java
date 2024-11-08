package com.fcardara.dbtestutils.exceptions;

public class DbUtilsException extends RuntimeException{

    public DbUtilsException(Exception e) {
        super(e);
    }

    public DbUtilsException(String message, Exception e) {
        super(message, e);
    }
}
