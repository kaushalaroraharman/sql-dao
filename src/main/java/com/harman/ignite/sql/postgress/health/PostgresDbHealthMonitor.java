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
import com.harman.ignite.sql.dao.constants.HealthConstants;
import com.harman.ignite.sql.dao.constants.PostgresDbConstants;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.eclipse.ecsp.healthcheck.HealthMonitor;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Health monitor for PostgresDB.
 * <br>
 * {@link org.eclipse.ecsp.healthcheck.HealthService} will use this health monitor to monitor the health of
 * PostgresDB.
 * <br>
 * Callback for restart on unhealthy health monitor is implemented by {@link PostgresDbHealthService}
 */
@Component
public class PostgresDbHealthMonitor implements HealthMonitor {

    private static IgniteLogger logger = IgniteLoggerFactory.getLogger(PostgresDbHealthMonitor.class);

    @Autowired
    private DataSource datasource;

    @Value("${" + HealthConstants.HEALTH_POSTGRES_DB_MONITOR_ENABLED + ": true }")
    private boolean postgresDbHealthMonitorEnabled;

    @Value("${" + HealthConstants.HEALTH_POSTGRES_DB_MONITOR_RESTART_ON_FAILURE + ": true }")
    private boolean postgresDbRestartOnFailure;

    @Value("${" + PostgresDbConstants.POSTGRES_POOL_NAME + "}")
    private String poolName;

    List<String> healthCheckList;

    /**
     * init().
     */
    @PostConstruct
    public void init() {
        healthCheckList = new ArrayList<>();
        healthCheckList.add(poolName + HealthConstants.POOL_CONNECTIVITY_HEALTH_CHECK);
        healthCheckList.add(poolName + HealthConstants.POOL_CONNECTION_99_PERCENT_HEALTH_CHECK);
    }

    @Override
    public boolean isHealthy(boolean forceHealthCheck) {

        if (datasource == null) {
            return false;
        }
        HealthCheckRegistry healthCheckRegistry = (HealthCheckRegistry)
                ((HikariDataSource) datasource).getHealthCheckRegistry();
        for (String healthCheckName : healthCheckList) {
            HealthCheck.Result healthCheckResult = healthCheckRegistry.getHealthCheck(healthCheckName).execute();
            logger.info("Health check result for {} - {} - isHealthy: {}",
                    HealthConstants.HEALTH_POSTGRES_DB_MONITOR_NAME,
                    healthCheckName,
                    healthCheckResult.isHealthy());
            if (Boolean.FALSE.equals(healthCheckResult.isHealthy())) {
                logger.error("Health check failed for {} - {}, with message {}, Error : {}",
                        HealthConstants.HEALTH_POSTGRES_DB_MONITOR_NAME,
                        healthCheckName,
                        healthCheckResult.getMessage(),
                        healthCheckResult.getError());
                return false;
            }
        }
        return true;
    }

    @Override
    public String monitorName() {
        return HealthConstants.HEALTH_POSTGRES_DB_MONITOR_NAME;
    }

    @Override
    public boolean needsRestartOnFailure() {
        return postgresDbRestartOnFailure;
    }

    @Override
    public String metricName() {
        return HealthConstants.HEALTH_POSTGRES_DB_MONTIOR_GUAGE;
    }

    @Override
    public boolean isEnabled() {
        return postgresDbHealthMonitorEnabled;
    }

}
