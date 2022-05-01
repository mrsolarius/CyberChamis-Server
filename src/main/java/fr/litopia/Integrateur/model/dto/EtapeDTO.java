package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.entity.Etape;
import fr.litopia.Integrateur.model.entity.Indication;
import fr.litopia.Integrateur.model.entity.Tache;

public class EtapeDTO {
    public Long id;
    public String titre;
    public Integer numero;
    public String description;
    public TypeEtapeDTO type;
    //dans indication
    public String text;
    public String video;
    public String image;
    //Tache
    public String question;
    public Integer point;

    public Etape toEntity() {
        Etape entity = null;
        if (type == TypeEtapeDTO.TacheDTO) {
            entity = new Tache(question,point);
        }
        else if(type == TypeEtapeDTO.IndicationDTO) {
            entity = new Indication();
        }
        return entity;
    }
}