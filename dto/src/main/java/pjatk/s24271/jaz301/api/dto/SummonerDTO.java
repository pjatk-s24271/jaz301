package pjatk.s24271.jaz301.api.dto;

import java.math.BigInteger;

public class SummonerDTO {
    public String accountId;
    public Integer profileIconId;
    public BigInteger revisionDate;
    public String name;
    public String id;
    public String puuid;
    public BigInteger summonerLevel;

    public SummonerDTO() {

    }

    public SummonerDTO(
            String accountId,
            Integer profileIconId,
            BigInteger revisionDate,
            String name,
            String id,
            String puuid,
            BigInteger summonerLevel
    ) {
        this.accountId = accountId;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.name = name;
        this.id = id;
        this.puuid = puuid;
        this.summonerLevel = summonerLevel;
    }
}