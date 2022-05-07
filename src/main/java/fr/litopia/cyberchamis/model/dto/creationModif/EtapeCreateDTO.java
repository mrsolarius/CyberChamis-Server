package fr.litopia.cyberchamis.model.dto.creationModif;

import fr.litopia.cyberchamis.model.dto.TypeEtapeDTO;
import fr.litopia.cyberchamis.model.entity.Etape;
import fr.litopia.cyberchamis.model.entity.Indication;
import fr.litopia.cyberchamis.model.entity.Tache;
public class EtapeCreateDTO {
    public Long idEtape;
    public String titreEtape;
    private String descriptionEtape;
    public Integer numero;
    public TypeEtapeDTO typeEtapeDTO;
}
