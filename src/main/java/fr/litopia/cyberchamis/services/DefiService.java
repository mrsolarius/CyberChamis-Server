package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.model.entity.Defi;

import java.util.Collection;

public interface DefiService {

    Defi findById(String id);

    Collection<Defi> findAll();

    void save(Defi defi);

    void update(Defi defi);

    void delete(Defi defi);

}
