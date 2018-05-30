package by.mrj.domain;

import by.mrj.crypto.util.CryptoUtils;
import by.mrj.domain.trx.Transaction;
import by.mrj.message.domain.Hashable;

import java.io.Serializable;
import java.util.Set;

public class Block extends BlockHeader implements Hashable, Serializable {

    private Set<Transaction> transactions;

    private Block(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount, Set<Transaction> transactions) {
        super(version, previousBlockHash, merkleRoot, timestamp, assetsCount);
        this.transactions = transactions;
    }

    public static BlockBuilder builder() {return new BlockBuilder();}

    @Override
    public String hash() {
        return CryptoUtils.doubleSha256(getVersion() + getPreviousBlockHash() + getMerkleRoot() + getTimestamp());
    }

    public Set<Transaction> getTransactions() {return this.transactions;}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Block)) return false;
        final Block other = (Block) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$transactions = this.getTransactions();
        final Object other$transactions = other.getTransactions();
        if (this$transactions == null ? other$transactions != null : !this$transactions.equals(other$transactions)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $transactions = this.getTransactions();
        result = result * PRIME + ($transactions == null ? 43 : $transactions.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof Block;}

    public String toString() {return "Block(super=" + super.toString() + ", transactions=" + this.getTransactions() + ")";}

    public static class BlockBuilder {
        private int version;
        private String previousBlockHash;
        private String merkleRoot;
        private long timestamp;
        private int assetsCount;
        private Set<Transaction> transactions;

        BlockBuilder() {}

        public Block.BlockBuilder version(int version) {
            this.version = version;
            return this;
        }

        public Block.BlockBuilder previousBlockHash(String previousBlockHash) {
            this.previousBlockHash = previousBlockHash;
            return this;
        }

        public Block.BlockBuilder merkleRoot(String merkleRoot) {
            this.merkleRoot = merkleRoot;
            return this;
        }

        public Block.BlockBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Block.BlockBuilder assetsCount(int assetsCount) {
            this.assetsCount = assetsCount;
            return this;
        }

        public Block.BlockBuilder transactions(Set<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public Block build() {
            return new Block(version, previousBlockHash, merkleRoot, timestamp, assetsCount, transactions);
        }

        public String toString() {return "Block.BlockBuilder(version=" + this.version + ", previousBlockHash=" + this.previousBlockHash + ", merkleRoot=" + this.merkleRoot + ", timestamp=" + this.timestamp + ", assetsCount=" + this.assetsCount + ", transactions=" + this.transactions + ")";}
    }
}
