package pjatk.s24271.jaz301.api.dto;

public class ChampionDTO {
    public String name;
    public int key;

    public ChampionDTO() {

    }

    public ChampionDTO(
            String name,
            int key
    ) {
        this.name = name;
        this.key = key;
    }
}