package fr.litopia.Integrateur.model.dto;

import fr.litopia.Integrateur.model.entity.Etape;
import fr.litopia.Integrateur.model.entity.Indication;
import fr.litopia.Integrateur.model.entity.Tache;

public class EtapeDTO {
    Long id;
    String titre;
    Integer numero;
    TypeEtapeDTO type;
    //dans indication
    String text;
    String video;
    String image;
    //Tache
    String question;
    Integer point;

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