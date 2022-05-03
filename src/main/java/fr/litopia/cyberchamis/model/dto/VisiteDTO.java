package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.StatutVisite;

import java.util.List;

public class VisiteDTO {
    public Long id;
    public StatutVisite statut;
    public EtapeDTO etapeCourante;
    public Integer points;
    public DefiDTO defi;
    public ReponseDTO reponseCourante;
}
