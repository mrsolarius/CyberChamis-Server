package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Chami;

public class ChamiDTO {
    public long id;
    public String idGoogle;
    public Integer age;
    public String bio;
    public String username;

    public Chami toEntity() {
        Chami c = new Chami();
        if (id != 0)
            c.id = this.id;
        if (idGoogle != null)
            c.idGoogle=this.idGoogle;
        if (age != null)
            c.age=this.age;
        if (bio != null)
            c.bio=this.bio;
        if (username != null)
            c.username=this.username;
        return c;
    }
}
