package by.mrj.domain.trx;

import by.mrj.message.domain.Hashable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


@Builder
@Getter
public class TrxIn implements Hashable, Serializable {
    private OutPoint outPoint;
    private int scriptLength;
    private String unlockingScript; // excluded from hash because in most of cases it will contain sign

    @Override
    public String hash() {
        return doubleSha256(outPoint.hash() + scriptLength);
    }
}
