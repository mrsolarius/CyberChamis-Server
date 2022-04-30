package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.dto.interfaces.DTO;
import fr.litopia.Integrateur.model.entity.Chami;
import fr.litopia.Integrateur.model.entity.Defi;

public class ChamiDTO implements DTO<Chami> {
    public long id;
    public Integer age;
    public String bio;
    public String username;

    @Override
    public Chami toEntity() {
        Chami c = new Chami();
        if (id != 0)
            c.id = this.id;
        if (age != null)
            c.age=this.age;
        if (bio != null)
            c.bio=this.bio;
        if (username != null)
            c.username=this.username;
        return c;
    }
}
