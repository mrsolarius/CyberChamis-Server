package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.StatutVisite;

import java.util.Date;
import java.util.List;

public class VisiteDTO {
    public Long id;
    public StatutVisite statut;
    public EtapeDTO etapeCourante;
    public Integer points;
    public DefiDTO defi;

    public Date debut;
    public Date finVisite;
    public ReponseDTO reponseCourante;

    /*public Visite toEntity(){
        Visite visite =  new Visite();
        visite.id = this.id;
        visite.points=this.points;
        visite.statut= StatutVisite.valueOf(this.statut);
        //louis doit le faire
        /*visite.etapeCourante=this;
        visite.defi=this.defi.;
        visite.reponses = this.reponses.stream().map(ReponseDTO::toEntity).collect(Collectors.toList());
        List<String> l1 = new ArrayList<String>();
        for (Reponse r : visite.reponses) {
            l1.add(r.getId());
        }
        visite.reponses.add(l1);
        return visite;
    }*/
}