package fr.litopia.cyberchamis.model.dto;

import java.util.Date;
import java.util.List;

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

    /*
    public static Defi defiToDTO(DefiDTO defiDTO) {
        Defi defi = Defi.builder()
                .id(defiDTO.id)
                .titre(defiDTO.titre)
                .description(defiDTO.description)
                .dateDeCreation(defiDTO.dateDeCreation)
                .dateDeModification(defiDTO.dateDeModification)
                .version(defiDTO.version)
                .duree(defiDTO.duree)
                .auteur(defiDTO.auteur.toEntity())
                .tags(defiDTO.tags)
                .arret(defiDTO.arretDTO.ToEntity())
                .etapes(defiDTO.etapes).build();
        return defi;
    }*/

   // @Override
    //public DefiDTO toDTO(Defi defi) {
      //  return null;

    //}
}
