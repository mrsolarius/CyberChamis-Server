package fr.litopia.cyberchamis.model.dto.creationModif;
import fr.litopia.cyberchamis.model.dto.ArretDTO;
import fr.litopia.cyberchamis.model.dto.IndiceDTO;

import java.util.Set;

public class DefiCreateDTO {

    public String id;
    public String titre;

    public String miniDescription;
    public String description;
    public String img;
    public Long version; // pas de modifier mais afficher pour le client
    public String duree;
    public Long auteurId;
    public Set<String> tags; //verifier si tag existe si oui on associe a id Defi
    public ArretDTO arret;
    public Set<EtapeCreateDTO> etapes;



}
