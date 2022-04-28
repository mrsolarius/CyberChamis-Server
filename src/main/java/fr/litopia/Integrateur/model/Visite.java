package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Visite {
    @Getter
    @Setter
    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "statut", nullable = false)
    public String statut;

    @Column(name = "point", nullable = false)
    public Integer point;
    /*
    @OneToOne
    @Column(name = "etapeCourante", nullable = false)
    public Etape etapeCourante; faut faire le getter*/

    @OneToMany
    @Column(name = "etapeCourante", nullable = false)
    public List<Etape> etapes;


}
