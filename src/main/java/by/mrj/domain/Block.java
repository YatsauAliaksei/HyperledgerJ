package by.mrj.domain;

import by.mrj.crypto.util.CryptoUtils;
import by.mrj.domain.trx.Transaction;
import by.mrj.messaging.network.domain.Hashable;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

@Value
@ToString(callSuper = true)
public class Block extends BlockHeader implements Hashable, Serializable {

    private Set<Transaction> transactions;

    @Builder
    private Block(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount, Set<Transaction> transactions) {
        super(version, previousBlockHash, merkleRoot, timestamp, assetsCount);
        this.transactions = transactions;
    }

    @Override
    public String hash() {
        return CryptoUtils.doubleSha256(getVersion() + getPreviousBlockHash() + getMerkleRoot() + getTimestamp());
    }
}
