package fr.litopia.cyberchamis.model.dto.creationModif;

import fr.litopia.cyberchamis.model.dto.IndiceDTO;
import fr.litopia.cyberchamis.model.dto.TypeEtapeDTO;
import fr.litopia.cyberchamis.model.entity.Etape;
import fr.litopia.cyberchamis.model.entity.Indication;
import fr.litopia.cyberchamis.model.entity.Tache;

import java.util.Set;

public class EtapeCreateDTO {
    public Long idEtape;
    public String titreEtape;
    public String descriptionEtape;
    public Integer numero;
    public TypeEtapeDTO type;

    //Indications
    public String text;
    public String image;
    public String video;

    //Tâches
    public String question;
    public String secret;
    public Integer point;
    public Set<IndiceDTO> indices;
}
