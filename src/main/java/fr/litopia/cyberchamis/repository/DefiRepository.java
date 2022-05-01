package fr.litopia.cyberchamis.repository;

import fr.litopia.cyberchamis.model.entity.Defi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefiRepository extends JpaRepository<Defi,String> {
}
