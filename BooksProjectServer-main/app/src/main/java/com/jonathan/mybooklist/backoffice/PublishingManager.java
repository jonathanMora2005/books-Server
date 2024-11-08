package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.models.Publishing;
import com.jonathan.mybooklist.repositories.PublishingRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class PublishingManager {
    private final BufferedReader in;
    private final PrintStream out;
    private final PublishingRepository publishingRepository;
    private final ModelFactory modelFactory;

    public PublishingManager(BufferedReader in, PrintStream out, PublishingRepository publishingRepository, ModelFactory modelFactory) {
        this.in = in;
        this.out = out;
        this.publishingRepository = publishingRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("Publishin Management System:");
        out.println("1. Insert a new Publishin");
        out.println("2. Update an Publishin");
        out.println("3. Show a Publishin");
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
        Publishing publishing = modelFactory.newPublishing();
        publishing.setpublishingid(id);
        publishingRepository.delete(publishing);
    }

    private void get() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        Publishing author = modelFactory.newPublishing();
        System.out.println(publishingRepository.get(id).getname());
    }

    private void insert() {
        try {
            out.print("Enter name: ");
            String name = in.readLine();
            out.print("Enter  email: ");
            String email = in.readLine();
            out.print("Enter country: ");
            String country = in.readLine();

            Publishing author = modelFactory.newPublishing();
            author.setpublishingid(0);
            author.setname(name);
            author.setemail(email);
            author.setcountry(country);

            publishingRepository.save(author);
            out.println("Inserted author successfully: " + author.getname());
        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }
    private void update() {
        try {
            out.print("Enter id: ");
            int id = Integer.parseInt(in.readLine());
            out.print("Enter name: ");
            String name = in.readLine();
            out.print("Enter  email: ");
            String email = in.readLine();
            out.print("Enter country: ");
            String country = in.readLine();

            Publishing author = modelFactory.newPublishing();
            author.setpublishingid(id);
            author.setname(name);
            author.setemail(email);
            author.setcountry(country);

            publishingRepository.save(author);
            out.println("Inserted author successfully: " + author.getname());
        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }

    private void getAll() {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";

        out.printf(red + "%-20s %-20s %-20s%n" , "Nombre", "Email", "País");
        out.printf("%-20s %-20s %-20s%n", "-------------------", "-------------------", "-------------------");
        for (Publishing publishing : publishingRepository.getAll()) {
            out.printf("%-20s %-20s %-20s%n", publishing.getname(), publishing.getemail(), publishing.getcountry());
        }
        System.out.println(reset);

    }
}
