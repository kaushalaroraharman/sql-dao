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

    /** The user name. */
    @Value("${" + PostgresDbConstants.POSTGRES_USERNAME + "}")
    private String userName;

    /** The password. */
    @Value("${" + PostgresDbConstants.POSTGRES_PASSWORD + "}")
    private String password;

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    @Override
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the password.
     *
     * @return the password as String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Refresh credentials.
     */
    @Override
    public void refreshCredentials() {
        // No refresh required for this implementation
    }

    /**
     * Gets all credentials config.
     *
     * @return Map{@code <}String{@code >}{@code <}Object{@code >}
     */
    @Override
    public Map<String, Object> getAllCredentialsConfig() {
        return new HashMap<>();
    }

    /**
     * Checks if refresh is in progress.
     *
     * @return true, if refresh is in progress
     */
    @Override
    public boolean isRefreshInProgress() {
        return false;
    }

}
