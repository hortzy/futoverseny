package gll1zu.futoverseny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EredmenyRepository extends JpaRepository<EredmenyEntity, Long> {
    List<EredmenyEntity> findByVersenyId(Long versenyId);
}
