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

import java.util.Collection;
import java.util.Optional;

@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {

    @Transactional
    @Query("select count(v) from Utilisateur u join u.vistes v where v.defi = :defi and v.statut in :statut and u = :utilisateur")
    long countVisitesByStatus(@Param("defi") Defi defi, @Param("statut") Collection<StatutVisite> statutVisite, @Param("utilisateur") Utilisateur utilisateur);

    @Transactional
    @Query("select v from Utilisateur u join u.vistes v where v.statut in :status and u = :utilisateur and v.defi = :defi")
    Visite findUserVisiteThatHaveStatus(@Param("defi") Defi defi, @Param("status") Collection<StatutVisite> status, @Param("utilisateur") Utilisateur utilisateur);

    @Transactional
    @Query("select v from Utilisateur u join u.vistes v where v.statut = :statu and u.id=:id")
    Collection<Visite> findVisiteByUserAndStatus(@Param("statu") StatutVisite status, @Param("id") Long id);

    @Transactional
    @Query("select v from Chami c join c.vistes v where c.idGoogle=:idGoogle and v.statut=2 order by v.dateDeModification DESC")
    Optional<Visite[]> getVisitesFinishedByDefiAndChami(@Param("idGoogle") String idGoogle);

    @Transactional
    @Query("select v from Chami c join c.vistes v where c.id=:id and v.statut=2 order by v.dateDeModification DESC")
    Optional<Visite[]> getVisitesFinishedByDefiAndChamiById(@Param("id") long idGoogle);
}
