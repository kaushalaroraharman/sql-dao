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
 * Constants for feature implementing a pooled connection to Postgres DB.
 */
public class PostgresDbConstants {

    private PostgresDbConstants() {
        throw new UnsupportedOperationException("PostgresDbConstants is a constants class and cannot be instantiated");
    }

    /** Postgres DB pooled connection properties. */
    public static final String POSTGRES_JDBC_URL = "postgres.jdbc.url";
    public static final String POSTGRES_USERNAME = "postgres.username";
    public static final String POSTGRES_PASSWORD = "postgres.password";
    public static final String POSTGRES_DRIVER_CLASS_NAME = "postgres.driver.class.name";
    public static final String POSTGRES_POOL_NAME = "postgres.pool.name";
    public static final String POSTGRES_MIN_POOL_SIZE = "postgres.min.pool.size";
    public static final String POSTGRES_MAX_POOL_SIZE = "postgres.max.pool.size";
    public static final String POSTGRES_MAX_IDLE_TIME = "postgres.max.idle.time";
    public static final String POSTGRES_CONNECTION_TIMEOUT_MS = "postgres.connection.timeout.ms";
    public static final String POSTGRES_DS_CACHE_PREPARED_STATEMENTS = "cachePrepStmts";
    public static final String POSTGRES_DS_PREPARED_STATEMENT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String POSTGRES_DS_PREPARED_STATEMENT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    public static final String POSTGRES_DS_CACHE_PREPARED_STATEMENTS_VALUE

            = "postgres.data-source-properties.cachePrepStmts";
    public static final String POSTGRES_DS_PREPARED_STATEMENT_CACHE_SIZE_VALUE
            = "postgres.data-source-properties.prepStmtCacheSize";
    public static final String POSTGRES_DS_PREPARED_STATEMENT_CACHE_SQL_LIMIT_VALUE
            = "postgres.data-source-properties.prepStmtCacheSqlLimit";

    public static final String PROMETHEUS_ENABLED = "";
    public static final String ONE_WAY_TLS_AUTH_MECHANISM = "one-way-tls";
    public static final int FIVE_THOUSAND = 5000;
}
