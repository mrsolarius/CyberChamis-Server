package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.repository.VisiteRepository;
import fr.litopia.cyberchamis.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VisiteRepository visiteRepository;

    @Override
    public Visite commencerVisite(Defi defi, Utilisateur utilisateur) {
        long checkNbVisiteEnCour = visiteRepository.countVisitesByStatus(defi,StatutVisite.ENCOURS,utilisateur);
        if(checkNbVisiteEnCour > 0){
            throw new IllegalStateException("You need to finish first your previous visit");
        }
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
    public Visite changeStatusVisite(Visite visite, StatutVisite visiteStatus) throws Exception {
        visite.setStatut(visiteStatus);
        visiteRepository.save(visite);
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
    public Visite continueVisite(Defi defi,Utilisateur utilisateur) {
        var rest = entityManager.createQuery("select v from Utilisateur u join u.vistes v where v.statut not in (:terminer,:abandone) and u = :utilisateur and v.defi = :defi",Visite.class)
                .setParameter("terminer",StatutVisite.FINISHED)
                .setParameter("abandone",StatutVisite.ABONDON)
                .setParameter("utilisateur",utilisateur)
                .setParameter("defi", defi);
        return rest.getSingleResult();
    }
}
