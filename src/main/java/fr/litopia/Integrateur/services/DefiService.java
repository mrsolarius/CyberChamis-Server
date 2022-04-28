package fr.litopia.Integrateur.services;

import fr.litopia.Integrateur.model.Defi;

import java.util.Collection;
import java.util.Optional;

public interface DefiService {

    Defi findById(String id);

    Collection<Defi> findAll();

    void save(Defi defi);

    void update(Defi defi);

    void delete(Defi defi);

}
