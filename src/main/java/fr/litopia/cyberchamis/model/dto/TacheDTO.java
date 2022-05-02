package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Tache;

public class TacheDTO extends EtapeDTO{
    String question;
    Integer point;

    public Tache toEntity(){
        Tache tache=new Tache();
        tache.question=this.question;
        tache.point=this.point;
        return tache;
    }
}
