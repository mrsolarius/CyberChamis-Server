package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.model.entity.Chami;

import java.util.Collection;
import java.util.Optional;

public interface ChamiService {

    Chami findById(long id) throws Exception;

    Chami findByIdGoogle(String idGoogle);

    Optional<Chami> findByUsername(String username);

    Collection<Chami> findAll();

    void save(Chami chami);

    void update(Chami chami);

    void delete(Chami chami);

}
