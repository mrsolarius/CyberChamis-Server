package fr.litopia.cyberchamis.repository;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.StatutVisite;
import fr.litopia.cyberchamis.model.entity.Utilisateur;
import fr.litopia.cyberchamis.model.entity.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {

    @Transactional
    @Query("select count(v) from Utilisateur u join u.vistes v where v.defi = :defi and v.statut = :statut and u = :utilisateur")
    long countVisitesByStatus(@Param("defi") Defi defi, @Param("statut") StatutVisite statutVisite, @Param("utilisateur") Utilisateur utilisateur);
}
