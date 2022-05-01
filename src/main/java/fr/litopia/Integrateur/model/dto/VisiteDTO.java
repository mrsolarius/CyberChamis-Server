package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.entity.StatutVisite;

import java.util.List;

public class VisiteDTO {
    public Long id;
    public StatutVisite statut;
    public EtapeDTO etapeCourante;
    public Integer points;
    public DefiDTO defi;
    public List<ReponseDTO> reponse;
}
