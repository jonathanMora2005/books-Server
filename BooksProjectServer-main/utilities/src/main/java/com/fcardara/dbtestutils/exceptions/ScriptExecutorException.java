package com.fcardara.dbtestutils.exceptions;

public class ScriptExecutorException extends RuntimeException{

    public ScriptExecutorException(Exception e) {
        super(e);
    }

    public ScriptExecutorException(String message, Exception e) {
        super(message, e);
    }


}
