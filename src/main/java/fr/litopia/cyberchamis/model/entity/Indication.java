package fr.litopia.cyberchamis.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Indication extends Etape{
    @Column(name = "indication",length = 1024)
    private String text;
    private String video;
    private String image;

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

    public void setText(String text) {
        this.text = text;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
