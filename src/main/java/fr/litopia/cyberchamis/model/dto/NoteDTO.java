package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Note;

public class NoteDTO {
    public String idDefi;
    public Long idUtilisateur;
    public Integer valeur;
//@todo faire le to entity
/*
    public Note toEntity(){
        Note noteE = new Note();
        noteE.note=this.valeur;
        return noteE;
    }*/
}
