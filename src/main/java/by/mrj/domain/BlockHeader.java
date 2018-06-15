package by.mrj.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlockHeader {
    private int version;
    private String previousBlockHash;
    private String merkleRoot;
    private long timestamp; // Unix time. Should be well validated.
    private int assetsCount;
}
