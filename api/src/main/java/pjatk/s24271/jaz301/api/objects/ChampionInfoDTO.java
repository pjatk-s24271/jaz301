package pjatk.s24271.jaz301.api.objects;

import java.util.List;

public class ChampionInfoDTO {
    public int maxNewPlayerLevel;
    public List<Integer> freeChampionIdsForNewPlayers;
    public List<Integer> freeChampionKeys;

    public ChampionInfoDTO() {

    }

    public ChampionInfoDTO(
            int maxNewPlayerLevel,
            List<Integer> freeChampionIdsForNewPlayers,
            List<Integer> freeChampionIds
    ) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
        this.freeChampionKeys = freeChampionIds;
    }
}