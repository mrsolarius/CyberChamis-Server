package fr.litopia.Integrateur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Indice {
    @Id
    @Column(name = "idIndice", nullable = false)
    public long id;
    @Column(name = "text", nullable = false)
    public String text;

    @Column(name = "pointsPerdus", nullable = false)
    public Integer pointsPerdus ;

    @Column(name = "statut", nullable = false)
    public StatutIndice statut;
}
