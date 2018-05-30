package by.mrj.domain.trx;

import by.mrj.message.domain.Hashable;

import java.io.Serializable;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


public class TrxIn implements Hashable, Serializable {
    private OutPoint outPoint;
    private int scriptLength;
    private String unlockingScript; // excluded from hash because in most of cases it will contain sign

    @java.beans.ConstructorProperties({"outPoint", "scriptLength", "unlockingScript"})
    TrxIn(OutPoint outPoint, int scriptLength, String unlockingScript) {
        this.outPoint = outPoint;
        this.scriptLength = scriptLength;
        this.unlockingScript = unlockingScript;
    }

    public static TrxInBuilder builder() {return new TrxInBuilder();}

    @Override
    public String hash() {
        return doubleSha256(outPoint.hash() + scriptLength);
    }

    public OutPoint getOutPoint() {return this.outPoint;}

    public int getScriptLength() {return this.scriptLength;}

    public String getUnlockingScript() {return this.unlockingScript;}

    public static class TrxInBuilder {
        private OutPoint outPoint;
        private int scriptLength;
        private String unlockingScript;

        TrxInBuilder() {}

        public TrxIn.TrxInBuilder outPoint(OutPoint outPoint) {
            this.outPoint = outPoint;
            return this;
        }

        public TrxIn.TrxInBuilder scriptLength(int scriptLength) {
            this.scriptLength = scriptLength;
            return this;
        }

        public TrxIn.TrxInBuilder unlockingScript(String unlockingScript) {
            this.unlockingScript = unlockingScript;
            return this;
        }

        public TrxIn build() {
            return new TrxIn(outPoint, scriptLength, unlockingScript);
        }

        public String toString() {return "TrxIn.TrxInBuilder(outPoint=" + this.outPoint + ", scriptLength=" + this.scriptLength + ", unlockingScript=" + this.unlockingScript + ")";}
    }
}
