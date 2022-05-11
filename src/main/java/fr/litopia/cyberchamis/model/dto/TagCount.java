package fr.litopia.cyberchamis.model.dto;

import fr.litopia.cyberchamis.model.entity.Tag;

public class TagCount implements ITagCount{
    public String tag;
    public Integer count;

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Integer getCount() {
        return count;
    }

    public static TagCount toTagCount(ITagCount tagCount){
        TagCount a = new TagCount();
        a.tag = tagCount.getTag();
        a.count = tagCount.getCount();
        return a;
    }
}
