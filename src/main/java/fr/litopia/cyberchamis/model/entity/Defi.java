package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.DefiDTO;
import fr.litopia.cyberchamis.model.dto.RatingDTO;
import fr.litopia.cyberchamis.model.dto.RatingDefiDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.DefiCreateDTO;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.security.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Builder
public class Defi {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "idDefi", nullable = false)
    public String id;

    @Column(name = "titre", nullable = false, length = 45)
    public String titre;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateDeCreation", nullable = false)
    public Date dateDeCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateDeModification", nullable = false)
    public Date dateDeModification;

    @Column(name = "description", nullable = false, length = 1024)
    public String description;

    @Column(name = "miniDescription", nullable = false, length = 128)
    public String miniDescription;

    @Version
    @Column(name = "version")
    @Min(value = 1)
    public Long version;

    @Column(name = "pointTotaux")
    public Integer pointTotaux;

    @Column(name = "duree")
    public String duree;

    @Column
    public String img;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    public Chami auteur;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "notes")
    public Set<Note> notes;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "commentaires")
    public Set<Commentaire> commentaires;

    @ManyToMany(fetch = FetchType.LAZY)
    @Column(name = "tags")
    public Set<Tag> tags;

    @OneToOne(fetch = FetchType.EAGER)
    public Arret arret;

    @OneToMany(orphanRemoval = true)
    public Set<Etape> etapes;

    public Defi(){
        this.id = UUID.randomUUID().toString();
        this.notes = new HashSet<>();
        this.commentaires = new HashSet<>();
        this.tags = new HashSet<>();
        this.etapes = new HashSet<>();
    }
    public String getId() {
        return id;
    }
    public void setDateDeCreation (Date date) {
        this.dateDeCreation = date;
    }
    public void setArret(Arret arret) {
        this.arret = arret;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.isEmpty()) {
            throw new IllegalArgumentException("Titre cannot be null or empty");
        }
        if (titre.length() > 45) {
            throw new IllegalArgumentException("Titre cannot be longer than 45 characters");
        }
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    //public Date getDateDeCreation() {
      //  return dateDeCreation;
    //}

    public void setDescription(String description) {
        if(description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(description.length() > 1024) {
            throw new IllegalArgumentException("Description cannot be longer than 128 characters");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setAuteur(Chami auteur) {
        if(this.auteur != null) {
            throw new IllegalArgumentException("Auteur cannot be changed");
        }
        if (auteur == null) {
            throw new IllegalArgumentException("Auteur cannot be null");
        }
        this.auteur = auteur;
    }

    public Chami getAuteur() {
        return auteur;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void ajouterOuModifierNote(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        this.notes.removeIf(n -> n.getId().equals(note.getId()));
        this.notes.add(note);
    }

    public void ajouterOuModifierCommentaire(Commentaire commentaire) {
        if (commentaire == null) {
            throw new IllegalArgumentException("Commentaire cannot be null");
        }
        this.commentaires.removeIf(c -> c.getId().equals(commentaire.getId()));
        this.commentaires.add(commentaire);
    }

    public void supprimerUnCommentaire(Commentaire commentaire) {
        if (commentaire == null) {
            throw new IllegalArgumentException("Commentaire cannot be null");
        }
        this.commentaires.removeIf(c -> c.getId().equals(commentaire.getId()));
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
        if (!tag.defis.contains(this)) {
            tag.addDefi(this);
        }
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        if(tag.defis.contains(this)) {
            tag.removeDefi(this);
        }
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public double getMoyenne(){
        double moyenne = 0;
        for (Note note : notes) {
            moyenne += note.getNote();
        }
        return moyenne / notes.size();
    }

    public void addEtape(Etape etape){
        if (!etapes.contains(etape)) {
            int index = etapes.size();
            etape.setNumero(index);
            this.etapes.add(etape);
        }
    }

    public void removeEtape(Etape etape){
        if (etapes.contains(etape)) {
            int index = etape.getNumero();
            for ( Etape e : etapes) {
                if (e.getNumero() > index) {
                    e.setNumero(e.getNumero() - 1);
                }
            }
            this.etapes.remove(etape);
        }
    }

    public void moveEtape(Etape etape, int index){
        if(!etapes.contains(etape)){
            throw new IllegalArgumentException("Etape not found");
        }
        if (index < 0 || index >= etapes.size()) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        for (Etape e : etapes) {
            if (e.getNumero().equals(index)) {
                e.setNumero(etape.getNumero());
                etape.setNumero(index);
            }
        }
    }

    public List<Etape> getSortEtapes(){
        List<Etape> sortEtapes = new ArrayList<>(etapes);
        sortEtapes.sort(Comparator.comparing(Etape::getNumero));
        return sortEtapes;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        if(miniDescription == null || miniDescription.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if(miniDescription.length() > 128) {
            throw new IllegalArgumentException("Description cannot be longer than 128 characters");
        }
        this.miniDescription = miniDescription;
    }

    @PrePersist
    protected void onCreate() {
        this.pointTotaux = totalPoints();
    }

    @PreUpdate
    protected void onUpdate() {
        this.pointTotaux = totalPoints();
    }

    public int totalPoints(){
        int total = 0;
        for (Etape etape : etapes) {
            if (etape instanceof Tache){
                total += ((Tache) etape).getPoint();
            }
        }
        return total;
    }

    public Set<Etape> getEtapes() {
        return etapes;
    }

    public DefiDTO toDTO() {
        DefiDTO dto = new DefiDTO();
        dto.id=id;
        dto.titre=titre;
        dto.description=description;
        dto.dateCreation=dateDeCreation;
        dto.dateDeModification= dateDeModification;
        dto.version=version;
        dto.duree=duree;
        dto.pointTotaux=pointTotaux;
        dto.auteur=auteur.toDTO();
        dto.tags=tags.stream().map(Tag::toDTO).collect(Collectors.toList());
        dto.arretDTO=arret.toDTO();
        dto.noteMoyenne=getMoyenne();
        dto.etapes=etapes.stream().map(Etape::toDTO).collect(Collectors.toList());
        return dto;
    }

    public RatingDefiDTO getDefisRatting(){
        RatingDefiDTO dto = new RatingDefiDTO();
        List<RatingDTO> dtoList = new ArrayList<>();
        dto.idDefi = id;
        dto.nbTotalGrade = this.notes.size();
        for(int i = 1; i<=5;i++){
            final var icheck = i;
            RatingDTO ratingDTO = new RatingDTO();
            ratingDTO.note=icheck;
            ratingDTO.number= this.notes.stream().filter(note -> note.note == icheck).toList().size();
            dtoList.add(ratingDTO);
        }
        dto.gradeList=dtoList;
        return dto;
    }

    public DefiCreateDTO toCreateDefiDTO() {
        DefiCreateDTO dto = new DefiCreateDTO();
        dto.id=id;
        dto.titre=titre;
        dto.description=description;
        dto.miniDescription=miniDescription;
        dto.duree=duree;
        dto.version=version;
        dto.auteurId=auteur.getId();
        dto.tags=tags.stream().map(Tag::getTag).collect(Collectors.toSet());
        dto.etapes=etapes.stream().map(Etape::toCreatEtapeDTO).collect(Collectors.toSet());
        dto.arret=arret.toDTO();
        return dto;
    }

    public void setImg(String img) {
        this.img=img;
    }
}
