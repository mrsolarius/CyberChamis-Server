package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.entity.Chami;

import java.util.Collection;

public interface ChamiService {

    Chami findById(long id);

    Collection<Chami> findAll();

    void save(Chami chami);

    void update(Chami chami);

    void delete(Chami chami);

}
