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
        Etape entity=null;
        if (type == TypeEtapeDTO.TacheDTO) {
            entity = new Tache(question,point);
            entity.id = id;
            entity.numero = numero;
            entity.titre = titre;
            entity.setDescription(description);

            ((Tache)entity).question= question;
            ((Tache)entity).setPoint(this.point);
        }
        else if(type == TypeEtapeDTO.IndicationDTO) {
            entity = new Tache(question,point);
            entity.id = id;
            entity.numero = numero;
            entity.titre = titre;
            entity.setDescription(description);

            ((Indication)entity).setText(this.text);
            ((Indication)entity).setImage(this.image);
            ((Indication)entity).setVideo(this.video);
        }
        return entity;
    }
    
}
