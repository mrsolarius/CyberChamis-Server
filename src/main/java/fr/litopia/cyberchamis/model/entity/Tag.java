package fr.litopia.cyberchamis.model.entity;

import fr.litopia.cyberchamis.model.dto.TagDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Tag {
    @Id
    @Column(name = "tag", nullable = false)
    private String tag;

    @ManyToMany
    @Column(name = "defis", nullable = false)
    private Set<Defi> defis;

    public Tag(String tag) {
        this();
        this.tag = tag;
    }

    public Tag() {
        defis = new HashSet<>();
    }

    public String getTag() {
        return tag;
    }

    public Set<Defi> getDefis() {
        return defis;
    }
    public void addDefi(Defi defi) {
        defis.add(defi);
        if (!defi.getTags().contains(this)) {
            defi.addTag(this);
        }
    }

    public void removeDefi(Defi defi) {
        defis.remove(defi);
        if (defi.getTags().contains(this)) {
            defi.removeTag(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tag.getTag().equals(this.tag);
    }

    public TagDTO toDTO(){
        TagDTO tagDTO =  new TagDTO();
        tagDTO.tag = this.tag;
        tagDTO.defiIds = this.defis.stream().map(Defi::getId).collect(Collectors.toList());
        return tagDTO;
    }
}
