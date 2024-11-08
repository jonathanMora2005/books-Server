package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookreadRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class ReadBookManager {
    private final BufferedReader in;
    private final PrintStream out;
    private final BookreadRepository bookreadRepository;
    private final ModelFactory modelFactory;
    String red = "\u001B[31m";
    String reset = "\u001B[0m";

    public ReadBookManager(BufferedReader in, PrintStream out, BookreadRepository authorRepository, ModelFactory modelFactory) {
        this.in = in;
        this.out = out;
        this.bookreadRepository = authorRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("ReadBook Management System:");
        out.println("1. Insert a new ReadBook");
        out.println("2. Update an ReadBook");
        out.println("3. Show all ReadBook");
        out.println("5. Get");
        out.println("4. Exit");

        String command;
        try {
            do {
                out.print("Enter command: ");
                command = in.readLine();

                switch (command) {
                    case "1":
                        insert();
                        break;
                    case "2":
                        update();
                        break;
                    case "3":
                        getAll();
                        break;
                    case "5":
                        get();
                        break;
                    default:
                        out.println("Invalid command. Please try again.");
                }
            } while (!command.equals("4"));
        } catch (IOException e) {
            out.println("Error reading input: " + e.getMessage());
        }

    }

    private void get() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        var a = bookreadRepository.get(id);
        System.out.println("ID: "+a.getauthorId());
        System.out.println("Name: "+a.getname());
        System.out.println("Data: " + a.getpublicationDate());
        System.out.println("Pages: " + a.getpages());
        System.out.println("Description: " + a.getdescription());
        System.out.println("Author: " + a.getauthorId());
        System.out.println("Publishing: " + a.getpublishingId());
        System.out.println("Genre: " + a.getgenreCode());

    }

    private void insert() {


    }
    private void update() {

    }

    private void getAll() {

    }

}

