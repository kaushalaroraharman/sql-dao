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

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *Test class for {@link PostgresDbHealthMonitor}.
 */
class PostgresDbHealthMonitorUnitTest {

    @InjectMocks
    PostgresDbHealthMonitor postgresDbHealthMonitor;

    @Mock
    PostgresDbHealthCheck postgresDbHealthCheck;

    HikariDataSource dataSource;

    @Mock
    HealthCheckRegistry healthCheckRegistry;

    @Mock
    HealthCheck healthCheck;

    @Mock
    HealthCheck.Result result;

    @Test
    void testunHealthy() {
        MockitoAnnotations.openMocks(this);
        boolean healthy = postgresDbHealthMonitor.isHealthy(true);
        assertFalse(healthy);
    }

    @Test
    void testFailedHealthCheck() {
        dataSource = mock(HikariDataSource.class);
        MockitoAnnotations.openMocks(this);
        postgresDbHealthMonitor.init();
        when(dataSource.getHealthCheckRegistry()).thenReturn(healthCheckRegistry);
        when(healthCheckRegistry.getHealthCheck(anyString())).thenReturn(healthCheck);
        when(healthCheck.execute()).thenReturn(result);
        boolean healthy = postgresDbHealthMonitor.isHealthy(true);
        assertFalse(healthy);
    }


}


