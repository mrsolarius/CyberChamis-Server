package fr.litopia.cyberchamis.controller;

import fr.litopia.cyberchamis.model.dto.DefiDTO;
import fr.litopia.cyberchamis.model.dto.NoteDTO;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Note;
import fr.litopia.cyberchamis.repository.ChamiRepository;
import fr.litopia.cyberchamis.repository.DefiRepository;
import fr.litopia.cyberchamis.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;

@RestController
@RequestMapping(value = "/api/note", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoteRestController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private DefiRepository defiRepository;

    @Autowired
    private ChamiRepository chamiRepository;

    @GetMapping("/{defiId}/{utilistateurId}")
    @ResponseStatus(HttpStatus.OK)
    public NoteDTO getNote(@PathVariable("defiId") String idDefi, @PathVariable("utilistateurId") Long idUser){
        var note = noteRepository.findByUserAndDefis(idDefi,idUser);
        if(note==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        return note.toDTO();
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public NoteDTO createNote(@RequestBody NoteDTO note){
        Note checkEmpty = noteRepository.findByUserAndDefis(note.idDefi,note.idUtilisateur);
        if(checkEmpty==null) {
            try {
                this.toSaveEntity(note);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity already exist");
        }
        return note;
    }

    @PutMapping("/{defiId}/{utilistateurId}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public NoteDTO updateNote(@PathVariable("defiId") String idDefi, @PathVariable("utilistateurId") Long idUser, @RequestBody final Integer note){
        Note noteToUpdate = noteRepository.findByUserAndDefis(idDefi,idUser);
        if(noteToUpdate == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
        noteToUpdate.setNote(note);
        this.noteRepository.save(noteToUpdate);
        return noteToUpdate.toDTO();
    }

    @Transactional
    public Note toSaveEntity(NoteDTO note){
        var chami = chamiRepository.findById(note.idUtilisateur);
        if(chami.isEmpty()){
            throw new NotFoundException("chami not found");
        }
        var defi = defiRepository.findById(note.idDefi);
        if(defi.isEmpty()){
            throw new NotFoundException("defi not found");
        }
        Note n = new Note(note.valeur,chami.get());
        defi.get().ajouterOuModifierNote(n);
        this.noteRepository.save(n);
        this.defiRepository.save(defi.get());
        return n;
    }
}
