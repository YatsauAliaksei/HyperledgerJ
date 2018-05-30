package by.mrj.domain.trx;

import by.mrj.domain.Asset;
import by.mrj.message.domain.Hashable;
//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrxOut implements Hashable, Serializable {
    private Asset value;
    private int scriptLength;
    private String lockingScript;

    @java.beans.ConstructorProperties({"value", "scriptLength", "lockingScript"})
    TrxOut(Asset value, int scriptLength, String lockingScript) {
        this.value = value;
        this.scriptLength = scriptLength;
        this.lockingScript = lockingScript;
    }

    public static TrxOutBuilder builder() {return new TrxOutBuilder();}

    @Override
    public String hash() {
        return doubleSha256(value.hash() + scriptLength + lockingScript);
    }

    public Asset getValue() {return this.value;}

    public int getScriptLength() {return this.scriptLength;}

    public String getLockingScript() {return this.lockingScript;}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TrxOut)) return false;
        final TrxOut other = (TrxOut) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        if (this.getScriptLength() != other.getScriptLength()) return false;
        final Object this$lockingScript = this.getLockingScript();
        final Object other$lockingScript = other.getLockingScript();
        if (this$lockingScript == null ? other$lockingScript != null : !this$lockingScript.equals(other$lockingScript)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        result = result * PRIME + this.getScriptLength();
        final Object $lockingScript = this.getLockingScript();
        result = result * PRIME + ($lockingScript == null ? 43 : $lockingScript.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof TrxOut;}

    public String toString() {return "TrxOut(value=" + this.getValue() + ", scriptLength=" + this.getScriptLength() + ", lockingScript=" + this.getLockingScript() + ")";}

    public static class TrxOutBuilder {
        private Asset value;
        private int scriptLength;
        private String lockingScript;

        TrxOutBuilder() {}

        public TrxOut.TrxOutBuilder value(Asset value) {
            this.value = value;
            return this;
        }

        public TrxOut.TrxOutBuilder scriptLength(int scriptLength) {
            this.scriptLength = scriptLength;
            return this;
        }

        public TrxOut.TrxOutBuilder lockingScript(String lockingScript) {
            this.lockingScript = lockingScript;
            return this;
        }

        public TrxOut build() {
            return new TrxOut(value, scriptLength, lockingScript);
        }

        public String toString() {return "TrxOut.TrxOutBuilder(value=" + this.value + ", scriptLength=" + this.scriptLength + ", lockingScript=" + this.lockingScript + ")";}
    }
}
