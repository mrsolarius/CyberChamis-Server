package fr.litopia.cyberchamis.model.entity;

import javax.persistence.Entity;

@Entity
public class Indication extends Etape{
    public String text;
    public String video;
    public String image;

    public Indication(){
    }
    public Indication(String text){
        this.text=text;
    }
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
