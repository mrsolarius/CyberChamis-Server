package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.ArretDTO;

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

    public String ville;

    public long latitude;

    public long longitude;

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


    public String getCodeArret() {
        return codeArret;
    }

    public String getNomArret() {
        return nomArret;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public ArretDTO toDTO() {
        ArretDTO dto = new ArretDTO();
        dto.codeArret = this.codeArret;
        dto.nomArret = this.nomArret;
        dto.ville = this.ville;
        dto.latitude = this.latitude;
        dto.longitude = this.longitude;
        return dto;
    }

}
