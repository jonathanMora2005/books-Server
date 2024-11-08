package com.fcardara.dbtestutils.junit;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.stream.Collectors;

import com.fcardara.dbtestutils.exceptions.DbAssertionException;

public class DbAssertion {
    private final Connection connection;

    DbAssertion(Connection connection) {
        this.connection = connection;
    }

    private String table;
    private String column;
    private Map<String, Object> whereConditions = new HashMap<>();

    public DbAssertion table(String table) {
        this.table = table;

        return this;
    }

    public DbAssertion column(String column) {
        this.column = column;

        return this;
    }

    public DbAssertion where(String column, Object value) {
        whereConditions.put(column, value);

        return this;
    }

    public DbAssertion and(String column, Object value) {
        whereConditions.put(column, value);

        return this;
    }

    public void hasOneLine() {
        column = "COUNT(*) AS NUMBER_OF_LINES";
        var select = composeSelect();
        try (var statement = connection.prepareStatement(select)) {
            var index = 1;
            for (var value : whereConditions.values()) {
                statement.setObject(index++, value);
            }

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                assertEquals(1, resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DbAssertionException(e);
        }
    }

    public void valueEqual(Object value) {
        var select = composeSelect();
        try (var statement = connection.prepareStatement(select)) {
            var index = 1;
            for (var whereValue : whereConditions.values()) {
                statement.setObject(index++, whereValue);
            }

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                assertEquals(value.toString(), resultSet.getObject(1).toString());
            }

        } catch (SQLException e) {
            throw new DbAssertionException(e);
        }
    }

    public void doesNotExist() {
        var select = composeSelect();
        try (var statement = connection.prepareStatement(select)) {
            var index = 1;
            for (var whereValue : whereConditions.values()) {
                statement.setObject(index++, whereValue);
            }

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                assertEquals(0, resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DbAssertionException(e);
        }
    }

    private String composeSelect() {
        var select = new StringBuilder("SELECT ");

        if (column != null && !column.isBlank()) {
            select.append(column);
        } else {
            select.append("count(*)");
        }
        select.append(" FROM " + table + " ");

        if (!whereConditions.isEmpty()) {
            select.append(whereConditions.keySet().stream().map(s -> s + " = ?")
                    .collect(Collectors.joining(" AND ", "WHERE ", "")));
        }

        return select.toString();
    }

    public void hasLines(int lines) {
        var select = composeSelect();
        try (var statement = connection.prepareStatement(select)) {
            var index = 1;
            for (var whereValue : whereConditions.values()) {
                statement.setObject(index++, whereValue);
            }

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                assertEquals(lines, resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DbAssertionException(e);
        }
    }
}
