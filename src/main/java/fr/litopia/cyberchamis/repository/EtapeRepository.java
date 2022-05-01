package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {
}
