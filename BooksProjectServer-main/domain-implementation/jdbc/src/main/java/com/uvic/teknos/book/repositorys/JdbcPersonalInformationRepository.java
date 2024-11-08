package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.models.PersonalInformation;
import com.jonathan.mybooklist.repositories.PersonalinformationRepository;
import com.uvic.teknos.book.models.Author;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcPersonalInformationRepository implements PersonalinformationRepository {
    private final Connection conection;
    JdbcPersonalInformationRepository(Connection conection) {
        this.conection = conection;
    }

    @Override
    public void save(PersonalInformation model) {
        insert(model);}
    private void insert(PersonalInformation model) {
        String sql = "INSERT INTO personal_information (DNI, Author_id, Nationality, Date_of_Birth, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getDNI());
            statement.setInt(2, model.getauthorId().getId());
            statement.setString(3, model.getNationality());
            statement.setDate(4, model.getDateOfBirth());
            statement.setInt(5, model.getPhone());
            statement.setString(6, model.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PersonalInformation get(Integer id) {

        try (PreparedStatement statement = conection.prepareStatement(
                "SELECT * FROM personal_information WHERE Author_id =  ?", Statement.RETURN_GENERATED_KEYS)) {
            com.uvic.teknos.book.models.PersonalInformation personalInformation = null;
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                personalInformation = new com.uvic.teknos.book.models.PersonalInformation();
                personalInformation.setDNI(resultSet.getString("DNI"));
                Author a  = new Author();
                var b = resultSet.getInt("Author_id");
                a.setId(b);
                personalInformation.setNationality(resultSet.getString("Nationality"));
                personalInformation.setDateOfBirth(resultSet.getDate("Date_of_Birth"));
                personalInformation.setPhone(resultSet.getInt("Phone"));
                personalInformation.setAddress(resultSet.getString("Address"));
                return personalInformation;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    @Override
    public void delete(PersonalInformation model) {
        String sql = "DELETE FROM personal_information WHERE DNI = ?";
        try (PreparedStatement statement = conection.prepareStatement(sql)) {
            Author a = new Author();
            a.setId(model.getauthorId().getId());
            var repository = new JdbcAuthorRepository(conection);
            repository.delete(a);
            statement.setString(1, model.getDNI());
            statement.executeUpdate();
            model.setDNI("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Set<PersonalInformation> getAll() {
        try (PreparedStatement statement = conection.prepareStatement("SELECT * FROM personal_information")) {
            ResultSet resultSet = statement.executeQuery();
            Set<PersonalInformation> personalInformations = new HashSet<>();
            while (resultSet.next()) {
                PersonalInformation personalInformation = new com.uvic.teknos.book.models.PersonalInformation();
                personalInformation.setDNI(resultSet.getString("DNI"));
                Author a  = new Author();
                var b = resultSet.getInt("Author_id");
                a.setId(b);
                personalInformation.setNationality(resultSet.getString("Nationality"));
                personalInformation.setDateOfBirth(resultSet.getDate("Date_of_Birth"));
                personalInformation.setPhone(resultSet.getInt("Phone"));
                personalInformation.setAddress(resultSet.getString("Address"));
                personalInformations.add(personalInformation);
            }
            return personalInformations;
        } catch (Exception e) {
            System.out.println("Error fetching personal information: " + e.getMessage());
            return null;

        }
    }


}




