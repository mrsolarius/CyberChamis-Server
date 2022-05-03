package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.repository.VisiteRepository;
import fr.litopia.cyberchamis.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VisiteRepository visiteRepository;

    @Override
    public Visite commencerVisite(Defi defi, Utilisateur utilisateur) {
        long checkNbVisiteEnCour = visiteRepository.countVisitesByStatus(defi,Arrays.asList(StatutVisite.ENCOURS,StatutVisite.PAUSE),utilisateur);
        if(checkNbVisiteEnCour > 0){
            throw new IllegalStateException("You need to finish first your previous visit");
        }
        Visite visite = new Visite(defi,utilisateur);
        visiteRepository.save(visite);
        return visite;
    }



    @Override
    @Transactional
    public Visite etapeSuivante(Visite visite) {
        Etape etape = visite.etapeSuivante();
        visiteRepository.save(visite);
        return visite;
    }


    @Override
    @Transactional
    public Visite etapePrecedente(Visite visite){
        Etape etape = visite.etapePrecedente();
        visiteRepository.save(visite);
        return visite;
    }


    @Override
    @Transactional
    public Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus) throws Exception {
        try {
            visite.setStatut(visiteStatus);
            visiteRepository.save(visite);
        } catch (Exception e) {
            throw new IllegalStateException("Visite status can't be changed");
        }
        return visite;
    }


    @Override
    public Indice revealIndice(Visite visite) {
        Indice i = visite.revelerIndiceCourant();
        visiteRepository.save(visite);
        return i;
    }

    @Override
    public List<Indice> getIndices(Visite visite) {
        return visite.getIndices();
    }

    @Override
    @Transactional
    public boolean checkResponse(String response, Visite visite) throws Exception {
        boolean isCorrect = visite.verificationReponse(response);
        visiteRepository.save(visite);
        return isCorrect;
    }

    @Override
    @Transactional
    public Visite reprendreVisite(Defi defi, Utilisateur utilisateur) {
        var v = visiteRepository.findUserVisiteThatHaveStatus(defi, Arrays.asList(StatutVisite.ENCOURS,StatutVisite.PAUSE),utilisateur);
        if(v == null){
            throw new IllegalArgumentException("not found");
        }
        if(v.getStatus() == StatutVisite.PAUSE){
            try {
                v.setStatut(StatutVisite.ENCOURS);
                visiteRepository.save(v);
            } catch (Exception e) {
                throw new IllegalStateException("You need to finish first your previous visit");
            }
        }
        return v;
    }
}
