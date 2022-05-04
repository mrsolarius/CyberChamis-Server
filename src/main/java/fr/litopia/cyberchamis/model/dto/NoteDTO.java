package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.controller.DefiRestController;
import fr.litopia.cyberchamis.model.entity.Note;
import fr.litopia.cyberchamis.repository.ChamiRepository;
import fr.litopia.cyberchamis.repository.DefiRepository;
import fr.litopia.cyberchamis.repository.NoteRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

public class NoteDTO {
    public String idDefi;
    public Long idUtilisateur;
    public Integer valeur;
}
