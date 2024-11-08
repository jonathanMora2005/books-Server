package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.models.Author;
import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.repositories.AuthorRepository;
import com.jonathan.mybooklist.repositories.GenreRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcAuthorRepository implements AuthorRepository {
    private final Connection conection;
    JdbcAuthorRepository(Connection conection) {
        this.conection = conection;
    }
    @Override
    public void save(Author model) {
        if (model.getId() <= 0) {
            model.setId(insert(model));
        }
        else {
            update(model);
        }
    }

    private void update(Author model) {
        try (PreparedStatement statement = conection.prepareStatement("UPDATE author SET Name = ?, Surname = ? WHERE Author_id = ?")) {
            statement.setString(1, model.getnom());
            statement.setString(2, model.getsurname()); // Update description
            statement.setInt(3, model.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró ningún registro para actualizar con el ID: " + model.getId());
            } else {
                System.out.println("Actualización exitosa para el registro con el ID: " + model.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
        }
    }

    private int insert(Author model) {
        int generatedId = -1;
        try (PreparedStatement statement = conection.prepareStatement(
                "INSERT INTO author (Name, Surname) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getnom());
            statement.setString(2, model.getsurname());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 1) {
                System.out.println("Inserción exitosa para el autor: " + model.getnom());
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    System.out.println("No se pudo obtener el ID generado.");
                }
            } else {
                System.out.println("Error al insertar el registro (filas afectadas: " + rowsInserted + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }
        return generatedId;
    }


    @Override
    public void delete(Author model) {
        try {
            try (PreparedStatement statement1 = conection.prepareStatement("DELETE FROM book_pending WHERE Author_id = (?)");
                 PreparedStatement statement2 = conection.prepareStatement("DELETE FROM book_read WHERE Author_id = (?)");
                 PreparedStatement statement4 = conection.prepareStatement("DELETE FROM personal_information WHERE Author_id = (?)");
                 PreparedStatement statement3 = conection.prepareStatement("DELETE FROM author WHERE Author_id = (?)")) {
                conection.setAutoCommit(false);
                statement1.setInt(1, model.getId());
                statement4.setInt(1, model.getId());

                statement2.setInt(1, model.getId());
                statement3.setInt(1, model.getId());
                statement1.executeUpdate();
                statement2.executeUpdate();
                statement4.executeUpdate();
                int rowsAffected = statement3.executeUpdate();
                if (rowsAffected != 0) {
                    model.setId(0);
                }
                conection.commit();
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }finally {
            try {
                conection.setAutoCommit(true);
            }catch (Exception e) {
                System.out.println("Error en el auto commit");
            }
        }

    }

    @Override
    public Author get(Integer id) {
        try (PreparedStatement statement = conection.prepareStatement(
                "SELECT * FROM author WHERE Author_id =  ?", Statement.RETURN_GENERATED_KEYS)) {
            Author author = null;

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                author = new com.uvic.teknos.book.models.Author();
                author.setId(resultSet.getInt("Author_id"));
                author.setnom(resultSet.getString("Name"));
                author.setsurname(resultSet.getString("Surname"));
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Author> getAll() {
            try (PreparedStatement statement = conection.prepareStatement("SELECT  * from  author")) {
                ResultSet resultat = statement.executeQuery();
                Set<Author> authorSet = new HashSet<>();
                while (resultat.next()) {
                    Author author = new com.uvic.teknos.book.models.Author();
                    author.setId(resultat.getInt("Author_id"));
                    author.setnom(resultat.getString("Name"));
                    author.setsurname(resultat.getString("Surname"));
                    authorSet.add(author);
                }
                return  authorSet;


            }catch (Exception e) {
                System.out.println("error al all");
            }
            return null;
        }

}
