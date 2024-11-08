package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.repositories.GenreRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;



public class GenreManager {
    private final BufferedReader in;
    private final PrintStream out;
    private final GenreRepository genreRepository;
    private final ModelFactory modelFactory;

    public GenreManager(BufferedReader in, PrintStream out, GenreRepository genreRepository, ModelFactory modelFactory) {
        this.in = in;
        this.out = out;
        this.genreRepository = genreRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("Genre Management System:");
        out.println("1. Insert a new Genre");
        out.println("2. Update an Genre");
        out.println("3. Show all Genre");
        out.println("4. Get");
        out.println("6. delete");

        out.println("5. Exit");

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
                    case "4":
                        get();
                        break;
                    case "6":
                        delete();
                        break;
                    default:
                        out.println("Invalid command. Please try again.");
                }
            } while (!command.equals("5"));
        } catch (IOException e) {
            out.println("Error reading input: " + e.getMessage());
        }
    }

    private void delete() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());

        Genre genre = modelFactory.newGenre();
        genre.setid(id);
        genreRepository.delete(genre);


    }

    private void get() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        Author author = modelFactory.crateAuthor();

        System.out.println(genreRepository.get(id).getdescription());

    }

    private void insert() {
        try {
            out.print("Enter first description: ");
            String  description = in.readLine();
            Genre genre = modelFactory.newGenre();
            genre.setid(0);
            genre.setdescription(description);
            genreRepository.save(genre);
            out.println("Inserted author successfully: " + genre.getdescription());
        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }
    private void update() {
        try {
            out.print("Entra el id");
            int id = Integer.parseInt(in.readLine());
            System.out.print("Enter description: ");
            String description = in.readLine();
            System.out.print("Enter last name: ");
            String lastName = in.readLine();
            Genre genre = modelFactory.newGenre();
            genre.setid(id);
            genre.setdescription(description);

            genreRepository.save(genre);
            out.println("Inserted author successfully: " + genre.getdescription());
        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }

    private void getAll() {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        System.out.println(red);
        out.printf(red + "%-20s %-20s%n" , "ID", "Descripción");
        out.printf("%-20s %-20s%n", "-------------------", "-------------------");
        for (Genre genre : genreRepository.getAll()) {
            out.printf("%-20s %-20s%n", genre.getId(), genre.getdescription());
        }
        System.out.println(reset);

    }
}
