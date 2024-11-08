package com.fcardara.dbtestutils.junit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({CreateSchemaExtension.class, GetConnectionExtension.class})
class CreateSchemaExtensionTest { 
    private final Connection connection;

    CreateSchemaExtensionTest(Connection connection) {
        this.connection = connection;
    }

    @Test
    void testConnection() {
        assertNotNull(connection);
    } 
}