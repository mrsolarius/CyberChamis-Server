package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Etape;
import fr.litopia.cyberchamis.model.entity.Tag;

import java.util.Date;
import java.util.List;
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
    public Date dateCreation;
    public Date dateDeModification;
    public Integer pointTotaux;


   /* public Defi toEntity() {
        Defi entity = new DefiDTO();
        entity.titre=titre;
        entity.id=id;
        entity.description=description;
        entity.version=version;
        entity.duree=duree;
        entity.pointTotaux=pointTotaux;
        entity.auteur=auteur.toDTO();
        entity.tags=tags.stream().map(Tag::toDTO).collect(Collectors.toList());
        entity.arretDTO=arret.toDTO();
        entity.noteMoyenne=getMoyenne();
        entity.etapes=etapes.stream().map(Etape::toDTO).collect(Collectors.toList());
        return entity;
    }
*/

}
