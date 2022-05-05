package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.CommentaireDTO;

import javax.persistence.*;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue
    @Column(name = "idCom", nullable = false)
    Long id;

    @Column(name = "commentaire", nullable = false, length = 128)
    String text;

    @ManyToOne(optional = false)
    Chami auteur;

    public Commentaire() {}
    public Commentaire(String text, Chami auteur) {
        this.setText(text);
        this.auteur = auteur;
    }
    public void setText(String text) {
        if(text.length() > 128){
            throw new IllegalArgumentException("Commentaire should be less than 128 characters");
        }
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public CommentaireDTO toDTO(){
        CommentaireDTO dto = new CommentaireDTO();
        dto.idCommentaire = id;
        dto.idUtilisateur = auteur.getId();
        dto.text = text;
        return dto;
    }
}
