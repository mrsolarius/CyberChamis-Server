package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.entity.StatutVisite;

import java.util.List;

public class VisiteDTO {
    StatutVisite statut;
    EtapeDTO etapeCourante;
    Integer points;
    DefiDTO defi;
    List<ReponseDTO> reponse;
}
