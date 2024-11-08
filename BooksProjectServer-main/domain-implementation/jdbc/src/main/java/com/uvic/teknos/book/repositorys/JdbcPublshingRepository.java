package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.models.Genre;
import com.jonathan.mybooklist.models.Publishing;
import com.jonathan.mybooklist.repositories.PublishingRepository;
import com.uvic.teknos.book.models.Publshing;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcPublshingRepository implements PublishingRepository {
    private final Connection conection;
    JdbcPublshingRepository(Connection conection) {
        this.conection = conection;
    }
    @Override
    public void save(Publishing model) {
        if (model.getpublishingid() <= 0) {
            insert(model);}
        else {
            update(model);
        }
    }

    private void insert(Publishing model) {
        try (PreparedStatement statement = conection.prepareStatement("INSERT INTO publishing values (?) , (?) , (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getname());
            statement.setString(2, model.getemail());
            statement.setString(3, model.getcountry());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                model.setpublishingid(keys.getInt(1));
            }
        }catch (Exception e) {
            System.out.println("uh");
        }
    }
    private void update(Publishing model) {
        try (PreparedStatement statement = conection.prepareStatement("UPDATE publishing SET Name = ? , Email = ? , Country = ? WHERE Publishing_id = ?")) {
            statement.setString(1, model.getname()); // Assuming newName is the new value for Name
            statement.setString(2, model.getemail()); // Assuming newEmail is the new value for Email
            statement.setString(3, model.getcountry()); // Assuming newCountry is the new value for Country
            statement.setInt(4, model.getpublishingid());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró ningún registro para actualizar con el ID: " + model.getpublishingid());
            } else {
                System.out.println("Actualización exitosa para el registro con el ID: " + model.getpublishingid());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
        }
    }
    @Override
    public void delete(Publishing model) {
        try {
            try (PreparedStatement statement1 = conection.prepareStatement("DELETE FROM book_pending WHERE Publishing_id = (?)");
                 PreparedStatement statement2 = conection.prepareStatement("DELETE FROM book_read WHERE Publishing_id = (?)");
                 PreparedStatement statement3 = conection.prepareStatement("DELETE FROM publishing WHERE Publishing_id = (?)")) {
                conection.setAutoCommit(false);
                statement1.setInt(1, model.getpublishingid());

                statement2.setInt(1,model.getpublishingid());
                statement3.setInt(1, model.getpublishingid());
                statement1.executeUpdate();
                statement2.executeUpdate();
                int rowsAffected = statement3.executeUpdate();
                if (rowsAffected != 0) {
                    model.setpublishingid(0);
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
    public Publishing get(Integer id) {
        try (PreparedStatement statement = conection.prepareStatement(
                "SELECT * FROM publishing WHERE Publishing_id =  ?", Statement.RETURN_GENERATED_KEYS)) {
            Publishing publishing = null;

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                publishing = new Publshing();
                publishing.setpublishingid(resultSet.getInt("Publishing_id"));
                publishing.setname(resultSet.getString("Name"));
                publishing.setemail(resultSet.getString("Email"));
                publishing.setcountry(resultSet.getString("Country"));
            }
            return publishing;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Publishing> getAll() {
        try (PreparedStatement statement = conection.prepareStatement("SELECT  * from  publishing")) {
            ResultSet resultat = statement.executeQuery();
            Set<Publishing> genreSet = new HashSet<>();
            while (resultat.next()) {
                Publishing genre = new Publshing();
                genre.setpublishingid(resultat.getInt("Publishing_id"));
                genre.setname(resultat.getString("Name"));
                genre.setemail(resultat.getString("Email"));
                genre.setcountry(resultat.getString("Country"));
                genreSet.add(genre);
            }
            return  genreSet;


        }catch (Exception e) {
            System.out.println("error al all");
        }
        return null;
    }
}
