package com.example;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Chami {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public String login;

    @Column(name = "naissance", nullable = false)
    public Date naissance;

    @Column(name = "description", nullable = false)
    public String description;

}
