package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Tag {
    @Id
    @Column(name = "tag", nullable = false)
    public String text;

    @ManyToMany
    @Column(name = "defis", nullable = false)
    public List<Defi> defis;

}
