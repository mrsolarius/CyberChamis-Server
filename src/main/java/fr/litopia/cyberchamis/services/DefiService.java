package fr.litopia.cyberchamis.services;

import fr.litopia.cyberchamis.model.entity.Defi;

import java.util.Collection;
import java.util.Set;

public interface DefiService {

    Defi findById(String id);

    Collection<Defi> findAll();

    void save(Defi defi);

    void update(Defi defi);

    void delete(Defi defi);

    Collection<Defi> findByTag(String tag);

}
