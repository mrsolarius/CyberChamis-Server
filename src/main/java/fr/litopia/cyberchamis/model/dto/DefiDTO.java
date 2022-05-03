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
    public Long version;
    public String duree;
    public ChamiDTO auteur;
    public double noteMoyenne;
    public List<TagDTO> tags;
    public ArretDTO arretDTO;
    public List<EtapeDTO> etapes;
    public Timestamp dateCreation;
    public Timestamp dateDeModification;
    public Integer pointTotaux;


    public Defi toEntity() {
        Defi entity = new Defi();
        entity.titre=titre;
        entity.id=id;
        entity.setDescription(description);
        entity.version=version;
        entity.duree=duree;
        entity.pointTotaux=pointTotaux;
        entity.auteur=auteur.toEntity();
        //entity.tags= tags.stream().map(TagDTO::toEntity).collect(Collectors.toSet());
        entity.arret=arretDTO.toEntity();
        entity.etapes=etapes.stream().map(EtapeDTO::toEntity).collect(Collectors.toSet());
        return entity;
    }

}
