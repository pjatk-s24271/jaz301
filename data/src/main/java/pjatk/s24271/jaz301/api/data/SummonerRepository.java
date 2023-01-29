package pjatk.s24271.jaz301.api.data;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.s24271.jaz301.api.data.objects.SummonerDAO;

import java.util.List;

@Repository
@Table(name = "summoner")
public interface SummonerRepository extends JpaRepository<SummonerDAO, Long> {
    List<SummonerDAO> findByPuuid(String puuid);
}