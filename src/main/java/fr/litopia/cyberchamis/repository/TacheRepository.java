package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache,Long> {
}
