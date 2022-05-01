package fr.litopia.Integrateur.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Indication extends Etape{
    public String text;
    public String video;
    public String image;

    public String getText() {
        return text;
    }

    public String getVideo() {
        return video;
    }

    public String getImage() {
        return image;
    }
}
