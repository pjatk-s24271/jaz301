package pjatk.s24271.jaz301.api.objects;

public class MatchDTO {
    public String participantPuuid;
    public String puuid;
    public int assists;
    public int deaths;
    public int kills;

    public MatchDTO() {

    }

    public MatchDTO(
            String participantPuuid,
            String puuid,
            int assists,
            int deaths,
            int kills
    ) {
        this.participantPuuid = participantPuuid;
        this.puuid = puuid;
        this.assists = assists;
        this.deaths = deaths;
        this.kills = kills;
    }
}
