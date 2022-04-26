package fr.litopia.Integrateur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Date;


@EnableJpaRepositories("fr.litopia.Integrateur.repository")
@Entity
@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class Defi {
    @Id
    @Column(name = "id", nullable = false)
    public String id;

    @Column(name = "name")
    public String titre;

    @Column(name = "dateDeCreation")
    public Date dateDeCreation;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name = "auteur_login")
    public Chami auteur;
}
