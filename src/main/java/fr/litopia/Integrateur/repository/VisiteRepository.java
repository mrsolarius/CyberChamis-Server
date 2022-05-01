package fr.litopia.Integrateur.repository;
import fr.litopia.Integrateur.model.entity.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {
}
