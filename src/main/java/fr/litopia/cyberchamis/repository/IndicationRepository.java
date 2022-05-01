package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationRepository extends JpaRepository<Indication,Long> {
}
