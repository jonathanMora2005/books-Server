package com.jonathan.mybooklist.models;

public interface ModelFactory {
    Author crateAuthor();
    Genre newGenre();
    Publishing newPublishing();
    PersonalInformation newPersonalInformation();
    ReadBook newReadBook();
}
