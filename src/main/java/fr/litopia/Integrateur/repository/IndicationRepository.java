package fr.litopia.Integrateur.repository;

import fr.litopia.Integrateur.model.entity.Arret;
import fr.litopia.Integrateur.model.entity.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationRepository extends JpaRepository<Indication,Long> {
}
