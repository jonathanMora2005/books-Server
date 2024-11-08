package com.jonathan.mybooklist.backoffice;

import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ReadBook;

import java.io.*;


public class BackOffice {
    private final BufferedReader in;
    private final PrintStream out;
    private final RepositoryFactory repositoryFactory;
    private final ModelFactory modelFactory;


    public BackOffice(InputStream inputStream, OutputStream outputStream, RepositoryFactory repositoryFactory, ModelFactory modelFactory)  {
        this.in = new BufferedReader(new InputStreamReader(inputStream));
        this.out = new PrintStream(outputStream);
        this.repositoryFactory = repositoryFactory;
        this.modelFactory = modelFactory;
    }

    public void start() {
        showWelcomeMessage();
        var command = "";
        try {
            do {
                showMainMenu();
                command = in.readLine();
                switch (command) {
                    case "1" -> manageAuthor();
                    case "2" -> manageGenre();
                    case "3" -> managePublishing();
                    case "4" -> managePersonalInformation();
                    case "5" -> manageReadBook();
                    case "6" -> managePendingBook();
                }
            } while (!command.equals("exit"));
            out.println("Bye!");
            }catch (Exception e) {
            System.out.println("error");
        }
        }

    private void managePendingBook() {
    }

    private void manageReadBook() {
        new ReadBookManager(in, out, repositoryFactory.getReadRepositori(), modelFactory).start();


    }

    private void managePersonalInformation() {
        new PersonalInformationManager(in, out, repositoryFactory.getPersonalInformationRepository(), modelFactory).start();
    }

    private void managePublishing() {
        new PublishingManager(in, out, repositoryFactory.getPublishingRepository(), modelFactory).start();
    }

    private void manageGenre() {
        new GenreManager(in, out, repositoryFactory.getGenreRepository(), modelFactory).start();

    }


    private void manageAuthor() {
        new AuthorManager(in, out, repositoryFactory.getAuthorRepository(), modelFactory).start();
    }


    private void showWelcomeMessage() {
        out.println("Welcome to the MyBookList Back Office");
        out.println("Select a menu option or type exit to exit the application");
    }

    private void showMainMenu() {
        out.println("1. Author");
        out.println("2. Genre");
        out.println("3. Publishing");
        out.println("4. PersonalInformation");
        out.println("5. BookRead");
        out.println("6. BookPending");

        ;
    }

}