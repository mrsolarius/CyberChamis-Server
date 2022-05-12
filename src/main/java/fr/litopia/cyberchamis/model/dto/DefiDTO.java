package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Etape;
import fr.litopia.cyberchamis.model.entity.Tag;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefiDTO {
    public String id;
    public String titre;
    public String description;
    public String miniDescription;
    public Long version;
    public String duree;
    public ChamiDTO auteur;
    public double noteMoyenne;
    public List<TagDTO> tags;
    public ArretDTO arretDTO;
    public List<EtapeDTO> etapes;
    public Date dateCreation;
    public Date dateDeModification;
    public Integer pointTotaux;
    public String img;

    public Defi toEntity() {
        Defi entity = new Defi();
        entity.setTitre(titre);
        entity.setId(id);
        entity.setDescription(description);
        entity.setDuree(duree);
        entity.pointTotaux=pointTotaux;
        entity.setImg(img);
        entity.setMiniDescription(miniDescription);
        entity.setAuteur(auteur.toEntity());
        entity.setArret(arretDTO.toEntity());
        entity.setEtapes(etapes.stream().map(EtapeDTO::toEntity).collect(Collectors.toSet()));
        return entity;
    }

}
