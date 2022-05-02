package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Reponse;

public class ReponseDTO {
    public long id;
    public Integer numero;
    public Integer nbIndicesUtilises;
    public String reponseUtilisateur;

    public Reponse toEntity(){
        Reponse reponse=new Reponse();
        reponse.reponseUtilisateur=this.reponseUtilisateur;
        reponse.nbIndicesUtilises=this.nbIndicesUtilises;
        reponse.numero=this.numero;
        return reponse;

    }
}
