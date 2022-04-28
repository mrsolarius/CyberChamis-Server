package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Getter
@Setter
@Entity
public class Note {
    @Id
    @Column(name = "idNote", nullable = false)
    public Long id;

    @Column(name = "valeur", nullable = false)
    public Integer valeur;

    @OneToOne
    Chami chami;
}
