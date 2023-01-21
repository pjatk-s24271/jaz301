package pjatk.s24271.jaz301.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigInteger;

@Entity
public class SummonerDAO {
    public String accountId;
    public Integer profileIconId;
    public BigInteger revisionDate;
    public String name;
    public String id;
    @Id
    public String puuid;
    public BigInteger summonerLevel;

    public SummonerDAO() {

    }

    public SummonerDAO(
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