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

package com.harman.ignite.sql.dao.constants;

/**
 * Constants for DB health check feature.
 */
public class HealthConstants {

    private HealthConstants() {
        throw new UnsupportedOperationException("HealthConstants is a constants class and cannot be instantiated");
    }

    public static final String HEALTH_POSTGRES_DB_MONITOR_ENABLED = "health.postgresdb.monitor.enabled";
    public static final String HEALTH_POSTGRES_DB_MONITOR_RESTART_ON_FAILURE =
            "health.postgresdb.monitor.restart.on.failure";

    public static final String HEALTH_POSTGRES_DB_MONITOR_NAME = "POSTGRESDB_HEALTH_MONITOR";
    public static final String HEALTH_POSTGRES_DB_MONTIOR_GUAGE = "POSTGRESDB_HEALTH_GUAGE";

    public static final String POOL_CONNECTIVITY_HEALTH_CHECK = ".pool.ConnectivityCheck";

    public static final String POOL_CONNECTION_99_PERCENT_HEALTH_CHECK = ".pool.Connection99Percent";

    public static final String POSTGRES_EXPECTED_99_PI_MS = "expected99thPercentileMs";

    public static final String POSTGRES_EXPECTED_99_PI_MS_VALUE = "postgres.expected99thPercentileMs";

}
