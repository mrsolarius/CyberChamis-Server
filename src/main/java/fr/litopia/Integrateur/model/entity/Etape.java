package fr.litopia.Integrateur.model.entity;

import fr.litopia.Integrateur.model.dto.EtapeDTO;
import fr.litopia.Integrateur.model.dto.TypeEtapeDTO;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "numero", nullable = false)
    @Min(0)
    public Integer numero;

    @Column(name = "titre", nullable = false, length = 32)
    public String titre;

    @Column(name = "description")
    private String description;

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
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
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
        if (this instanceof Tache){
            dto.type = TypeEtapeDTO.TacheDTO;
            dto.question = ((Tache)this).getQuestion();
            dto.point = ((Tache)this).getPoint();
        } else if (this instanceof Indication){
            dto.type = TypeEtapeDTO.IndicationDTO;
            dto.text = ((Indication)this).getText();
            dto.image = ((Indication)this).getImage();
            dto.video = ((Indication)this).getVideo();
        }
        return dto;
    }
}

