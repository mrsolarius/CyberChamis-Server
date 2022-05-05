package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Commentaire;
import fr.litopia.cyberchamis.model.entity.Defi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface DefiRepository extends JpaRepository<Defi,String> {

    @Transactional
    @Query("select c from Defi d join d.commentaires c where c.id=:idCom")
    Optional<Commentaire> findComByDefi(@Param("idCom") Long idCom);

}
