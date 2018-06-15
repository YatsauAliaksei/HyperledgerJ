package by.mrj.domain;


import by.mrj.crypto.util.CryptoUtils;
import by.mrj.message.domain.Hashable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public abstract class Asset implements Hashable, Serializable {

    private final float amount; // precision should be checked and restricted.

    @Override
    public String hash() {
        return CryptoUtils.doubleSha256("" + amount);
    }
}
