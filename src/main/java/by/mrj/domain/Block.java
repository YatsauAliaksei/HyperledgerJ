package by.mrj.domain;

import by.mrj.crypto.util.CryptoUtils;
import by.mrj.domain.trx.Transaction;
import by.mrj.message.domain.Hashable;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Getter
public class Block extends BlockHeader implements Hashable, Serializable {

    private Set<Transaction> transactions;

    @Builder(builderMethodName = "creator")
    public Block(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount, Set<Transaction> transactions) {
        super(version, previousBlockHash, merkleRoot, timestamp, assetsCount);
        this.transactions = transactions;
    }

    @Override
    public String hash() {
        return CryptoUtils.doubleSha256(getVersion() + getPreviousBlockHash() + getMerkleRoot() + getTimestamp());
    }
}
