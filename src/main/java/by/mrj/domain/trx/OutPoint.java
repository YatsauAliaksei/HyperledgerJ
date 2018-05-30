package by.mrj.domain.trx;

import by.mrj.message.domain.Hashable;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OutPoint implements Hashable {
    private String hash;
    private int index;

    @java.beans.ConstructorProperties({"hash", "index"})
    OutPoint(String hash, int index) {
        this.hash = hash;
        this.index = index;
    }

    public static OutPointBuilder builder() {return new OutPointBuilder();}

    @Override
    public String hash() {
        return doubleSha256(hash + index);
    }

    public String getHash() {return this.hash;}

    public int getIndex() {return this.index;}

    public static class OutPointBuilder {
        private String hash;
        private int index;

        OutPointBuilder() {}

        public OutPoint.OutPointBuilder hash(String hash) {
            this.hash = hash;
            return this;
        }

        public OutPoint.OutPointBuilder index(int index) {
            this.index = index;
            return this;
        }

        public OutPoint build() {
            return new OutPoint(hash, index);
        }

        public String toString() {return "OutPoint.OutPointBuilder(hash=" + this.hash + ", index=" + this.index + ")";}
    }
}
