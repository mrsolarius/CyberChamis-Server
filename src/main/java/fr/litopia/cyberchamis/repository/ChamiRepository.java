package fr.litopia.cyberchamis.repository;
import fr.litopia.cyberchamis.model.entity.Chami;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamiRepository extends JpaRepository<Chami,String> {
}

