package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Arret {
    @Getter
    @Setter
    @Id
    //@OneToOne
    @Column(name = "codeArret", nullable = false)
    public String codeArret;

    @Column(name = "nomArret", nullable = false)
    public String nomArret;

    @Column(name = "streetMapUrl", nullable = false)
    public String streetMap;


}
