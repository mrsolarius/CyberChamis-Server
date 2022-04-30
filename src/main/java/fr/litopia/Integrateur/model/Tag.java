package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tag {
    @Id
    @Column(name = "tag", nullable = false)
    public String tag;

    @ManyToMany
    @Column(name = "defis", nullable = false)
    public Set<Defi> defis;



}
