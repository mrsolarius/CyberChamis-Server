package fr.litopia.Integrateur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@Entity
public class Note {
    @Id
    @Column(name = "idNote", nullable = false)
    public String id;

    @Column(name = "note", nullable = false)
    @Min(value = 0)
    @Max(value = 5)
    public Integer note;

    @OneToOne
    Chami chami;

    public Note() {
        this.id = UUID.randomUUID().toString();
    }

    public Note(Integer valeur, Chami chami) {
        this();
        this.note = valeur;
        this.chami = chami;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getNote() {
        return this.note;
    }

    public String getId() {
        return this.id;
    }


}
