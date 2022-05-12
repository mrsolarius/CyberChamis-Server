package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Utilisateur;
import fr.litopia.cyberchamis.model.entity.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Transactional
    @Query("select u from Utilisateur u join u.vistes v join v.defi d where d.id=:id")
    Optional<Utilisateur[]> getUtilisateurByDefi(@Param("id") String id);


}
