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
package io.seata.common.exception;

/**
 * the store exception
 *
 */
public class StoreException extends FrameworkException {

    /**
     * Instantiates a new Store exception.
     *
     * @param err the err
     */
    public StoreException(FrameworkErrorCode err) {
        super(err);
    }

    /**
     * Instantiates a new Store exception.
     *
     * @param msg the msg
     */
    public StoreException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Store exception.
     *
     * @param msg     the msg
     * @param errCode the err code
     */
    public StoreException(String msg, FrameworkErrorCode errCode) {
        super(msg, errCode);
    }

    /**
     * Instantiates a new Store exception.
     *
     * @param cause   the cause
     * @param msg     the msg
     * @param errCode the err code
     */
    public StoreException(Throwable cause, String msg, FrameworkErrorCode errCode) {
        super(cause, msg, errCode);
    }

    /**
     * Instantiates a new Store exception.
     *
     * @param th the th
     */
    public StoreException(Throwable th) {
        super(th);
    }

    /**
     * Instantiates a new Store exception.
     *
     * @param th  the th
     * @param msg the msg
     */
    public StoreException(Throwable th, String msg) {
        super(th, msg);
    }
}
