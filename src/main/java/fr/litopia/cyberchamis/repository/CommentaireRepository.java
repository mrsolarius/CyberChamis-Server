package fr.litopia.cyberchamis.repository;


import fr.litopia.cyberchamis.model.entity.Chami;
import fr.litopia.cyberchamis.model.entity.Commentaire;
import fr.litopia.cyberchamis.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    @Transactional
    @Query("select c from Defi d join d.commentaires c where d.id=:idDefi")
    Optional<Commentaire[]> findByDefi(@Param("idDefi") String idDefi);

    @Transactional
    @Query("select c from Commentaire c join c.auteur a where a.id=:idChami")
    Optional<Commentaire[]> findByChami(@Param("idChami") Long idChami);


}
