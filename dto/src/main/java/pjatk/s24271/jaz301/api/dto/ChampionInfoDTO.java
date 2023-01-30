package pjatk.s24271.jaz301.api.dto;

import java.util.List;

public class ChampionInfoDTO {
    public int maxNewPlayerLevel;
    public List<Integer> freeChampionIdsForNewPlayers;
    public List<Integer> freeChampionIds;

    public ChampionInfoDTO() {

    }

    public ChampionInfoDTO(
            int maxNewPlayerLevel,
            List<Integer> freeChampionIdsForNewPlayers,
            List<Integer> freeChampionIds
    ) {
        this.maxNewPlayerLevel = maxNewPlayerLevel;
        this.freeChampionIdsForNewPlayers = freeChampionIdsForNewPlayers;
        this.freeChampionIds = freeChampionIds;
    }
}