package fr.litopia.Integrateur.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.JOINED) //@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @Column(name = "idUtilisateur", nullable = false)
    public Integer id;
    @OneToMany
    @Column(name = "visites", nullable = false)
    public List<Visite> vistes;


}
