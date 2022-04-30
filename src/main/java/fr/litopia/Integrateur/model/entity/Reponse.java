package fr.litopia.Integrateur.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @Min(0)
    @Column(name = "numero", nullable = false)
    public Integer numero;

    @Min(0)
    @Column(name = "score", nullable = false)
    public Integer nbIndicesUtilises;

    @Column(name = "texte", nullable = false)
    public String reponseUtilisateur;

    public Reponse(){
        this.nbIndicesUtilises = 0;
    }

    public Reponse(Integer numero){
        this();
        if (numero < 0) {
            throw new IllegalArgumentException("Numero must be positive");
        }
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public Integer getNbIndicesUtilises() {
        return nbIndicesUtilises;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getReponseUtilisateur() {
        return reponseUtilisateur;
    }

    public void addIndiceUtilise() {
        nbIndicesUtilises++;
    }

    public void setReponseUtilisateur(String reponseUtilisateur) {
        if(this.reponseUtilisateur != null) {
            throw new IllegalArgumentException("Reponse could not be changed");
        }
        this.reponseUtilisateur = reponseUtilisateur;
    }
}
