package fr.litopia.Integrateur.repository;

import fr.litopia.Integrateur.model.entity.Defi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi,String> {
}
