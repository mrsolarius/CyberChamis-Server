package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.NoteDTO;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
public class Note {
    @Id
    @GeneratedValue
    @Column(name = "idNote", nullable = false)
    private Long id;

    @Column(name = "note", nullable = false)
    @Min(value = 1)
    @Max(value = 5)
    private Integer note;

    @OneToOne
    private Chami chami;

    public Note() {}

    public Note(Integer valeur, Chami chami) {
        this.setNote(valeur);
        this.chami = chami;
    }

    public void setNote(Integer note) {
        if (note < 1 || note > 5) {
            throw new IllegalArgumentException("Note must be between 1 and 5");
        }
        this.note = note;
    }

    public Integer getNote() {
        return this.note;
    }

    public Boolean isEqualTo(Integer note){
        return this.note==note;
    }

    public Long getId() {
        return this.id;
    }

    public NoteDTO toDTO(){
        NoteDTO dto = new NoteDTO();
        dto.idUtilisateur = chami.getId();
        dto.valeur = note;
        return dto;
    }
}
