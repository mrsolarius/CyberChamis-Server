package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.entity.*;
import fr.litopia.Integrateur.repository.VisiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class GameServiceImpl implements GameService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VisiteRepository visiteRepository;

    @Override
    public Visite commencerVisite(Defi defi, Utilisateur utilisateur) {
        Visite visite = new Visite(defi,utilisateur);
        visiteRepository.save(visite);
        return visite;
    }



    @Override
    @Transactional
    public Etape etapeSuivante(Visite visite) {
        Etape etape = visite.etapeSuivante();
        visiteRepository.save(visite);
        return etape;
    }


    @Override
    @Transactional
    public Etape etapePrecedente(Visite visite){
        Etape etape = visite.etapePrecedente();
        visiteRepository.save(visite);
        return etape;
    }


    @Override
    @Transactional
    public Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus){
        try {
            visite.setStatut(visiteStatus);
        }catch (Exception e){
            e.printStackTrace();
        }
        visiteRepository.save(visite);
        return visite;
    }


    @Override
    public Indice revealIndice(Visite visite) {
        return visite.revelerIndiceCourant();
    }

    @Override
    @Transactional
    public boolean checkResponse(String response, Visite visite) {
        boolean isCorrect = visite.verificationReponse(response);
        visiteRepository.save(visite);
        return isCorrect;
    }
}
