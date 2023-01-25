package pjatk.s24271.jaz301.api.objects;


import java.util.List;

public class MatchRiotDTO {

    public MetadataDto metadata;
    public InfoDto info;

    public static class MetadataDto {
        public String matchId;

        public MetadataDto() {

        }
    }

    public static class InfoDto {
        public long gameStartTimestamp;
        public List<ParticipantDto> participants;

        public static class ParticipantDto {
            public int kills;
            public int assists;
            public int deaths;
            public String puuid;

            public ParticipantDto() {

            }
        }

        public InfoDto() {

        }
    }

    public MatchRiotDTO() {

    }
}
