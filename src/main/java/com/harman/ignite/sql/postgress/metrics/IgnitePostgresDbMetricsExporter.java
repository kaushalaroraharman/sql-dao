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

package com.harman.ignite.sql.postgress.metrics;

import com.codahale.metrics.MetricRegistry;
import com.harman.ignite.sql.dao.constants.MetricsConstants;
import com.harman.ignite.sql.dao.constants.PostgresDbConstants;
import com.zaxxer.hikari.HikariDataSource;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.ecsp.healthcheck.ThreadUtils;
import org.eclipse.ecsp.utils.logger.IgniteLogger;
import org.eclipse.ecsp.utils.logger.IgniteLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Fetches PostgresDB's metrics from the datasource through a Scheduled thread executor
 * at configured time interval and sets the value of each metric to its corresponding Prometheus guage.
 *
 * @author karora
 */
@Component
public class IgnitePostgresDbMetricsExporter {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(IgnitePostgresDbMetricsExporter.class);

    @Value("${" + MetricsConstants.POSTGRES_DB_METRICS_ENABLED + ":false}")
    private boolean postgresDbMetricsEnabled;

    @Value("${" + MetricsConstants.PROMETHEUS_ENABLED + ":false}")
    private boolean prometheusEnabled;

    @Value("${" + MetricsConstants.POSTGRES_DB_METRICS_THREAD_INITIAL_DELAY_MS + ":2000}")
    private int threadInitialDelay;

    @Value("${" + MetricsConstants.POSTGRES_DB_METRICS_THREAD_FREQUENCY_MS + ":5000}")
    private int threadFrequency;

    @Value("${" + MetricsConstants.SERVICE_NAME + "}")
    private String svc;

    @Value("${NODE_NAME:localhost}")
    private String nodeName;

    @Value("${" + MetricsConstants.POSTGRES_DB_METRICS_EXECUTOR_SHUTDOWN_BUFFER_MS + ":2000}")
    private int shutdownBuffer;

    @Autowired
    private IgnitePostgresDbGuage postgresDbGauge;

    @Autowired
    private DataSource datasource;

    private ScheduledExecutorService postgresDbMetricsExecutor;

    @Value("${" + PostgresDbConstants.POSTGRES_POOL_NAME + "}")
    private String poolName;

    @Value("${" + MetricsConstants.PROMETHEUS_AGENT_PORT_KEY + ":"
            + MetricsConstants.PROMETHEUS_AGENT_PORT_EXPOSED + "}")
    private int prometheusExportPort;

    private HTTPServer prometheusExportServer;

    List<String> metricsList;

    /**
     * init().
     */
    @PostConstruct
    public void init() {
        if (postgresDbMetricsEnabled) {
            startPrometheusServer();
            postgresDbGauge.setup();
            createMetricsList();
            createPostgresDbMetricsExecutor();
            startPostgresDbMetricsExecutor();
        }
    }

    private void createMetricsList() {
        metricsList = new ArrayList<>();
        metricsList.add(poolName + MetricsConstants.POSTGRES_METRIC_TOTAL_CONNECTIONS);
        metricsList.add(poolName + MetricsConstants.POSTGRES_METRIC_ACTIVE_CONNECTIONS);
        metricsList.add(poolName + MetricsConstants.POSTGRES_METRIC_IDLE_CONNECTIONS);
        metricsList.add(poolName + MetricsConstants.POSTGRES_METRIC_PENDING_CONNECTIONS);
    }

    private void startPrometheusServer() {
        if (prometheusEnabled) {
            try {
                prometheusExportServer = new HTTPServer(prometheusExportPort, true);
                DefaultExports.initialize();
            } catch (IOException e) {
                LOGGER.error("Error encountered when initializing prometheus server", e);
            }
        }
    }

    private void createPostgresDbMetricsExecutor() {
        postgresDbMetricsExecutor = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread t = Executors.defaultThreadFactory().newThread(runnable);
            t.setDaemon(true);
            t.setUncaughtExceptionHandler((thread, throwable) ->
                    LOGGER.error("Uncaught exception in thread {} {}", thread.getName(), throwable)
            );
            t.setName(Thread.currentThread().getName() + ":" + "postgresDBMetricsExecutor");
            return t;
        });
        LOGGER.debug("Scheduled thread executor for PostgresDB metrics created successfully.");
    }

    private void startPostgresDbMetricsExecutor() {
        LOGGER.info("Starting the PostgresDB metrics executor...");
        postgresDbMetricsExecutor.scheduleWithFixedDelay(this::fetchMetrics,
                threadInitialDelay, threadFrequency, TimeUnit.MILLISECONDS);
    }

    private void fetchMetrics() {

        if (null != datasource) {
            MetricRegistry metricRegistry = ((MetricRegistry) ((HikariDataSource) datasource).getMetricRegistry());
            for (String metricName : metricsList) {
                int metricValue = (Integer) metricRegistry.gauge(metricName).getValue();
                LOGGER.info("Postgres Metric - {} : {} : ", metricName, metricValue);
                exportMetrics(metricValue, metricName);
            }
        }
    }

    private void exportMetrics(double val, String metricName) {
        postgresDbGauge.set(val, metricName, svc, nodeName);
    }

    /**
     * destroys the open resources.
     */
    @PreDestroy
    public void close() {
        if (postgresDbMetricsExecutor != null && !postgresDbMetricsExecutor.isShutdown()) {
            LOGGER.info("Shutting down the PostgresDB metrics executor...");
            ThreadUtils.shutdownExecutor(postgresDbMetricsExecutor, shutdownBuffer, false);
        }
        prometheusExportServer.stop();
    }
}
