package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;

public class PersonalInformationManager {
    private final BufferedReader in;
    private final PrintStream out;
    private final PersonalinformationRepository personalInformationRepository;
    private final ModelFactory modelFactory;

    public PersonalInformationManager(BufferedReader in, PrintStream out, PersonalinformationRepository personalInformationRepository, ModelFactory modelFactory) {
        this.in = in;
        this.out = out;
        this.personalInformationRepository = personalInformationRepository;
        this.modelFactory = modelFactory;
    }

    public void start() {
        out.println("personalInformation Management System:");
        out.println("1. Insert a new personalInformation");
        out.println("2. Update an personalInformation");
        out.println("3. Show all personalInformation");
        out.println("4. Get");
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
                    case "6": delete();
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
        out.print("Entra el DNI");
        int id = Integer.parseInt(in.readLine());
        PersonalInformation personalInformation = modelFactory.newPersonalInformation();
        personalInformation.setDNI(String.valueOf(id));
        personalInformationRepository.delete(personalInformation);
    }

    private void get() throws IOException {
        out.print("Entra el id");
        int id = Integer.parseInt(in.readLine());
        System.out.println(personalInformationRepository.get(id).getauthorId());

    }

    private void insert() {
        try {
            out.print("Enter  DNI: ");
            String  DNI = in.readLine();
            out.print("Enter Author id: ");
            int  authorId = Integer.parseInt(in.readLine());
            out.print("Enter  Nacionalitat: ");
            String  nacionalitat = in.readLine();
            out.print("Enter  data: ");
            Date data = Date.valueOf(in.readLine());
            out.print("Enter  Phone: ");
            int phone = Integer.parseInt(in.readLine());
            out.print("Enter  address: ");
            String address = in.readLine();
            PersonalInformation personalInformation = modelFactory.newPersonalInformation();
            personalInformation.setDNI(DNI);
            Author a = new com.uvic.teknos.book.models.Author();
            a.setId(authorId);
            personalInformation.setAuthorId(a);
            personalInformation.setDateOfBirth(data);
            personalInformation.setPhone(phone);
            personalInformation.setNationality(nacionalitat);
            personalInformation.setAddress(address);
            personalInformationRepository.save(personalInformation);
            out.println("Inserted author successfully: " + personalInformation.getDNI());
        } catch (IOException e) {
            out.println("Error inserting author: " + e.getMessage());
        }
    }
    private void update() {

    }

    private void getAll() {
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        out.print(red);
        out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n" , "Author ID", "DNI", "Nationality", "Date of Birth", "Phone", "Address");
        out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n", "---------------", "---------------", "---------------", "---------------", "---------------", "---------------");
        for (PersonalInformation personalInformation : personalInformationRepository.getAll()) {
            out.printf("%-15s %-15s %-15s %-15s %-15s %-15s%n", personalInformation.getauthorId(), personalInformation.getDNI(), personalInformation.getNationality(), personalInformation.getDateOfBirth(), personalInformation.getPhone(), personalInformation.getAddress());
        }
        System.out.println(reset);

    }
}
