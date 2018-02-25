package by.mrj.service;

import by.mrj.domain.UniqueTypedAsset;
import by.mrj.domain.trx.TrxOut;
import by.mrj.messaging.network.Message;
import by.mrj.messaging.network.MsgService;
import by.mrj.messaging.network.domain.Acknowledge;
import by.mrj.messaging.network.types.Command;
import by.mrj.messaging.network.types.ResponseStatus;

import org.springframework.stereotype.Component;

@Component
public class BasicOperations {

    public Message<?> handshake(Message<?> message) {

        UniqueTypedAsset typedAsset = UniqueTypedAsset.builder().amount(2).build();
        TrxOut trxOut = TrxOut.builder().value(typedAsset).build();
        return MsgService.makeMessageWithPubKey(trxOut, Command.HANDSHAKE);

    }

    public Message<?> acknowledge(Message<?> message) {
        Acknowledge basicAck = Acknowledge.builder()
                .address(message.getAddress())
                .correlationId(message.getChecksum())
                .responseStatus(ResponseStatus.OK)
                .build();

        return MsgService.makeMessageWithSig(basicAck, Command.ACKNOWLEDGE);
    }
}
