package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.entity.*;

public interface GameService {
    Visite commencerVisite(Defi defi, Utilisateur utilisateur);

    Etape visiteSuivante(Visite visite);

    Etape visitePrecedente(Visite visite);

    /**
     * Termine la visite par un abandon si le joueur n'a pas réussi à la finir
     * @param visite
     * @param visiteStatus
     */
    Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus);

    Indice revealIndice(Visite visite);

    boolean checkResponse(String response, Visite visite);
}
