package pjatk.s24271.jaz301.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummonerRepository extends JpaRepository<SummonerDAO, String> {
}