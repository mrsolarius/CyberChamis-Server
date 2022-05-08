package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {
}
