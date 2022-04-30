package fr.litopia.Integrateur.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag", nullable = false)
    public String tag;

    @ManyToMany
    @Column(name = "defis", nullable = false)
    public Set<Defi> defis;

    Tag(String tag) {
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
}
