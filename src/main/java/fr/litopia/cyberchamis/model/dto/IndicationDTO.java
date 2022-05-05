package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Indication;
import fr.litopia.cyberchamis.model.entity.Indice;

public class IndicationDTO  {
    String text;
    public Indication toEntity(){
        Indication indice =new Indication();
        indice.text=text;
        return indice;
    }
}
