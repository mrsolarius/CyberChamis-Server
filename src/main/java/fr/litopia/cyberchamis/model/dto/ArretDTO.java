package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Arret;

public class ArretDTO {
    public String codeArret;
    public String nomArret;

    public String ville;
    public long latitude;
    public long longitude;

    public Arret toEntity() {
        Arret entity = new Arret();
        entity.codeArret = this.codeArret;
        entity.nomArret = this.nomArret;
        entity.ville = this.ville;
        entity.latitude = this.latitude;
        entity.longitude = this.longitude;
        return entity;
    }
}
