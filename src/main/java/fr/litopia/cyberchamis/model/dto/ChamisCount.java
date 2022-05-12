package fr.litopia.cyberchamis.model.dto;

public class ChamisCount implements IChamisCount{
    public String idDefi;

    public Integer count;

    @Override
    public String getIdDefi() {
        return this.idDefi;
    }

    @Override
    public Integer getCount() {
        return this.count;
    }

    public static ChamisCount toChamisCount(IChamisCount chamisCount){
        ChamisCount c = new ChamisCount();
        c.idDefi = chamisCount.getIdDefi();
        c.count = chamisCount.getCount();
        return c;
    }
}
