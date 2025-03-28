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

package org.eclipse.ecsp.sql.postgress.health;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import org.eclipse.ecsp.healthcheck.HealthService;
import org.eclipse.ecsp.healthcheck.HealthServiceCallBack;
import org.eclipse.ecsp.sql.dao.constants.PostgresDbConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Health service customization for PostgresDB.
 * <br>
 * It provides restart callback to the {@link HealthService}
 */
@Component
public class PostgresDbHealthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgresDbHealthService.class);

    @Autowired
    private DataSource datasource;
    @Autowired
    private HealthService healthService;

    @Value("${sp.restart.on.failure:false}")
    private boolean restartOnFailure;
    @Value("${" + PostgresDbConstants.POSTGRES_POOL_NAME + "}")
    private String poolName;

    /**
     * Register callback for PostgresDB health service.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("Registering callback for PostgresDbHealthService...");
        healthService.registerCallBack(new PostgresHealthServiceCallBack());
        LOGGER.info("Starting health service executor...");
        healthService.startHealthServiceExecutor();
    }

    class PostgresHealthServiceCallBack implements HealthServiceCallBack {

        @Override
        public boolean performRestart() {
            if (restartOnFailure) {
                ((HikariDataSource) datasource).close();
            }
            return restartOnFailure;
        }
    }

}
