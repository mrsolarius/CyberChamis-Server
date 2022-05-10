package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Commentaire;
import fr.litopia.cyberchamis.model.entity.Defi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DefiRepository extends JpaRepository<Defi,String> {

    @Transactional
    @Query("select d from Defi d join d.commentaires c where c.id=:idCom")
    Optional<Defi> findDefiByCom(@Param("idCom") Long idCom);

    @Transactional
    @Query("select distinct d from Defi d join d.commentaires c join c.auteur a where a.id=:idChami")
    Optional<Defi[]> findDefisByChami(@Param("idChami") Long idChami);

    @Transactional
    @Query("select d from Defi d join d.tags t where t.tag=:tag")
    Collection<Defi> getDefisByTag(@Param("tag")String tag);




}
