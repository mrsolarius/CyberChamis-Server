package fr.litopia.Integrateur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EnableJpaRepositories("fr.litopia.Integrateur.repository")
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class Defi {
    @Id
    @Column(name = "idDefi", nullable = false)
    public String id;

    @Column(name = "titre")
    public String titre;

    @Column(name = "typeDefi")
    public String type ;

    @ManyToOne
    @JoinColumn(name = "auteur_login")
    public Chami auteur;

    @Column(name = "dateDeCreation")
    public Date dateDeCreation;

    @Column(name = "dateDeModification")
    public Date dateDeModification;

    @Column(name = "description")
    public String description;

    @Column(name = "version")
    public Integer version;

    @Column(name = "point")
    public Integer point;

    @Column(name = "duree")
    public String duree;

    @OneToMany
    @Column(name = "notes")
    public List<Note> notes;

    @OneToMany
    @Column(name = "commentaires")
    public List<Commentaire> commentaires;

    @ManyToMany
    @Column(name = "tags")
    public List<Tag> tags;

    @OneToOne
    @Column(name = "arret")
    public Arret arret;

    @OneToMany
    @Column(name = "etapes")
    public List<Etape> etapes;



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
