package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "match")
public class MatchDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long idd;
    public String puuid;
    public String region;
    public String id;
    public int assists;
    public int deaths;
    public int kills;
    public long startTimestamp;

    public MatchDAO() {

    }

    public MatchDAO(
            String puuid,
            String region,
            String id,
            int assists,
            int deaths,
            int kills,
            long startTimestamp
    ) {
        this.puuid = puuid;
        this.region = region;
        this.id = id;
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
        this.startTimestamp = startTimestamp;
    }
}
