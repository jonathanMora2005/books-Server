package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;

import java.util.Set;

public class JdbcPeandingBookRepository implements PersonalinformationRepository {
    @Override
    public void save(PersonalInformation model) {

    }

    @Override
    public void delete(PersonalInformation model) {

    }

    @Override
    public PersonalInformation get(Integer id) {
        return null;
    }

    @Override
    public Set<PersonalInformation> getAll() {
        return null;
    }
}
