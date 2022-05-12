package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.EtapeDTO;
import fr.litopia.cyberchamis.model.dto.TypeEtapeDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.DefiCreateDTO;
import fr.litopia.cyberchamis.model.dto.creationModif.EtapeCreateDTO;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false)
    @Min(0)
    private Integer numero;

    @Column(name = "titre", nullable = false, length = 32)
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name="banner")
    private String banner;


    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        if (numero == null) {
            throw new IllegalArgumentException("Numero cannot be null");
        }
        if (numero < 0) {
            throw new IllegalArgumentException("Numero cannot be negative");
        }
        this.numero = numero;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.isEmpty()) {
            throw new IllegalArgumentException("Titre cannot be null or empty");
        }
        if (titre.length() > 32) {
            throw new IllegalArgumentException("Titre cannot be longer than 32 characters");
        }
        this.titre = titre;
    }

    public void setDescription(String description) {
        if (description.length() > 255) {
            throw new IllegalArgumentException("Description cannot be longer than 255 characters");
        }
        this.description = description;
    }


    public EtapeDTO toDTO() {
        EtapeDTO dto = new EtapeDTO();
        dto.id = id;
        dto.numero = numero;
        dto.titre = titre;
        dto.description = description;
        dto.banner = banner;
        if (this instanceof Tache){
            dto.type = TypeEtapeDTO.TacheDTO;
            dto.question = ((Tache)this).getQuestion();
            dto.point = ((Tache)this).getPoint();
            dto.nbIndices = ((Tache)this).getIndices().size();
        } else if (this instanceof Indication){
            dto.type = TypeEtapeDTO.IndicationDTO;
            dto.text = ((Indication)this).getText();
            dto.image = ((Indication)this).getImage();
            dto.video = ((Indication)this).getVideo();
        }
        return dto;
    }
    public EtapeCreateDTO toCreatEtapeDTO() {
        EtapeCreateDTO dto = new EtapeCreateDTO();
        dto.idEtape=this.id;
        dto.numero = this.numero;
        dto.titreEtape=this.titre;
        dto.descriptionEtape=this.description;
        dto.banner=this.banner;
        if (this instanceof Tache){
            dto.type = TypeEtapeDTO.TacheDTO;
            dto.question= ((Tache)this).getQuestion();
            dto.point = ((Tache)this).getPoint();
            dto.indices = ((Tache)this).getIndices().stream().map(Indice::toDTO).collect(Collectors.toSet());
            dto.secret=((Tache)this).getSecret();
        } else if (this instanceof Indication){
            dto.type = TypeEtapeDTO.IndicationDTO;
            dto.text = ((Indication)this).getText();
            dto.video = ((Indication)this).getVideo();
        }
        return dto;
    }

    public void setBanner(String banner) {
        this.banner=banner;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

