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
    private String id;

    @Column(name = "name")
    private String titre;

    @Column(name = "dateDeCreation")
    private Date dateDeCreation;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "auteur_login")
    private Chami auteur;
}
