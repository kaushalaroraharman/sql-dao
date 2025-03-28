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

import com.codahale.metrics.health.HealthCheck;
import org.springframework.stereotype.Component;

/**
 * Metric for health check of PostgresDB.
 */
@Component
public class PostgresDbHealthCheck extends HealthCheck {

    /**
     * Check if database is healthy.
     * Implement custom health check to be registered to HealthCheckRegistry
     *
     * @return the result
     * @throws Exception the exception
     */
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
