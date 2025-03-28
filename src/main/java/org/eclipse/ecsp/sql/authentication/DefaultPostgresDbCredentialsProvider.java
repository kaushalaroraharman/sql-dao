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

package org.eclipse.ecsp.sql.authentication;

import org.eclipse.ecsp.sql.dao.constants.PostgresDbConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Default database Credential Provider.
 * Refresh is not required for this implementation.
 * It only provides username and password as credentials.
 */
@Component("defaultPostgresDbCredentialsProvider")
public class DefaultPostgresDbCredentialsProvider implements PostgresDbCredentialsProvider {

    @Value("${" + PostgresDbConstants.POSTGRES_USERNAME + "}")
    private String userName;

    @Value("${" + PostgresDbConstants.POSTGRES_PASSWORD + "}")
    private String password;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void refreshCredentials() {
        // No refresh required for this implementation
    }

    @Override
    public Map<String, Object> getAllCredentialsConfig() {
        return new HashMap<>();
    }

    @Override
    public boolean isRefreshInProgress() {
        return false;
    }

}
