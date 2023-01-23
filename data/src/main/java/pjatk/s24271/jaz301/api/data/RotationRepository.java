package pjatk.s24271.jaz301.api.data;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.s24271.jaz301.api.data.objects.ChampionDAO;
import pjatk.s24271.jaz301.api.data.objects.SummonerDAO;

@Repository
@Table(name="rotation")
public interface RotationRepository extends JpaRepository<ChampionDAO, String> {
}