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


package org.eclipse.ecsp.sql.postgress.config;

import org.eclipse.ecsp.sql.authentication.CredentialsProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("testCredentialsProvider")
class TestCredentialsProvider implements CredentialsProvider {

    public boolean refresh = true;
    int temp = 0;

    @Override
    public String getUserName() {
        return "test";
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public Map<String, Object> getAllCredentialsConfig() {
        return new HashMap<>();
    }

    @Override
    public void refreshCredentials() {}

    @Override
    public boolean isRefreshInProgress() {
        if (refresh && temp == 1) {
            refresh = false;
        }
        temp++;
        return refresh;
    }

}
