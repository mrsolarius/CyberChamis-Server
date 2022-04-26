package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.Defi;
import fr.litopia.Integrateur.repository.DefiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class DefiServiceImpl implements DefiService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private DefiRepository defiRepository;

    @Override
    public Defi findById(String id) {
        return entityManager.find(Defi.class, id);
    }

    @Override
    public Collection<Defi> findAll() {
        return defiRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Defi defi) {
        entityManager.persist(defi);
    }

    @Override
    @Transactional
    public void update(Defi defi) {
        entityManager.merge(defi);
    }

    @Override
    @Transactional
    public void delete(Defi defi){
        entityManager.remove(defi);
    }
}
