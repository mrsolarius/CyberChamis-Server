package fr.litopia.Integrateur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Commentaire {

    @Id
    @Column(name = "idCom", nullable = false)
    Integer id;

    @Column(name = "text", nullable = false)
    String text;

    @ManyToOne
    @Column(name = "Auteur-du-commentaire ", nullable = false)
    Chami auteur;


}
