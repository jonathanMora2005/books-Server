package com.jonathan.mybooklist;

import com.jonathan.mybooklist.repositories.*;

public interface RepositoryFactory {
    AuthorRepository getAuthorRepository();
    GenreRepository getGenreRepository();
    BookreadRepository getReadRepositori();

    PublishingRepository getPublishingRepository();
    PersonalinformationRepository getPersonalInformationRepository();


}
