
package com.uvic.teknos.book.repositorys;


import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.repositories.GenreRepository;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class JdbcGenreRepository implements GenreRepository {
    private final Connection conection;
    JdbcGenreRepository(Connection conection) {
        this.conection = conection;

    }
    @Override
    public Genre get(Integer id) {
        try (PreparedStatement statement = conection.prepareStatement(
                "SELECT * FROM genre WHERE Code =  ?", Statement.RETURN_GENERATED_KEYS)) {
            Genre genre = null;

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = new com.uvic.teknos.book.models.Genre();
                genre.setid(resultSet.getInt("Code"));
                genre.setdescription(resultSet.getString("Descripcion"));
            }
            return genre;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(Genre model) {
        if (model.getId() <= 0) {
            insert(model);}
        else {
            update(model);
        }
    }

    private void insert(Genre model) {
        try (PreparedStatement statement = conection.prepareStatement("INSERT INTO genre(Descripcion) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getdescription());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                model.setid(keys.getInt(1));
            }
        }catch (Exception e) {
            System.out.println("uh");
        }
    }
    private void update(Genre model) {
        try (PreparedStatement statement = conection.prepareStatement("UPDATE genre SET Descripcion = (?) WHERE Code = (?)")) {
            statement.setString(1, model.getdescription());
            statement.setInt(2, model.getId());
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



    @Override
    public Set<Genre> getAll() {
        try (PreparedStatement statement = conection.prepareStatement("SELECT  * from  genre")) {
            ResultSet resultat = statement.executeQuery();
            Set<Genre> genreSet = new HashSet<>();
            while (resultat.next()) {
                Genre genre = new com.uvic.teknos.book.models.Genre();
                genre.setid(resultat.getInt("Code"));
                genre.setdescription(resultat.getString("Descripcion"));
                genreSet.add(genre);
            }
            return  genreSet;


        }catch (Exception e) {
            System.out.println("error al all");
        }
        return null;
    }
    @Override
    public void delete(Genre model) {
        try {
            try (PreparedStatement statement1 = conection.prepareStatement("DELETE FROM book_pending WHERE Genre_Code = (?)");
                 PreparedStatement statement2 = conection.prepareStatement("DELETE FROM book_read WHERE Genre_Code = (?)");
                 PreparedStatement statement3 = conection.prepareStatement("DELETE FROM genre WHERE Code = (?)")) {
                conection.setAutoCommit(false);
                statement1.setInt(1, model.getId());

                statement2.setInt(1, model.getId());
                statement3.setInt(1, model.getId());
                statement1.executeUpdate();
                statement2.executeUpdate();
                int rowsAffected = statement3.executeUpdate();
                if (rowsAffected != 0) {
                    model.setid(0);
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



}
