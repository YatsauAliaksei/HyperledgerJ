package by.mrj.domain;

//import lombok.AccessLevel;
//import lombok.experimental.FieldDefaults;

//@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlockHeader {
    private int version;
    private String previousBlockHash;
    private String merkleRoot;
    private long timestamp; // Unix time. Should be well validated.
    private int assetsCount;

    @java.beans.ConstructorProperties({"version", "previousBlockHash", "merkleRoot", "timestamp", "assetsCount"})
    public BlockHeader(int version, String previousBlockHash, String merkleRoot, long timestamp, int assetsCount) {
        this.version = version;
        this.previousBlockHash = previousBlockHash;
        this.merkleRoot = merkleRoot;
        this.timestamp = timestamp;
        this.assetsCount = assetsCount;
    }

    public int getVersion() {return this.version;}

    public String getPreviousBlockHash() {return this.previousBlockHash;}

    public String getMerkleRoot() {return this.merkleRoot;}

    public long getTimestamp() {return this.timestamp;}

    public int getAssetsCount() {return this.assetsCount;}

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BlockHeader)) return false;
        final BlockHeader other = (BlockHeader) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getVersion() != other.getVersion()) return false;
        final Object this$previousBlockHash = this.getPreviousBlockHash();
        final Object other$previousBlockHash = other.getPreviousBlockHash();
        if (this$previousBlockHash == null ? other$previousBlockHash != null : !this$previousBlockHash.equals(other$previousBlockHash)) return false;
        final Object this$merkleRoot = this.getMerkleRoot();
        final Object other$merkleRoot = other.getMerkleRoot();
        if (this$merkleRoot == null ? other$merkleRoot != null : !this$merkleRoot.equals(other$merkleRoot)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        if (this.getAssetsCount() != other.getAssetsCount()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getVersion();
        final Object $previousBlockHash = this.getPreviousBlockHash();
        result = result * PRIME + ($previousBlockHash == null ? 43 : $previousBlockHash.hashCode());
        final Object $merkleRoot = this.getMerkleRoot();
        result = result * PRIME + ($merkleRoot == null ? 43 : $merkleRoot.hashCode());
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        result = result * PRIME + this.getAssetsCount();
        return result;
    }

    protected boolean canEqual(Object other) {return other instanceof BlockHeader;}

    public String toString() {return "BlockHeader(version=" + this.getVersion() + ", previousBlockHash=" + this.getPreviousBlockHash() + ", merkleRoot=" + this.getMerkleRoot() + ", timestamp=" + this.getTimestamp() + ", assetsCount=" + this.getAssetsCount() + ")";}
}
