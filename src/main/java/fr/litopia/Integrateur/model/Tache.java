package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tache extends Etape {
    @Getter
    @Setter
    @Column(name = "question", nullable = false)
    public String question;

    @Column(name = "reponse", nullable = false)
    public String reponse;

    @Column(name = "secret", nullable = false)
    public String secret;

    @Column(name = "point", nullable = false)
    public Integer point;

    @OneToMany
    @Column(name = "indices", nullable = false)
    public List<Indice> indices;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public List<Indice> getIndices() {
        return indices;
    }

    public void setIndices(List<Indice> indices) {
        this.indices = indices;
    }

    /* renvoie true si la reponse est juste, false sinon */
    public boolean estJuste() {
        return reponse.compareToIgnoreCase(question) == 0;
    }

    /* renvoie le nb de points remporté par le joueur
    * Si la réponse est juste et qu'aucun indice n'a été utilisé, on renvoie les points de la question
    * Si la réponse est juste et qu'un ou plusieurs indices ont été utilisés, on renvoie les points de la question décrémentés des points des indices
    * Si la réponse est fausse, on renvoie 0.*/
    /*
    public int calculerPoint() {
        int pointJoueur = point;
        if (!estJuste()) {
            return 0;
        } else {
            for (Indice indice : indices
                 ) {
                if (indice.statut == StatutIndice.USED) {
                    pointJoueur -= indice.pointsPerdus;
                }
            }
        }
        return pointJoueur;
    }*/














}
