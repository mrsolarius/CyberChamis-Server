package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.model.entity.*;

import java.util.List;

public interface GameService {
    Visite commencerVisite(Defi defi, Utilisateur utilisateur);

    Visite etapeSuivante(Visite visite);

    Visite etapePrecedente(Visite visite);

    /**
     * Termine la visite par un abandon si le joueur n'a pas réussi à la finir
     * @param visite la visite à terminer
     * @param visiteStatus le statut de la visite
     */
    Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus) throws Exception;

    Indice revealIndice(Visite visite);

    List<Indice> getIndices(Visite visite);

    boolean checkResponse(String response, Visite visite) throws Exception;

    Visite reprendreVisite(Defi defiId, Utilisateur utilisateur);

    int getIndiceCost(Visite v);
}
