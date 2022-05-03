package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Indice;

public class IndiceDTO {

    public long id;
    public String indice;
    public Integer pointsPerdus;
    public int numIndice;

    public Indice toEntity(){
        Indice indiceEn =new Indice();
        indiceEn.indice=indice;
        indiceEn.pointsPerdus=pointsPerdus;
        return indiceEn;
    }
}
