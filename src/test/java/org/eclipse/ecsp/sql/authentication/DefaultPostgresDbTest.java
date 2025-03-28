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

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;

/**
 * The Class DefaultPostgresDbTest.
 */
class DefaultPostgresDbTest {

    /** The default postgres db credentials provider. */
    DefaultPostgresDbCredentialsProvider defaultPostgresDbCredentialsProvider
            = new DefaultPostgresDbCredentialsProvider();
    
    /**
     * Test get user name.
     */
    @Test
    void testgetUserName() {
        assertNull(defaultPostgresDbCredentialsProvider.getUserName());
    }

    /**
     * Test get password.
     */
    @Test
    void testgetPassword() {
        assertNull(defaultPostgresDbCredentialsProvider.getPassword());
    }
}
