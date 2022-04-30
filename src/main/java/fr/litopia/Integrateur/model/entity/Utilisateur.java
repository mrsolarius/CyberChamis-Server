package fr.litopia.Integrateur.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy= InheritanceType.JOINED) //@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur", nullable = false)
    public Long id;
    @OneToMany
    @Column(name = "visites", nullable = false)
    public List<Visite> vistes;


}
