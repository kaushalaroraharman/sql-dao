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

package com.harman.ignite.sql.postgres.config;

import com.harman.ignite.sql.authentication.CredentialsProvider;
import com.harman.ignite.sql.authentication.DefaultPostgresDbCredentialsProvider;
import com.harman.ignite.sql.exception.SqlDaoException;
import com.harman.ignite.sql.postgress.config.PostgresDbConfig;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Connection;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link PostgresDbConfig}.
 *
 */
public class PostgresConfigJunitTest {

    @Mock
    ApplicationContext ctx;

    @InjectMocks
    PostgresDbConfig postgresDbConfig;

    @Mock
    DefaultPostgresDbCredentialsProvider defaultPostgresDbCredentialsProvider;

    @Mock
    CredentialsProvider credentialsProvider;

    @Mock
    Connection connection;

    public static final int THREE = 3;
    public static final int THIRTY_THREE = 30;

    @Test
    void testFailureDatasourceCreation() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(postgresDbConfig, "dataSourceRetryCount", THREE);
        ReflectionTestUtils.setField(postgresDbConfig, "dataSourceRetryDelay", THIRTY_THREE);
        ReflectionTestUtils.setField(postgresDbConfig, "connectionRetryCount", THREE);
        ReflectionTestUtils.setField(postgresDbConfig, "connectionRetryDelay", THIRTY_THREE);
        Exception e = assertThrows(SqlDaoException.class, () -> postgresDbConfig.initDataSource());
        assertTrue(e.getMessage().contains("Retry Attempts exhausted for creating the datasource"));
    }

    @Test
    void test()  {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(postgresDbConfig, "credentialProviderBeanName", "testCredentialsProvider");
        ReflectionTestUtils.setField(postgresDbConfig, "ctx", ctx);
        when(ctx.getBean(anyString())).thenReturn(new TestCredentialsProvider());
        postgresDbConfig.setCredentialsProvider();
        assertNull(postgresDbConfig.dataSource());
    }
}
