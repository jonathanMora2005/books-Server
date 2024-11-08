package com.jonathan.services;

import com.jonathan.mybooklist.models.ModelFactory;
import com.jonathan.mybooklist.repositories.Repository;
import com.jonathan.services.controllers.*;
import com.uvic.teknos.book.models.JdbcModelFactory;
import com.uvic.teknos.book.repositorys.JdbcRepositoryFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws IOException, SQLException {
        JdbcRepositoryFactory modelFactory = new JdbcRepositoryFactory();
        JdbcModelFactory repositoryFactory = new JdbcModelFactory();
        var controlers = new HashMap<String, Controller>();
        controlers.put("genre",new GenreControler(modelFactory,repositoryFactory));
        controlers.put("author",new AuthorControler(modelFactory,repositoryFactory));
        controlers.put("personalInformation",new PersonalInformationControler(modelFactory,repositoryFactory));
        controlers.put("publishing",new PublishingControler(modelFactory,repositoryFactory));
        controlers.put("readBook",new ReadBookControler(modelFactory,repositoryFactory));
        controlers.put("peandingBook",new PeandingBookControler(modelFactory,repositoryFactory));
        var requestRouter = new RequestRouterImpl(controlers);
        new Server(requestRouter).start();

    }
}
