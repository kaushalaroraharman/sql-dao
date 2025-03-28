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

import java.util.Map;

/**
 * Contract for any database credential provider.
 */
public interface CredentialsProvider {

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName();

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword();

    /**
     * Gets all the credentials configuration.
     *
     * @return Map{@code <}String{@code >}{@code <}Object{@code >}
     */
    public Map<String, Object> getAllCredentialsConfig();

    /**
     * Refresh credentials.
     */
    public void refreshCredentials();

    /**
     * Checks if refresh is in progress.
     *
     * @return true, if refresh is in progress
     */
    public boolean isRefreshInProgress();

}
