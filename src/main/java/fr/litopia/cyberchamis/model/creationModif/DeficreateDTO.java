package fr.litopia.cyberchamis.model.creationModif;

import fr.litopia.cyberchamis.model.entity.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.Set;

public class DeficreateDTO {

    public String id;
    public String titre;
    public Date dateDeCreation;
    public Date dateDeModification;
    public String description;
    public Long version;
    public Integer pointTotaux;
    public String duree;
    public Chami auteur;
    public Set<Note> notes;
    public Set<Commentaire> commentaires;
    public Set<Tag> tags;
    public Arret arret;
    public Set<Etape> etapes;
    //etape
    public Long idEtape;
    public Integer numeroEtape;
    public String titreEtape;
    private String descriptionEtape;
    //Tache

    public String question;
    public String secret;
    public Integer point;
    public Set<Indice> indices;
    //Indication

    public String text;
    public String video;
    public String image;

}
