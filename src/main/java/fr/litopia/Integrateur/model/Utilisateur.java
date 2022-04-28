package fr.litopia.Integrateur.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.JOINED) //@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @Column(name = "idUtilisateur", nullable = false)
    Integer id;


}
