package fr.litopia.Integrateur.repository;

import fr.litopia.Integrateur.model.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepository extends JpaRepository<Tache,Long> {
}
