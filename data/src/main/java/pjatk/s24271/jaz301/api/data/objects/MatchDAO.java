package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.Id;

public class MatchDAO {
    public String participantPuuid;
    @Id
    public String puuid;
    public int assists;
    public int deaths;
    public int kills;

    public MatchDAO() {

    }

    public MatchDAO(
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
