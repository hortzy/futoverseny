package gll1zu.futoverseny;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersenyRepository extends JpaRepository<VersenyEntity, Long> {
}
