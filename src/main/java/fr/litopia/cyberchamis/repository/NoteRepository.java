package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    @Transactional
    @Query("select n from Defi d join d.notes n join n.chami c where c.id=:idChami and d.id=:idDefi")
    Note findByUserAndDefis(@Param("idDefi") String idDefi,@Param("idChami")Long idChamis);
}
