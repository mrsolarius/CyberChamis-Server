package fr.litopia.cyberchamis.repository;
import fr.litopia.cyberchamis.model.dto.ITagCount;
import fr.litopia.cyberchamis.model.dto.TagCount;
import fr.litopia.cyberchamis.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    @Transactional
    @Query("select count(d) as count, t.tag as tag from Tag t join t.defis d group by t.tag")
    Collection<ITagCount> countTags();
}
