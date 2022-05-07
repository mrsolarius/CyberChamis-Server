package fr.litopia.cyberchamis.model.dto.creationModif;

import fr.litopia.cyberchamis.model.entity.Indication;

public class IndicationCreateDTO extends EtapeCreateDTO{
    String text;

    public Indication toEntity(){
        Indication indice =new Indication();
        indice.text=text;
        return indice;
    }
}
