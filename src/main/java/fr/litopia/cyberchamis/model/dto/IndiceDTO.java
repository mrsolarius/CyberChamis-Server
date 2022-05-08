package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Indice;

public class IndiceDTO {

    public Long id;
    public String indice;
    public Integer pointsPerdus;
    public Integer numIndice;

    public Indice toEntity(){
        Indice indiceEn =new Indice();
        indiceEn.indice=indice;
        indiceEn.pointsPerdus=pointsPerdus;
        return indiceEn;
    }

    public Integer getNumIndice() {
        return numIndice;
    }
}
