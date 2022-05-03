package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Note;

public class NoteDTO {
    Integer valeur;

    public Note toEntity(){
        Note noteE = new Note();
        noteE.note=this.valeur;
        return noteE;
    }
}
