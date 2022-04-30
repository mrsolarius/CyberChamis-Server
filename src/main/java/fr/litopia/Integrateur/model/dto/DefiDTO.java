package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.dto.ChamiDTO;
import fr.litopia.Integrateur.model.dto.ArretDTO;
import fr.litopia.Integrateur.model.entity.Tag;

import java.util.Date;
import java.util.List;

public class DefiDTO {
    public String titre;
    public String description;
    public Date dateDeCreation;
    public Date dateDeModification;
    public Integer version;
    public String duree;
    public ChamiDTO auteur;
    public double noteMoyenne;
    public List<String> tags;
    public ArretDTO arretDTO;
    public List<EtapeDTO> etapes;
}
