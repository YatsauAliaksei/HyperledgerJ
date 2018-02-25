package by.mrj.domain.trx;

import by.mrj.crypto.util.CryptoUtils;
import by.mrj.messaging.network.domain.Hashable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@Builder
@ToString
public class Transaction implements Hashable {
    private int version;
    private int inCount;
    private Set<TrxIn> trxInputs;
    private int outCount;
    private Set<TrxOut> trxOutputs;

    @Override
    public String hash() {
        String txInsHash = trxInputs.stream()
                .map(TrxIn::hash)
                .reduce(String::concat)
                .orElseThrow(IllegalStateException::new);

        String txOutsHash = trxOutputs.stream()
                .map(TrxOut::hash)
                .reduce(String::concat)
                .orElseThrow(IllegalStateException::new);

        return CryptoUtils.doubleSha256(version + inCount + txInsHash + outCount + txOutsHash);
    }
}
