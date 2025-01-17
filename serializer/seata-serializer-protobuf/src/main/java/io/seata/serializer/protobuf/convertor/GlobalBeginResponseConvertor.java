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
package io.seata.serializer.protobuf.convertor;

import io.seata.serializer.protobuf.generated.AbstractMessageProto;
import io.seata.serializer.protobuf.generated.AbstractResultMessageProto;
import io.seata.serializer.protobuf.generated.AbstractTransactionResponseProto;
import io.seata.serializer.protobuf.generated.GlobalBeginResponseProto;
import io.seata.serializer.protobuf.generated.MessageTypeProto;
import io.seata.serializer.protobuf.generated.ResultCodeProto;
import io.seata.serializer.protobuf.generated.TransactionExceptionCodeProto;
import io.seata.core.exception.TransactionExceptionCode;
import io.seata.core.protocol.ResultCode;
import io.seata.core.protocol.transaction.GlobalBeginResponse;

/**
 */
public class GlobalBeginResponseConvertor implements PbConvertor<GlobalBeginResponse, GlobalBeginResponseProto> {
    @Override
    public GlobalBeginResponseProto convert2Proto(GlobalBeginResponse globalBeginResponse) {
        final short typeCode = globalBeginResponse.getTypeCode();

        final AbstractMessageProto abstractMessage = AbstractMessageProto.newBuilder().setMessageType(
            MessageTypeProto.forNumber(typeCode)).build();

        final String msg = globalBeginResponse.getMsg();
        final AbstractResultMessageProto abstractResultMessageProto = AbstractResultMessageProto.newBuilder().setMsg(
            msg == null ? "" : msg).setResultCode(ResultCodeProto.valueOf(globalBeginResponse.getResultCode().name()))
            .setAbstractMessage(abstractMessage).build();

        final AbstractTransactionResponseProto abstractTransactionRequestProto = AbstractTransactionResponseProto
            .newBuilder().setAbstractResultMessage(abstractResultMessageProto).setTransactionExceptionCode(
                TransactionExceptionCodeProto.valueOf(globalBeginResponse.getTransactionExceptionCode().name()))
            .build();

        final String extraData = globalBeginResponse.getExtraData();
        GlobalBeginResponseProto result = GlobalBeginResponseProto.newBuilder().setAbstractTransactionResponse(
            abstractTransactionRequestProto).setExtraData(extraData == null ? "" : extraData).setXid(
            globalBeginResponse.getXid()).build();
        return result;
    }

    @Override
    public GlobalBeginResponse convert2Model(GlobalBeginResponseProto globalBeginResponseProto) {
        GlobalBeginResponse branchCommitResponse = new GlobalBeginResponse();
        branchCommitResponse.setXid(globalBeginResponseProto.getXid());
        branchCommitResponse.setExtraData(globalBeginResponseProto.getExtraData());
        branchCommitResponse.setMsg(
            globalBeginResponseProto.getAbstractTransactionResponse().getAbstractResultMessage().getMsg());
        branchCommitResponse.setResultCode(ResultCode.valueOf(
            globalBeginResponseProto.getAbstractTransactionResponse().getAbstractResultMessage().getResultCode()
                .name()));

        branchCommitResponse.setTransactionExceptionCode(TransactionExceptionCode
            .valueOf(globalBeginResponseProto.getAbstractTransactionResponse().getTransactionExceptionCode().name()));
        return branchCommitResponse;
    }
}
