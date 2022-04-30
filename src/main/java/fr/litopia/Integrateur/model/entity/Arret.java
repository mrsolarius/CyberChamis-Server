package fr.litopia.Integrateur.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Arret {
    @Id
    @Column(name = "codeArret", nullable = false, length = 12)
    public String codeArret;

    @Column(name = "nomArret", nullable = false, length = 50)
    public String nomArret;

    @Column(name = "streetMapUrl", nullable = false)
    public String streetMap;

    Arret(String codeArret, String nomArret, String streetMap) {
        this.setCodeArret(codeArret);
        this.setNomArret(nomArret);
        this.setStreetMap(streetMap);
    }

    public Arret() {
    }

    public void setCodeArret(String codeArret) {
        if(codeArret.length() > 12) {
            throw new IllegalArgumentException("CodeArret must be 12 characters long");
        }
        this.codeArret = codeArret;
    }
    public void setNomArret(String nomArret) {
        if(nomArret.length() > 50) {
            throw new IllegalArgumentException("NomArret must be 50 characters long");
        }
        this.nomArret = nomArret;
    }

    public void setStreetMap(String streetMap) {
        if (streetMap.length() > 256) {
            throw new IllegalArgumentException("StreetMap must be 256 characters long");
        }
        this.streetMap = streetMap;
    }

    public String getCodeArret() {
        return codeArret;
    }

    public String getNomArret() {
        return nomArret;
    }

    public String getStreetMap() {
        return streetMap;
    }
}
