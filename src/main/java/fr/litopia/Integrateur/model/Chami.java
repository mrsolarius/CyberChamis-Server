package fr.litopia.Integrateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Chami {
    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "age")
    private Integer age;

    @OneToMany
    List<Defi> defis;
}
