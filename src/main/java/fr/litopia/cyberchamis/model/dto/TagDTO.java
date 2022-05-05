package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Defi;
import fr.litopia.cyberchamis.model.entity.Tag;
import fr.litopia.cyberchamis.model.dto.DefiDTO;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagDTO {
    public String tag;
    public List<String> defiIds;

  /*  public Tag toEntity() {
        Tag tagE = new Tag();
        tagE.tag = this.tag;
        List<String> l1 = new ArrayList<String>();
        for (String d : this.defiIds) {
            l1.add(d.getId());
        }
        tagE.defis=l1;
        return tagE;
    }
*/
}

