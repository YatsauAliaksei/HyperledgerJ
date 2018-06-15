package by.mrj.domain.trx;

import by.mrj.domain.Asset;
import by.mrj.message.domain.Hashable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


@Builder
@Getter
public class TrxOut implements Hashable, Serializable {
    private Asset value;
    private int scriptLength;
    private String lockingScript;

    @Override
    public String hash() {
        return doubleSha256(value.hash() + scriptLength + lockingScript);
    }
}
