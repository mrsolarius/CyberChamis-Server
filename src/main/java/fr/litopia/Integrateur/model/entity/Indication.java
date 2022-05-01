package fr.litopia.Integrateur.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Indication extends Etape{
    public String text;
    public String video;
    public String image;
}
