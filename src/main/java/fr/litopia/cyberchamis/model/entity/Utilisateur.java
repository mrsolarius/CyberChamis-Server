package fr.litopia.cyberchamis.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy= InheritanceType.JOINED) //@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur", nullable = false)
    public Long id;
    @OneToMany
    @Column(name = "visites", nullable = false)
    public Set<Visite> vistes;

    public void addVisite(Visite visite) {
        vistes.add(visite);
    }

    public Long getId(){
        return this.id;
    }
}
