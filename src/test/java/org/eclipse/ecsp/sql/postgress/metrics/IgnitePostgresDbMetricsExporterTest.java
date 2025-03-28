/*
 *
 *
 *   ******************************************************************************
 *
 *    Copyright (c) 2023-24 Harman International
 *
 *
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *
 *    you may not use this file except in compliance with the License.
 *
 *    You may obtain a copy of the License at
 *
 *
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 *    Unless required by applicable law or agreed to in writing, software
 *
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *    See the License for the specific language governing permissions and
 *
 *    limitations under the License.
 *
 *
 *
 *    SPDX-License-Identifier: Apache-2.0
 *
 *    *******************************************************************************
 *
 *
 */

package org.eclipse.ecsp.sql.postgress.metrics;

import org.eclipse.ecsp.sql.authentication.DefaultPostgresDbCredentialsProvider;
import org.eclipse.ecsp.sql.postgress.config.PostgresDbConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for {@link IgnitePostgresDbMetricsExporter}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DefaultPostgresDbCredentialsProvider.class, PostgresDbConfig.class,
    IgnitePostgresDbGuage.class, IgnitePostgresDbMetricsExporter.class })
@TestPropertySource("/application-test.properties")
public class IgnitePostgresDbMetricsExporterTest {

    @Autowired
    private IgnitePostgresDbGuage postgresDbGuage;

    @Autowired
    private IgnitePostgresDbMetricsExporter ignitePostgresDbMetricsExporter;

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15").withDatabaseName("test")
            .withUsername("root").withPassword("root");

    @BeforeAll
    public static void setUpPostgres() {
        postgresqlContainer.start();
        System.setProperty("DB_URL", postgresqlContainer.getJdbcUrl());
    }

    @Test
    void testConnectionPool() {
        assertNotEquals(1.0, postgresDbGuage.get("hikariConnectionPool.pool.TotalConnections",
                "test", "localhost"), 0.0);
        assertNotEquals(1.0, postgresDbGuage.get("hikariConnectionPool.pool.ActiveConnections",
                "test", "localhost"), 0.0);
        assertNotEquals(1.0, postgresDbGuage.get("hikariConnectionPool.pool.IdleConnections",
                "test", "localhost"), 0.0);
        assertNotEquals(1.0, postgresDbGuage.get("hikariConnectionPool.pool.PendingConnections",
                "test", "localhost"), 0.0);
    }

    @Test
    void testMetrics() {
        final int two = 2;
        final float value = 2.0f;
        postgresDbGuage.set(two, "test2", "test2", "test2");
        assertEquals(value, postgresDbGuage.get("test2", "test", "test"), value);
    }

    @Test
    void testInvalidMetrics() {
        assertEquals(0.0, postgresDbGuage.get("test", "test", "test"), 0.0);
    }

    @AfterAll
    public static void tearUpPostgresServer() {
        postgresqlContainer.stop();
    }
}


