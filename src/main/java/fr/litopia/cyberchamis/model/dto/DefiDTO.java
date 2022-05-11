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
        entity.titre=titre;
        entity.id=id;
        entity.setDescription(description);
        entity.duree=duree;
        entity.pointTotaux=pointTotaux;
        entity.img=img;
        entity.miniDescription=miniDescription;
        entity.auteur=auteur.toEntity();
        //entity.tags= tags.stream().map(TagDTO::toEntity).collect(Collectors.toSet());
        entity.arret=arretDTO.toEntity();
        entity.etapes=etapes.stream().map(EtapeDTO::toEntity).collect(Collectors.toSet());
        return entity;
    }

}
