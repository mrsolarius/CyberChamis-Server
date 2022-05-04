package fr.litopia.cyberchamis.repository;
import fr.litopia.cyberchamis.model.entity.Chami;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.StatutVisite;
import fr.litopia.cyberchamis.model.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface ChamiRepository extends JpaRepository<Chami,Long> {
    @Transactional
    @Query("select c from Chami c where c.idGoogle=:idGoogle")
    Chami findByIdGoogle(@Param("idGoogle") String idGoogle);

}

