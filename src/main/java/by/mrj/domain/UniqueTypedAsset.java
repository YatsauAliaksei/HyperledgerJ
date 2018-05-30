package by.mrj.domain;

import static by.mrj.crypto.util.CryptoUtils.doubleSha256;


public class UniqueTypedAsset extends Asset {
    private String type;
    private String uuid;

    private UniqueTypedAsset(float amount, String type, String uuid) {
        super(amount);
        this.type = type;
        this.uuid = uuid;
    }

    public static UniqueTypedAssetBuilder builder() {return new UniqueTypedAssetBuilder();}

    @Override
    public String hash() {
        return doubleSha256(super.hash() + type + uuid);
    }

    public String getType() {return this.type;}

    public String getUuid() {return this.uuid;}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof UniqueTypedAsset)) return false;
        final UniqueTypedAsset other = (UniqueTypedAsset) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$uuid = this.getUuid();
        final Object other$uuid = other.getUuid();
        if (this$uuid == null ? other$uuid != null : !this$uuid.equals(other$uuid)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $uuid = this.getUuid();
        result = result * PRIME + ($uuid == null ? 43 : $uuid.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof UniqueTypedAsset;}

    public String toString() {return "UniqueTypedAsset(type=" + this.getType() + ", uuid=" + this.getUuid() + ")";}

    public static class UniqueTypedAssetBuilder {
        private float amount;
        private String type;
        private String uuid;

        UniqueTypedAssetBuilder() {}

        public UniqueTypedAsset.UniqueTypedAssetBuilder amount(float amount) {
            this.amount = amount;
            return this;
        }

        public UniqueTypedAsset.UniqueTypedAssetBuilder type(String type) {
            this.type = type;
            return this;
        }

        public UniqueTypedAsset.UniqueTypedAssetBuilder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public UniqueTypedAsset build() {
            return new UniqueTypedAsset(amount, type, uuid);
        }

        public String toString() {return "UniqueTypedAsset.UniqueTypedAssetBuilder(amount=" + this.amount + ", type=" + this.type + ", uuid=" + this.uuid + ")";}
    }
}
