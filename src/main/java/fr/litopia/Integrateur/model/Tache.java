package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tache extends Etape {
    @Getter
    @Setter
    @Column(name = "question", nullable = false)
    public String question;

    @Column(name = "reponse", nullable = false)
    public String reponse;

    @Column(name = "secret", nullable = false)
    public String secret;

    @Column(name = "point", nullable = false)
    public Integer point;

    @OneToMany
    @Column(name = "indices", nullable = false)
    public List<Indice> indices;

}
