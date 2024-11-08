package com.uvic.teknos.book.repositorys;

import com.jonathan.mybooklist.models.Publishing;
import com.jonathan.mybooklist.models.ReadBook;
import com.jonathan.mybooklist.repositories.BookreadRepository;
import com.jonathan.mybooklist.repositories.PublishingRepository;
import com.uvic.teknos.book.models.Publshing;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class JdbcReadBookRepository implements BookreadRepository {
    private final Connection conection;
    JdbcReadBookRepository(Connection conection) {
        this.conection = conection;
    }
    @Override
    public void save(ReadBook model) {
        if (model.getidBookRead() <= 0) {
            insert(model);}
        else {
            update(model);
        }
    }
    private void insert(ReadBook model) {
        try (PreparedStatement statement = conection.prepareStatement("INSERT INTO book_read values (?) , (?) , (?), (?), (?), (?), (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, model.getname());
            statement.setDate(2,model.getpublicationDate());
            statement.setInt(3,model.getpages());
            statement.setString(4,model.getdescription());
            statement.setInt(5,model.getauthorId());
            statement.setInt(6,model.getpublishingId());
            statement.setInt(7,model.getgenreCode());
            statement.executeUpdate();
            var keys = statement.getGeneratedKeys();
            if (keys.next()) {
                model.setidBookRead(keys.getInt(1));
            }
        }catch (Exception e) {
            System.out.println("uh");
        }
    }
    private void update(ReadBook model) {
        try (PreparedStatement statement = conection.prepareStatement("INSERT INTO book_read (Name,Publication_date,Pages,Description,Author_id,Publishing_id,Genre_Code) VALUES (?, ?, ?, ?, ?, ? ,?)")) {
            statement.setString(1, model.getname());
            statement.setDate(2,model.getpublicationDate());
            statement.setInt(3,model.getpages());
            statement.setString(4,model.getdescription());
            statement.setInt(5,model.getauthorId());
            statement.setInt(6,model.getpublishingId());
            statement.setInt(7,model.getgenreCode());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró ningún registro para actualizar con el ID: " + model.getidBookRead());
            } else {
                System.out.println("Actualización exitosa para el registro con el ID: " + model.getidBookRead());
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
        }
    }

    @Override
    public void delete(ReadBook model) {

    }

    @Override
    public ReadBook get(Integer id) {
        try (PreparedStatement statement = conection.prepareStatement(
                "SELECT * FROM book_read WHERE ID_book_read =  ?", Statement.RETURN_GENERATED_KEYS)) {
            ReadBook readBook = null;

            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                readBook = new com.uvic.teknos.book.models.ReadBook();
                readBook.setidBookRead(id);
                readBook.setname(resultSet.getString("Name"));
                readBook.setpublicationDate(resultSet.getDate("Publication_date"));
                readBook.setpages(resultSet.getInt("Pages"));
                readBook.setdescription(resultSet.getString("Description"));
                readBook.setauthorId(resultSet.getInt("Author_id"));
                readBook.publishingId(resultSet.getInt("Publishing_id"));
                readBook.setgenreCode(resultSet.getInt("Genre_Code"));
            }
            return readBook;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<ReadBook> getAll() {
        try (PreparedStatement statement = conection.prepareStatement("SELECT  * from  book_read")) {
            ResultSet resultat = statement.executeQuery();
            Set<ReadBook> readBookHashSet = new HashSet<>();
            while (resultat.next()) {
                com.uvic.teknos.book.models.ReadBook readBook = new com.uvic.teknos.book.models.ReadBook();
                readBook.setname(resultat.getString("Name"));
                readBook.setpublicationDate(resultat.getDate("Publication_date"));
                readBook.setpages(resultat.getInt("Pages"));
                readBook.setdescription(resultat.getString("Description"));
                readBook.setauthorId(resultat.getInt("Author_id"));
                readBook.publishingId(resultat.getInt("Publishing_id"));
                readBook.setgenreCode(resultat.getInt("Genre_Code"));
                readBookHashSet.add(readBook);
            }
            return  readBookHashSet;


        }catch (Exception e) {
            System.out.println("error al all");
        }
        return null;
    }
    }

