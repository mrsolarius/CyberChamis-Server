package fr.litopia.Integrateur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Date;


@EnableJpaRepositories("fr.litopia.Integrateur.repository")
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class Defi {
    @Id
    @Column(name = "id", nullable = false)
    public String id;

    @Column(name = "name")
    public String titre;

    @Column(name = "dateDeCreation")
    public Date dateDeCreation;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "auteur_login")
    public Chami auteur;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuteur(Chami auteur) {
        this.auteur = auteur;
    }

    public Chami getAuteur() {
        return auteur;
    }


}
