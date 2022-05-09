package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Arret;

public class ArretDTO {
    public String codeArret;
    public String nomArret;

    public String ville;
    public double latitude;
    public double longitude;

    public Arret toEntity() {
        Arret entity = new Arret();
        entity.setCodeArret(this.codeArret);
        entity.setNomArret(this.nomArret);
        entity.setVille(this.ville);
        entity.setLatitude(this.latitude);
        entity.setLongitude(this.longitude);
        return entity;
    }
}
