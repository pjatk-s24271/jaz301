package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.Id;

import java.math.BigInteger;

public class MatchDAO {
    @Id
    public BigInteger id;
    public String participantPuuid;
    public String puuid;
    public int assists;
    public int deaths;
    public int kills;
}
