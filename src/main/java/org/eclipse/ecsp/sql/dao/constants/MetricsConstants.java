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

package org.eclipse.ecsp.sql.dao.constants;

/**
 * Constants for implementing feature to publish Postgres DB metrics to Prometheus.
 */
public class MetricsConstants {

    /**
     * Private constructor for metrics constants.
     */
    private MetricsConstants() {
        throw new UnsupportedOperationException("MetricsConstants is a constants class and cannot be instantiated");
    }

    /** The Constant PROMETHEUS_ENABLED. */
    public static final String PROMETHEUS_ENABLED = "metrics.prometheus.enabled";
    
    /** The Constant PROMETHEUS_AGENT_PORT_KEY. */
    public static final String PROMETHEUS_AGENT_PORT_KEY = "prometheus.agent.port";
    
    /** The Constant PROMETHEUS_AGENT_PORT_EXPOSED. */
    public static final String PROMETHEUS_AGENT_PORT_EXPOSED = "prometheus.agent.port.exposed";
    
    /** The Constant POSTGRES_DB_METRICS_ENABLED. */
    public static final String POSTGRES_DB_METRICS_ENABLED = "postgresdb.metrics.enabled";
    
    /** The Constant POSTGRES_DB_METRICS_THREAD_INITIAL_DELAY_MS. */
    public static final String POSTGRES_DB_METRICS_THREAD_INITIAL_DELAY_MS
            = "postgresdb.metrics.thread.initial.delay.ms";
    
    /** The Constant POSTGRES_DB_METRICS_THREAD_FREQUENCY_MS. */
    public static final String POSTGRES_DB_METRICS_THREAD_FREQUENCY_MS
            = "postgresdb.metrics.thread.freq.ms";
    
    /** The Constant POSTGRES_DB_METRICS_EXECUTOR_SHUTDOWN_BUFFER_MS. */
    public static final String POSTGRES_DB_METRICS_EXECUTOR_SHUTDOWN_BUFFER_MS
            = "postgresdb.metrics.executor.shutdown.buffer.ms";
    
    /** The Constant POSTGRES_METRIC_IDLE_CONNECTIONS. */
    public static final String POSTGRES_METRIC_IDLE_CONNECTIONS = ".pool.IdleConnections";
    
    /** The Constant POSTGRES_METRIC_ACTIVE_CONNECTIONS. */
    public static final String POSTGRES_METRIC_ACTIVE_CONNECTIONS = ".pool.ActiveConnections";
    
    /** The Constant POSTGRES_METRIC_PENDING_CONNECTIONS. */
    public static final String POSTGRES_METRIC_PENDING_CONNECTIONS = ".pool.PendingConnections";
    
    /** The Constant POSTGRES_METRIC_TOTAL_CONNECTIONS. */
    public static final String POSTGRES_METRIC_TOTAL_CONNECTIONS = ".pool.TotalConnections";
    
    /** The Constant SERVICE_NAME. */
    public static final String SERVICE_NAME = "service.name";

}
