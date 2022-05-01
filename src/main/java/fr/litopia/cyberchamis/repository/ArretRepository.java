package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Arret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArretRepository extends JpaRepository<Arret,String> {
}
