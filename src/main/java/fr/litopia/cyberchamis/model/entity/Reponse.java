package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.ReponseDTO;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @Min(0)
    @Column(name = "numero", nullable = false)
    public Integer numero;

    @Min(-1)
    @Column(name = "score", nullable = false)
    public Integer nbIndicesUtilises;

    @Column(name = "reponseUtilisateur")
    public String reponseUtilisateur;

    public Reponse(){
        this.nbIndicesUtilises = -1;
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
        return Objects.requireNonNullElse(this.reponseUtilisateur, "");
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

    public ReponseDTO toDTO() {
        ReponseDTO reponseDTO = new ReponseDTO();
        reponseDTO.id = this.id;
        reponseDTO.numero = this.numero;
        reponseDTO.nbIndicesUtilises = this.nbIndicesUtilises;
        reponseDTO.reponseUtilisateur = this.reponseUtilisateur;
        return reponseDTO;
    }
}
