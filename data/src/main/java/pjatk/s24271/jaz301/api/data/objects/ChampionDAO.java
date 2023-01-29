package pjatk.s24271.jaz301.api.data.objects;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "rotation")
public class ChampionDAO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int idd;
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