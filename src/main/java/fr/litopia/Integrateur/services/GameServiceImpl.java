package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.entity.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class GameServiceImpl{
    @PersistenceContext
    private EntityManager entityManager;

    public Visite commencerVisite(Defi defi, Utilisateur utilisateur) {
        return null;

    }


    @Transactional
    public Etape visiteSuivante(Visite visite) {
        return visite.etapeSuivante();
    }
    

    @Transactional
    public Etape visitePrecedente(Visite visite) {
        return visite.etapePrecedente();
    }


    @Transactional
    public Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus){
        try {
            visite.setStatut(visiteStatus);
        }catch (Exception e){
            e.printStackTrace();
        }
        return visite;
    }


    @Transactional
    public Indice revealIndice(Visite visite) {
        Indice indice = visite.revelerIndiceCourant();
        return indice;
    }


    public boolean checkResponse(String response, Visite visite) {

        return visite.verificationReponse(response);
    }
}
