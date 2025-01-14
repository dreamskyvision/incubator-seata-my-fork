/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.seata.common.loader;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * The type Enhanced service not found exception.
 *
 */
public class EnhancedServiceNotFoundException extends NestableRuntimeException {
    private static final long serialVersionUID = 7748438218914409019L;

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     */
    public EnhancedServiceNotFoundException(String errorCode) {
        super(errorCode);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param cause     the cause
     */
    public EnhancedServiceNotFoundException(String errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param errorDesc the error desc
     */
    public EnhancedServiceNotFoundException(String errorCode, String errorDesc) {
        super(errorCode + ":" + errorDesc);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param errorCode the error code
     * @param errorDesc the error desc
     * @param cause     the cause
     */
    public EnhancedServiceNotFoundException(String errorCode, String errorDesc, Throwable cause) {
        super(errorCode + ":" + errorDesc, cause);
    }

    /**
     * Instantiates a new Enhanced service not found exception.
     *
     * @param cause the cause
     */
    public EnhancedServiceNotFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
