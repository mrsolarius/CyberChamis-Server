package fr.litopia.Integrateur.repository;
import fr.litopia.Integrateur.model.Chami;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamiRepository extends JpaRepository<Chami,String> {
}

