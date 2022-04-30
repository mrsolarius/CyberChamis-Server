package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Visite {
    @Getter
    @Setter
    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "statut", nullable = false)
    public StatutVisite statut;

    @Column(name = "points", nullable = false)
    public Integer points;

    @Column(name = "etapeCourante", nullable = false)
    public int etapeCourante;

    @OneToMany
    @Column(name = "etapes", nullable = false)
    public List<Etape> etapes;

    /* retourne l'etape courante à partir de son id */
    public Etape getEtapeCourante() throws IndexOutOfBoundsException {
        return etapes.get(etapeCourante);
    }

    /*
    public void etapeSuivante() {
        getEtapeCourante().statut = StatutEtape.FINISHED;
        etapeCourante += 1;
        getEtapeCourante().statut = StatutEtape.CURRENT;
    }

    public void etapePrecedente() {
        getEtapeCourante().statut = StatutEtape.WAITING;
        etapeCourante -= 1;
        getEtapeCourante().statut = StatutEtape.CURRENT;
    }

    public void setStatut(StatutVisite statut) throws Exception {
        if (statut == StatutVisite.FINISHED) {
            for (Etape etape : etapes) {
                if (etape.statut != StatutEtape.FINISHED) {
                    throw new Exception("Statut Visite Fini impossible : toutes les étapes ne sont pas terminées.");
                }
            }
            calculPoints();
        } else if (statut == StatutVisite.ABONDON) {
            calculPoints();
        }
        this.statut = statut;
    }

    public void calculPoints() {
        for (Etape etape :
             etapes) {
            //trouver un moyen de compter les points des taches
        }
    }*/
}
