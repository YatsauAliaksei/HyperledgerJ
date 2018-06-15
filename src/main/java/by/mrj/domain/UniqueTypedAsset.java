package by.mrj.domain;

import lombok.Builder;
import lombok.Getter;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


@Getter
public class UniqueTypedAsset extends Asset {
    private String type;
    private String uuid;

    @Builder
    private UniqueTypedAsset(float amount, String type, String uuid) {
        super(amount);
        this.type = type;
        this.uuid = uuid;
    }

    @Override
    public String hash() {
        return doubleSha256(super.hash() + type + uuid);
    }
}
