package pjatk.s24271.jaz301.api.data;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pjatk.s24271.jaz301.api.data.objects.MatchDAO;

import java.util.List;

@Repository
@Table(name = "match")
public interface MatchRepository extends JpaRepository<MatchDAO, String> {
    @Query(value = "SELECT * FROM 'match' WHERE region = ':region' AND participantPuuid = ':puuid' LIMIT :count ORDER BY startTimestamp DESC", nativeQuery = true)
    List<MatchDAO> getLast(@Param("region") String region, @Param("puuid") String puuid, @Param("count") int count);
}