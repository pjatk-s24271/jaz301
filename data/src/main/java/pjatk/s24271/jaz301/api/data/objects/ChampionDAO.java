package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ChampionDAO {
    public String name;
    @Id
    public int key;

    public ChampionDAO() {

    }

    public ChampionDAO(
            String name,
            int key
    ) {
        this.name = name;
        this.key = key;
    }
}