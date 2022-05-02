package fr.litopia.cyberchamis.model.dto;
import fr.litopia.cyberchamis.model.entity.Etape;
import fr.litopia.cyberchamis.model.entity.Indication;
import fr.litopia.cyberchamis.model.entity.Tache;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public Integer nbIndices;

    public Etape toEntity() {
        Etape entity = null;
        if (type == TypeEtapeDTO.TacheDTO) {
            entity = new Tache(question,point);
        }
        else if(type == TypeEtapeDTO.IndicationDTO) {
            entity = new Indication(text);
        }
        return entity;
    }
}