package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.Chami;

import java.util.Collection;
import java.util.Optional;

public interface ChamiService {

    Chami findById(String id);

    Collection<Chami> findAll();

    void save(Chami chami);

    void update(Chami chami);

    void delete(Chami chami);

}
