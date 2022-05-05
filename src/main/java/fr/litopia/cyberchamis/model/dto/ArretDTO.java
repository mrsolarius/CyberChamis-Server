package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Arret;

public class ArretDTO {
    public String codeArret;
    public String nomArret;
    public String streetMap;

    public Arret toEntity() {
        Arret entity = new Arret();
        entity.codeArret = this.codeArret;
        entity.nomArret = this.nomArret;
        entity.streetMap = this.streetMap;
        return entity;
    }
}
