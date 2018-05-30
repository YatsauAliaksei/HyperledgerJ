package by.mrj.domain.trx;

import by.mrj.crypto.util.CryptoUtils;
import by.mrj.message.domain.Hashable;

import java.util.Set;

public class Transaction implements Hashable {
    private int version;
    private int inCount;
    private Set<TrxIn> trxInputs;
    private int outCount;
    private Set<TrxOut> trxOutputs;

    @java.beans.ConstructorProperties({"version", "inCount", "trxInputs", "outCount", "trxOutputs"})
    Transaction(int version, int inCount, Set<TrxIn> trxInputs, int outCount, Set<TrxOut> trxOutputs) {
        this.version = version;
        this.inCount = inCount;
        this.trxInputs = trxInputs;
        this.outCount = outCount;
        this.trxOutputs = trxOutputs;
    }

    public static TransactionBuilder builder() {return new TransactionBuilder();}

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

    public int getVersion() {return this.version;}

    public int getInCount() {return this.inCount;}

    public Set<TrxIn> getTrxInputs() {return this.trxInputs;}

    public int getOutCount() {return this.outCount;}

    public Set<TrxOut> getTrxOutputs() {return this.trxOutputs;}

    public String toString() {return "Transaction(version=" + this.getVersion() + ", inCount=" + this.getInCount() + ", trxInputs=" + this.getTrxInputs() + ", outCount=" + this.getOutCount() + ", trxOutputs=" + this.getTrxOutputs() + ")";}

    public static class TransactionBuilder {
        private int version;
        private int inCount;
        private Set<TrxIn> trxInputs;
        private int outCount;
        private Set<TrxOut> trxOutputs;

        TransactionBuilder() {}

        public Transaction.TransactionBuilder version(int version) {
            this.version = version;
            return this;
        }

        public Transaction.TransactionBuilder inCount(int inCount) {
            this.inCount = inCount;
            return this;
        }

        public Transaction.TransactionBuilder trxInputs(Set<TrxIn> trxInputs) {
            this.trxInputs = trxInputs;
            return this;
        }

        public Transaction.TransactionBuilder outCount(int outCount) {
            this.outCount = outCount;
            return this;
        }

        public Transaction.TransactionBuilder trxOutputs(Set<TrxOut> trxOutputs) {
            this.trxOutputs = trxOutputs;
            return this;
        }

        public Transaction build() {
            return new Transaction(version, inCount, trxInputs, outCount, trxOutputs);
        }

        public String toString() {return "Transaction.TransactionBuilder(version=" + this.version + ", inCount=" + this.inCount + ", trxInputs=" + this.trxInputs + ", outCount=" + this.outCount + ", trxOutputs=" + this.trxOutputs + ")";}
    }
}
