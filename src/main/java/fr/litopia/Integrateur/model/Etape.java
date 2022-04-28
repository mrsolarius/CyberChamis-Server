package fr.litopia.Integrateur.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Etape {
    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "titre", nullable = false)
    public String titre;

    @Column(name = "description", nullable = false)
    public String description;

    @Column(name = "numero", nullable = false)
    public Integer numero;

    @Column(name = "statut", nullable = false)
    public StatutEtape statut;

}
