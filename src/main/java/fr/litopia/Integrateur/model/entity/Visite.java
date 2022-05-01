package fr.litopia.Integrateur.model.entity;

import fr.litopia.Integrateur.model.dto.VisiteDTO;
import org.apache.coyote.Response;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Visite {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "statut", nullable = false)
    public StatutVisite statut;

    @Column(name = "points", nullable = false)
    public Integer points;

    @Column(name = "etapeCourante", nullable = false)
    public int etapeCourante;

    @OneToOne
    public Defi defi;

    @OneToMany
    public Set<Reponse> reponses;

    public Visite(Defi defi, Utilisateur utilisateur){
        this.defi=defi;
        utilisateur.addVisite(this);
    }

    public Visite() {

    }

    // repo
    private Reponse getReponse(int numero) {
        if(numero < 0 || numero > reponses.size()){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        for (Reponse reponse : reponses) {
            if (reponse.numero == numero) {
                return reponse;
            }
        }
        return null;
    }

    private List<Reponse> getSortReponses() {
        //sort reponses by numeros
        return this.reponses.stream().sorted(Comparator.comparingInt(Reponse::getNumero)).collect(Collectors.toList());
    }

    /**
     *  retourne l'etape courante à partir de son id
     * @return Etape l'etape courante
     */
    public Etape getEtapeCourante(){
        return defi.getSortEtapes().get(etapeCourante);
    }

    public Reponse getReponseCourante(){
        return getReponse(etapeCourante);
    }

    /**
     * actualiser etapeCourante
     * si c'est une tache : création d'une réponse
     * throw une erreur si il n'y a pas d'étape suivante
     */
    public Etape etapeSuivante() {
        if(statut != StatutVisite.ENCOURS){
            throw new RuntimeException("Visite not in progress");
        }
        if(etapeCourante +1 > defi.getSortEtapes().size() - 1){
            throw new RuntimeException("No more steps");
        }
        etapeCourante++;
        Etape currentEtape = getEtapeCourante();
        if(currentEtape instanceof Tache){
            Reponse reponse = new Reponse(etapeCourante);
            this.reponses.add(reponse);
        }
        return currentEtape;
    }

    public Etape etapePrecedente() {
        if(statut != StatutVisite.ENCOURS){
            throw new RuntimeException("Visite not in progress");
        }
        if(etapeCourante -1 < 0){
            throw new RuntimeException("No previous steps");
        }
        etapeCourante--;
        return getEtapeCourante();
    }

    public void setStatut(StatutVisite statut) throws Exception {
        if(this.statut == StatutVisite.FINISHED || this.statut == StatutVisite.ABONDON){
            throw new IllegalArgumentException("Visite already finished");
        }
        switch (statut) {
            case FINISHED -> {
                if (etapeCourante != defi.getSortEtapes().size() - 1) {
                    throw new RuntimeException("Could not finish the visite while not at the end of the defi");
                }
                calculPoints();
            }
            case ABONDON, PAUSE -> calculPoints();
        }
        this.statut = statut;
    }

    public void calculPoints() {
        Integer tempPoints = 0;
        for (Etape etape : defi.getSortEtapes()) {
            if (etape instanceof Tache) {
                Tache tache = (Tache) etape;
                Reponse reponse = getReponse(etape.getNumero());
                if(tache.isSecret(this.getReponse(etape.getNumero()).getReponseUtilisateur())){
                    tempPoints += tache.getPoint();
                    if(reponse.getNbIndicesUtilises()>0){
                        for(int i = 0; i < reponse.getNbIndicesUtilises(); i++){
                            tempPoints -= tache.getSortedIndices().get(i).getPointsPerdus();
                        }
                    }
                };
            }
        }
        this.points = tempPoints;
    }

    public Indice revelerIndiceCourant(){

        Etape e = this.getEtapeCourante();
        if(e instanceof Tache){
            Tache tache = (Tache) e;
            Reponse response = this.getReponseCourante();
            if (response!=null){
                Indice indice = tache.getSortedIndices().get(response.nbIndicesUtilises+1);
                if(indice!=null){
                    response.addIndiceUtilise();
                    return indice;
                }
            }
        }
        return null;
    }

    public boolean verificationReponse(String reponse){
        Reponse r = getReponseCourante();
        if (r == null) return false;
        if (!(getEtapeCourante() instanceof Tache)) return false;
        r.setReponseUtilisateur(reponse);
        Tache tache = (Tache) getEtapeCourante();
        return tache.isSecret(reponse);
    }

    public VisiteDTO toDTO() {
        VisiteDTO dto = new VisiteDTO();
        dto.id=this.id;
        dto.etapeCourante=this.getEtapeCourante().toDTO();
        dto.points=this.points;
        dto.statut = this.statut;
        dto.defi=this.defi.toDTO();
        dto.reponse= this.getSortReponses().stream().map(Reponse::toDTO).collect(Collectors.toList());
        return dto;
    }


}
