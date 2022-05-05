package fr.litopia.cyberchamis.controller;


import fr.litopia.cyberchamis.model.dto.CommentaireDTO;
import fr.litopia.cyberchamis.model.dto.NoteDTO;
import fr.litopia.cyberchamis.model.entity.Commentaire;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Note;
import fr.litopia.cyberchamis.repository.ChamiRepository;
import fr.litopia.cyberchamis.repository.CommentaireRepository;
import fr.litopia.cyberchamis.repository.DefiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/commentaires", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentaireRestController {

    @Autowired
    private CommentaireRepository commentaireRepository;

    @Autowired
    private DefiRepository defiRepository;

    @Autowired
    private ChamiRepository chamiRepository;

    @GetMapping("/{defiId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<CommentaireDTO> getCommentaires(@PathVariable("defiId") String idDefi) {
        var commentaires = commentaireRepository.findByDefi(idDefi);
        if (commentaires.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        Set<CommentaireDTO> commentairesDto = new HashSet<>();
        for(Commentaire com : commentaires.get()){
            commentairesDto.add(com.toDTO());
        }
        return commentairesDto;
    }

    @GetMapping("/chami/{idChami}")
    @ResponseStatus(HttpStatus.OK)
    public Set<CommentaireDTO> getCommentairesByChami(@PathVariable("idChami") Long idChami) {
        var commentaires = commentaireRepository.findByChami(idChami);
        if (commentaires.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        Set<CommentaireDTO> commentairesDto = new HashSet<>();
        for(Commentaire com : commentaires.get()){
            commentairesDto.add(com.toDTO());
        }
        return commentairesDto;
    }

    @GetMapping("/commentaire/{idCom}")
    @ResponseStatus(HttpStatus.OK)
    public CommentaireDTO getCommentaireById(@PathVariable("idCom") Long idCom) {
        var commentaire = commentaireRepository.findById(idCom);
        if (commentaire.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return commentaire.get().toDTO();
    }


    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public CommentaireDTO createOrUpdateCom(@RequestBody CommentaireDTO commentaire) {
        try {
            this.toSaveEntity(commentaire);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return commentaire;
    }

   /* @DeleteMapping("/{idCom}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deleteCom(@PathVariable("idCom") Long idCom){
        var commentaireToDelete = commentaireRepository.findById(idCom);
        if(commentaireToDelete.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        commentaireRepository.delete(commentaireToDelete.get());
    }*/


    private void toSaveEntity(CommentaireDTO comm) {
        var d = this.defiRepository.findById(comm.idDefi);
        if (d.isEmpty()){
            throw new NotFoundException("Defi not found");
        }
        var chami = this.chamiRepository.findById(comm.idUtilisateur);
        if(chami.isEmpty()){
            throw new NotFoundException("Chami not found");
        }
        if(comm.idCommentaire!=null){
            var c = this.commentaireRepository.findById(comm.idCommentaire);
            if(c.isPresent()){
                c.get().setText(comm.text);
                this.saveCommentaire(c.get(), d.get());
                return;
            }
        }
        Commentaire nc = new Commentaire(comm.text,chami.get());
        this.saveCommentaire(nc,d.get());
    }

    private void saveCommentaire(Commentaire c, Defi d){
        this.commentaireRepository.save(c);
        d.ajouterOuModifierCommentaire(c);
        this.defiRepository.save(d);
    }

}
