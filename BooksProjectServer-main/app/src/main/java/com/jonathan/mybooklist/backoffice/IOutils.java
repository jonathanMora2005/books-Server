package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.backoffice.exeptions.BackofficeException;

import java.io.BufferedReader;
import java.io.*;



public class IOutils {
    public static String readline(BufferedReader in) {
        String command;
        try {
             command = in.readLine();
        } catch (IOException e) {
            throw new BackofficeException(e);
        }
      return command;
    }
}
