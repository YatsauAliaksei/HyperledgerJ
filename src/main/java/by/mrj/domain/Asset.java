package by.mrj.domain;


import by.mrj.crypto.util.CryptoUtils;
import by.mrj.message.domain.Hashable;

import java.io.Serializable;

public abstract class Asset implements Hashable, Serializable {

    private float amount; // precision should be checked and restricted.

    @java.beans.ConstructorProperties({"amount"})
    protected Asset(float amount) {
        this.amount = amount;
    }

    @Override
    public String hash() {
        return CryptoUtils.doubleSha256("" + amount);
    }

    public float getAmount() {return this.amount;}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Asset)) return false;
        final Asset other = (Asset) o;
        if (!other.canEqual((Object) this)) return false;
        if (Float.compare(this.getAmount(), other.getAmount()) != 0) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.getAmount());
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof Asset;}

    public String toString() {return "Asset(amount=" + this.getAmount() + ")";}
}
