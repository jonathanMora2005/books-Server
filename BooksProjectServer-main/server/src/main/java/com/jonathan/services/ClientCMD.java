package com.jonathan.services;

import com.jonathan.mybooklist.RepositoryFactory;
import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.services.controllers.AuthorControler;
import com.jonathan.services.controllers.GenreControler;
import com.jonathan.services.controllers.PublishingControler;
import com.jonathan.services.controllers.ReadBookControler;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientCMD {
    public static Scanner scanner = new Scanner(System.in);
    public static RepositoryFactory repositoryFactory;

    static {
        try {
            repositoryFactory = new JdbcRepositoryFactory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ModelFactory modelFactory = new JdbcModelFactory();



    public static void main(String[] args) throws SQLException {

        int a = 0;
        while (a != 5){
            System.out.println("Quina base de dades vols fer servir: ");
            System.out.println("1. Genre ");
            System.out.println("2. Author ");
            System.out.println("3. Publisher ");
            System.out.println("4. Read Book ");
            System.out.println("5. salir");
            a = scanner.nextInt();
            switch (a) {
                case 1:
                    genreCMD();
                    break;
                case 2:
                    authorCMD();
                    break;
                case 3:
                    publisherCMD();
                    break;
                case 4:
                    readBookCMD();
                    break;
                default:
                    System.out.println("Invalid day");
                    break;
            }

        }
    }

    private static void readBookCMD() {
        int b = 0;
        while (b != 6) {
            ReadBookControler readBookControler = new ReadBookControler(repositoryFactory, modelFactory);
            System.out.println("Que vols fer: ");
            System.out.println("1. get ");
            System.out.println("2. getALL ");
            System.out.println("3. delete ");
            System.out.println("4. update ");
            System.out.println("5. insert ");
            System.out.println("6. salir ");
            b = scanner.nextInt();
            switch (b) {
                case 1:
                    readget(readBookControler);
                    break;
                case 2:
                    readALL(readBookControler);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid day");
                    break;
            }
        }
    }

    private static void readALL(ReadBookControler readBookControler) {
        System.out.println(readBookControler.get());

    }

    private static void readget(ReadBookControler readBookControler) {
        System.out.println("pon el id del que quieras seleciona");
        int x = scanner.nextInt();
        System.out.println(readBookControler.get(x));

    }

    private static void publisherCMD() {
        int b = 0;
        while (b != 6) {
            PublishingControler publishingControler = new PublishingControler(repositoryFactory, modelFactory);
            System.out.println("Que vols fer: ");
            System.out.println("1. get ");
            System.out.println("2. getALL ");
            System.out.println("3. delete ");
            System.out.println("4. update ");
            System.out.println("5. insert ");
            System.out.println("6. salir ");
            b = scanner.nextInt();
            switch (b) {
                case 1:
                    publishingget(publishingControler);
                    break;
                case 2:
                    publishingALL(publishingControler);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid day");
                    break;
            }
        }
    }

    private static void publishingALL(PublishingControler publishingControler) {
        System.out.println(publishingControler.get());
    }

    private static void publishingget(PublishingControler publishingControler) {
        System.out.println("pon el id del que quieras seleciona");
        int x = scanner.nextInt();
        System.out.println(publishingControler.get(x));
    }

    private static void authorCMD() {
        int b = 0;
        while (b != 6) {
            AuthorControler authorControler = new AuthorControler(repositoryFactory, modelFactory);
            System.out.println("Que vols fer: ");
            System.out.println("1. get ");
            System.out.println("2. getALL ");
            System.out.println("3. delete ");
            System.out.println("4. update ");
            System.out.println("5. insert ");
            System.out.println("6. salir ");
            b = scanner.nextInt();
            switch (b) {
                case 1:
                    authorget(authorControler);
                    break;
                case 2:
                    authorALL(authorControler);
                    break;
                case 3:
                    authorDelete(authorControler);
                    break;
                case 4:
                    authorUpdate(authorControler);
                    break;
                default:
                    System.out.println("Invalid day");
                    break;
            }
        }
    }

    private static void authorUpdate(AuthorControler authorControler) {
        System.out.println("pon el id del que quieras actualitzar");
        int x = scanner.nextInt();
        System.out.println("pon su nuevo nombre");
        String texto = scanner.nextLine();
        System.out.println("pon su nuevo cognom");
        String cognom = scanner.nextLine();
        authorControler.put(x, texto +"/" + cognom);
    }

    private static void authorDelete(AuthorControler authorControler) {
        System.out.println("pon el id del que quieras eliminar");
        int x = scanner.nextInt();
        authorControler.delete(x);
    }

    private static void authorALL(AuthorControler authorControler) {
        System.out.println(authorControler.get());

    }

    private static void authorget(AuthorControler authorControler) {
        System.out.println("pon el id del que quieras seleciona");
        int x = scanner.nextInt();
        System.out.println(authorControler.get(x));
    }

    private static void genreCMD() {
        int b = 0;
        while (b != 6) {
            GenreControler genreController = new GenreControler(repositoryFactory, modelFactory);
            System.out.println("Que vols fer: ");
            System.out.println("1. get ");
            System.out.println("2. getALL ");
            System.out.println("3. delete ");
            System.out.println("4. update ");
            System.out.println("5. insert ");
            System.out.println("6. salir ");
            b = scanner.nextInt();
            switch (b) {
                case 1:
                    genreget(genreController);
                    break;
                case 2:
                    getALL(genreController);
                    break;
                case 3:
                    genreDelete(genreController);
                    break;
                case 4:
                    genreUpdate(genreController);
                    break;
                case 5:
                    genreInsert(genreController);
                    break;
                default:
                    System.out.println("Invalid day");
                    break;
            }
        }

    }

    private static void genreInsert(GenreControler genreController) {
        System.out.println("pon su  descripcion");
        String texto = scanner.nextLine();
        genreController.post(texto);
    }

    private static void genreUpdate(GenreControler genreController) {
        System.out.println("pon el id del que quieras actualitzar");
        int x = scanner.nextInt();
        System.out.println("pon su nueva descripcion");
        String texto = scanner.nextLine();
        genreController.put(x, texto);
    }

    private static void genreDelete(GenreControler genreController) {
        System.out.println("pon el id del que quieras eliminar");
        int x = scanner.nextInt();
        genreController.delete(x);
    }

    private static void genreget(GenreControler genreController) {
        System.out.println("pon el id del que quieras seleciona");
        int x = scanner.nextInt();
        System.out.println(genreController.get(x));
    }
    private static void getALL(GenreControler genreController) {
        System.out.println(genreController.get());

    }
}
