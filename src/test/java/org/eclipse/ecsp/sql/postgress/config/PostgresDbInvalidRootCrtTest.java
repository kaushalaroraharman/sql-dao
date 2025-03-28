package org.eclipse.ecsp.sql.postgress.config;

import org.eclipse.ecsp.sql.authentication.DefaultPostgresDbCredentialsProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class for {@link PostgresDbConfig}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PostgresDbConfig.class, DefaultPostgresDbCredentialsProvider.class })
@TestPropertySource("/application-ssl-invalid-crt-test.properties")
@Disabled
class PostgresDbInvalidRootCrtTest {

    @Autowired
    DefaultPostgresDbCredentialsProvider defaultPostgresDbCredentialsProvider;

    @Autowired
    private DataSource dataSource;

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15").withDatabaseName("test")
          .withUsername("root").withPassword("root");

    @BeforeAll
     public static void setUpPostgres() {
        postgresqlContainer.start();
        System.setProperty("DB_URL", postgresqlContainer.getJdbcUrl());
    }

    @Test
    void testConnection() throws SQLException {
        assertNull(dataSource.getConnection());
    }


    @AfterAll
    public static void tearUpPostgresServer() {
        postgresqlContainer.stop();
    }
}