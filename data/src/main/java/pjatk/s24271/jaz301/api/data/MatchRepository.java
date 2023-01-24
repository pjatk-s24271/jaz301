package pjatk.s24271.jaz301.api.data;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.s24271.jaz301.api.data.objects.MatchDAO;

@Repository
@Table(name="match")
public interface MatchRepository extends JpaRepository<MatchDAO, String> {
}