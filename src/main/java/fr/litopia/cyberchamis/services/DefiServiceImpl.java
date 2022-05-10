package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.repository.DefiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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

    @Override
    @Transactional
    public Collection<Defi> findByTag(String tag) {
        return defiRepository.getDefisByTag(tag);
    }
}
