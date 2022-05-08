package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.VisiteDTO;

import javax.persistence.*;
import java.util.*;
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

    @OneToOne(fetch = FetchType.EAGER)
    public Defi defi;

    @OneToMany(fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.ALL)
    @Column(nullable = false)
    public Set<Reponse> reponses;

    public Visite(Defi defi, Utilisateur utilisateur){
        this.defi=defi;
        utilisateur.addVisite(this);
        statut = StatutVisite.ENCOURS;
        points = 0;
        etapeCourante = 0;
        this.reponses = new HashSet<>();
        Etape currentEtape = getEtapeCourante();
        if(currentEtape instanceof Tache){
            if(getReponseCourante()==null) {
                Reponse reponse = new Reponse(etapeCourante);
                this.reponses.add(reponse);
            }
        }
    }

    public Visite() {}

    // repo
    private Reponse getReponse(int numero) {
        for (Reponse reponse : reponses) {
            if (reponse.numero == numero) {
                return reponse;
            }
        }
        return null;
    }

    private List<Reponse> getSortReponses() {
        if (this.statut!= StatutVisite.ENCOURS) {
            throw new RuntimeException("Visite not in progress");
        }
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
    public Etape etapeSuivante() throws RuntimeException {
        if(statut != StatutVisite.ENCOURS){
            throw new RuntimeException("Visite not in progress");
        }
        if(etapeCourante +1 > defi.getSortEtapes().size() - 1){
            throw new RuntimeException("No more steps");
        }
        etapeCourante++;
        Etape currentEtape = getEtapeCourante();
        if(currentEtape instanceof Tache){
            if(getReponseCourante()==null) {
                Reponse reponse = new Reponse(etapeCourante);
                this.reponses.add(reponse);
            }
        }
        return currentEtape;
    }

    public Etape etapePrecedente() throws RuntimeException {
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
                try {
                    Reponse reponse = getReponse(etape.getNumero());
                    if (reponse != null) {
                        if (tache.isSecret(this.getReponse(etape.getNumero()).getReponseUtilisateur())) {
                            tempPoints += tache.getPoint();
                            if (reponse.getNbIndicesUtilises() +1> 0) {
                                for (int i = 0; i < reponse.getNbIndicesUtilises()+1; i++) {
                                    tempPoints -= tache.getSortedIndices().get(i).getPointsPerdus();
                                }
                            }
                        }
                    }
                } catch (Exception ignored) {}
            }
        }
        this.points = tempPoints;
    }

    public Indice revelerIndiceCourant(){
        if (this.statut!= StatutVisite.ENCOURS) {
            throw new RuntimeException("Visite not in progress");
        }
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

    public int getCostOfNextIndice() {
        if (this.statut != StatutVisite.ENCOURS) {
            throw new RuntimeException("Visite not in progress");
        }
        int cost = 0;
        Etape e = this.getEtapeCourante();
        if(e instanceof Tache) {
            Tache tache = (Tache) e;
            Reponse response = this.getReponseCourante();
            if (response != null) {
                Indice indice = tache.getSortedIndices().get(response.nbIndicesUtilises + 1);
                cost = indice.getPointsPerdus();
            }
        }
        return cost;
    }

    public boolean verificationReponse(String reponse) throws Exception {
        if (this.statut!= StatutVisite.ENCOURS) {
            throw new RuntimeException("Visite not in progress");
        }
        Reponse r = getReponseCourante();
        if (r == null) return false;
        if (!(getEtapeCourante() instanceof Tache)) return false;
        r.setReponseUtilisateur(reponse);
        Tache tache = (Tache) getEtapeCourante();
        var isCorrect = tache.isSecret(reponse);
        r.setCorrect(isCorrect);
        r.setHasResponse(true);
        return isCorrect;
    }

    public VisiteDTO toDTO() {
        VisiteDTO dto = new VisiteDTO();
        dto.id=this.id;
        dto.etapeCourante=this.getEtapeCourante().toDTO();
        dto.points=this.points;
        dto.statut = this.statut;
        dto.defi=this.defi.toDTO();
        if(this.getReponseCourante()!=null)
            dto.reponseCourante= this.getReponseCourante().toDTO();
        return dto;
    }


    public List<Indice> getIndices() {
        if (this.statut!= StatutVisite.ENCOURS) {
            throw new RuntimeException("Visite not in progress");
        }
        Etape e = this.getEtapeCourante();
        List<Indice> indices = new ArrayList<>();
        if(e instanceof Tache) {
            Tache tache = (Tache) e;
            Reponse response = this.getReponseCourante();
            if (response != null) {
                for (int i = 0; i < response.getNbIndicesUtilises()+1; i++) {
                    indices.add(tache.getSortedIndices().get(i));
                }
            }
        }
        return indices;
    }

    public StatutVisite getStatus() {
        return this.statut;
    }
}
