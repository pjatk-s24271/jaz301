package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChampionDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long idd;
    public String name;
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