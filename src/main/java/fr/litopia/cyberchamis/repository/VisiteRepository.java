package fr.litopia.cyberchamis.repository;
import fr.litopia.cyberchamis.model.entity.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {
}
