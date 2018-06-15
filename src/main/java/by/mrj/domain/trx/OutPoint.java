package by.mrj.domain.trx;

import by.mrj.message.domain.Hashable;
import lombok.Builder;
import lombok.Getter;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;

@Builder
@Getter
public class OutPoint implements Hashable {
    private String hash;
    private int index;

    @Override
    public String hash() {
        return doubleSha256(hash + index);
    }
}
