package pjatk.s24271.jaz301.api.dto;

public class MatchDTO {
    public String puuid;
    public String region;
    public String id;
    public int assists;
    public int deaths;
    public int kills;
    public long startTimestamp;

    public MatchDTO() {

    }

    public MatchDTO(
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
