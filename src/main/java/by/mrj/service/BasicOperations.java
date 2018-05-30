package by.mrj.service;

import by.mrj.domain.UniqueTypedAsset;
import by.mrj.domain.trx.TrxOut;
import by.mrj.message.domain.Acknowledge;
import by.mrj.message.domain.Message;
import by.mrj.message.types.Command;
import by.mrj.message.types.ResponseStatus;
import by.mrj.message.util.MessageUtils;

import org.springframework.stereotype.Component;

@Component
public class BasicOperations {

    public Message<?> handshake(Message<?> message) {

        UniqueTypedAsset typedAsset = UniqueTypedAsset.builder().amount(2).build();
        TrxOut trxOut = TrxOut.builder().value(typedAsset).build();
        return MessageUtils.makeMessageWithPubKey(trxOut, Command.HANDSHAKE);

    }

    public Message<?> acknowledge(Message<?> message) {
        Acknowledge basicAck = Acknowledge.builder()
                .address(message.getAddress())
                .correlationId(message.getChecksum())
                .responseStatus(ResponseStatus.OK)
                .build();

        return MessageUtils.makeMessageWithSig(basicAck, Command.ACKNOWLEDGE);
    }
}
