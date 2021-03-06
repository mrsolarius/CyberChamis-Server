package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.ChamiDTO;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.Min;


@Entity
@Builder
public class Chami extends Utilisateur {
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "idGoogle", nullable = false, unique = true)
    private String idGoogle;

    @Column(name = "age")
    @Min(value = 13, message = "You must be at least 13 years old")
    private Integer age;

    @Column(name = "bio", length = 255)
    private String bio;


    @Column(name = "profileImg")
    private String profileImg;

    public Chami(String username) {
        super();
        this.setUsername(username);
    }

    public Chami() {
        super();
    }

    public void setUsername(String login) {
        if(login.length() > 20) {
            throw new IllegalArgumentException("Login must be 20 characters long");
        }
        this.username = login;
    }

    public void setAge(Integer age) {
        if(age != null && age < 13) {
            throw new IllegalArgumentException("You must be at least 13 years old");
        }
        this.age = age;
    }

    public void setBio(String bio) {
        if(bio != null && bio.length() > 255) {
            throw new IllegalArgumentException("Bio must be 255 characters long");
        }
        this.bio = bio;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getIdGoogle() {
        return idGoogle;
    }


    public String getUsername() {
        return this.username;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getBio() {
        return this.bio;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public ChamiDTO toDTO() {
        ChamiDTO dto = new ChamiDTO();
        dto.id= super.getId();
        dto.idGoogle=this.idGoogle;
        dto.age=this.age;
        dto.bio=this.bio;
        dto.username=this.username;
        dto.profileImg=this.profileImg;
        return dto;
    }
}
