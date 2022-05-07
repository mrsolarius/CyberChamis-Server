package fr.litopia.cyberchamis.model.dto.creationModif;

import fr.litopia.cyberchamis.model.dto.IndiceDTO;
import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Indication;
import fr.litopia.cyberchamis.model.entity.Indice;
import fr.litopia.cyberchamis.model.entity.Tache;

import java.util.Set;
import java.util.stream.Collectors;

public class TacheCreateDTO extends EtapeCreateDTO{
    public String question;
    public String secret;
    public Integer point;
    public Set<IndiceDTO> indices;

    public Tache toEntity(){
        Tache tache =new Tache(question,secret,point);
        tache.indices = this.indices.stream().map(IndiceDTO::toEntity).collect(Collectors.toSet());
        return tache;
    }
}
