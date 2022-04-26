package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.Chami;
import fr.litopia.Integrateur.repository.ChamiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class ChamiServiceImpl implements ChamiService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ChamiRepository chamiRepository;

    @Override
    public Chami findById(String id) {
        return entityManager.find(Chami.class, id);
    }

    @Override
    public Collection<Chami> findAll() {
        return chamiRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Chami chami) {
        entityManager.persist(chami);
    }

    @Override
    @Transactional
    public void update(Chami chami) {
        entityManager.merge(chami);
    }

    @Override
    @Transactional
    public void delete(Chami chami){
        entityManager.remove(chami);
    }
}
