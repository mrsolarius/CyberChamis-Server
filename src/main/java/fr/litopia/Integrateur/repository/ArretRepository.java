package fr.litopia.Integrateur.repository;

import fr.litopia.Integrateur.model.entity.Arret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArretRepository extends JpaRepository<Arret,String> {
}
