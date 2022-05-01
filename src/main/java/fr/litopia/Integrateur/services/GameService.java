package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.entity.*;

import java.util.List;

public interface GameService {
    Visite commencerVisite(Defi defi, Utilisateur utilisateur);

    Etape etapeSuivante(Visite visite);

    Etape etapePrecedente(Visite visite);

    /**
     * Termine la visite par un abandon si le joueur n'a pas réussi à la finir
     * @param visite
     * @param visiteStatus
     */
    Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus);

    Indice revealIndice(Visite visite);

    List<Indice> getIndices(Visite visite);

    boolean checkResponse(String response, Visite visite);
}
