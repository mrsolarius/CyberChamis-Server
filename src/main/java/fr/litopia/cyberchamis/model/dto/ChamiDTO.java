package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Chami;

public class ChamiDTO {
    public long id;
    public String idGoogle;
    public Integer age;
    public String bio;
    public String username;

    public String profileImg;

    public Chami toEntity() {
        Chami c = new Chami();
        if (id != 0)
            c.setId(this.id);
        if (idGoogle != null)
            c.setIdGoogle(this.idGoogle);
        if (age != null)
            c.setAge(this.age);
        if (bio != null)
            c.setBio(this.bio);
        if (username != null)
            c.setUsername(this.username);
        if (profileImg != null)
            c.setProfileImg(profileImg);
        return c;
    }
}
