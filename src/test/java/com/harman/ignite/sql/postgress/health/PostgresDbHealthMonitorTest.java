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

package com.harman.ignite.sql.postgress.health;

import com.harman.ignite.sql.authentication.DefaultPostgresDbCredentialsProvider;
import com.harman.ignite.sql.postgress.config.PostgresDbConfig;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *Test class for {@link PostgresDbHealthService}.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DefaultPostgresDbCredentialsProvider.class,
    PostgresDbConfig.class, PostgresDbHealthMonitor.class, PostgresDbHealthCheck.class })
@TestPropertySource("/application-test.properties")
public class PostgresDbHealthMonitorTest {

    @Autowired
    PostgresDbHealthMonitor postgresDbHealthMonitor;

    @Autowired
    PostgresDbHealthCheck postgresDbHealthCheck;

    @Container
    static PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15").withDatabaseName("test")
            .withUsername("root").withPassword("root");

    @BeforeAll
    public static void setUpPostgres() {
        postgresqlContainer.start();
        System.setProperty("DB_URL", postgresqlContainer.getJdbcUrl());
    }

    @Test
    void testHealthy() {
        boolean healthy = postgresDbHealthMonitor.isHealthy(true);
        assertTrue(healthy);
    }

    @Test
    void testDiagnosticMonitorName() {
        assertEquals("POSTGRESDB_HEALTH_MONITOR", postgresDbHealthMonitor.monitorName());
    }

    @Test
    void testDiagnosticMetricName() {
        assertEquals("POSTGRESDB_HEALTH_GUAGE", postgresDbHealthMonitor.metricName());
    }

    @Test
    void testMonitorEnabled() {
        assertTrue(postgresDbHealthMonitor.isEnabled());
    }

    @Test
    void testNeedsRestartOnFailure() {
        assertTrue(postgresDbHealthMonitor.needsRestartOnFailure());
    }

    @Test
    void testcheckHealthResult() throws Exception {
        assertNotNull(postgresDbHealthCheck.check());
    }

    @AfterAll
    public static void tearUpPostgresServer() {
        postgresqlContainer.stop();
    }
}


