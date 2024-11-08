package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.repositories.AuthorRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class AuthorManager {
    private final BufferedReader in;
    private final PrintStream out;
    private final AuthorRepository authorRepository;
    private final ModelFactory modelFactory;
    String red = "\u001B[31m";
    String reset = "\u001B[0m";

    public AuthorManager(BufferedReader in, PrintStream out, AuthorRepository authorRepository, ModelFactory modelFactory) {
        this.in = in;
        this.out = out;
        this.authorRepository = authorRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("Author Management System:");
        out.println("1. Insert a new Author");
        out.println("2. Update an Author");
        out.println("3. Show a Author");

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
                    case "6": delete();
                        break;
                    default:
                        out.println("Invalid command. Please try again.");
                }
            } while (!command.equals("4"));
        } catch (IOException e) {
            out.println("Error reading input: " + e.getMessage());
        }
    }

    private void delete() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        Author author = modelFactory.crateAuthor();
        author.setId(id);
        authorRepository.delete(author);


    }

    private void get() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        Author author = modelFactory.crateAuthor();
        System.out.println(red);
        System.out.println(authorRepository.get(id).getnom());
        System.out.println(reset);


    }

    private void insert() {
        try {
            out.print("Enter first name: ");
            String firstName = in.readLine();
            System.out.print(firstName);
            out.print("Enter last name: ");
            String lastName = in.readLine();


            Author author = modelFactory.crateAuthor();
            author.setId(0);
            author.setnom(firstName);
            author.setsurname(lastName);
            authorRepository.save(author);
            System.out.println(red);
            out.println("Inserted author successfully: " + author.getnom());
            System.out.println(reset);

        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }
    private void update() {
        try {
            out.print("Entra el id");
            int id = Integer.parseInt(in.readLine());
            String firstName = in.readLine();
            String lastName = in.readLine();
            Author author = modelFactory.crateAuthor();
            author.setId(id);
            author.setnom(firstName);
            author.setsurname(lastName);
            authorRepository.save(author);
            System.out.println(red);
            out.println("Inserted author successfully: " + author.getnom());
            System.out.println(reset);

        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }

    private void getAll() {
        // Encabezados de la tabla

        out.printf(red + "%-20s %-20s%n" , "Nombre", "Cognom");
        // Línea separadora
        out.printf("%-20s %-20s%n", "-------------------", "-------------------");
        // Listado de autores
        for (Author author : authorRepository.getAll()) {
            out.printf("%-20s %-20s%n", author.getnom(), author.getsurname());
        }

        System.out.println(reset);
    }

}
