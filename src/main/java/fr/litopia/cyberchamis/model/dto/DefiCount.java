package fr.litopia.cyberchamis.model.dto;

public class DefiCount implements IDefiCount{
    public Long auteurId;

    public Long count;

    @Override
    public Long getAuteurId() {
        return auteurId;
    }

    @Override
    public Long getCount() {
        return this.count;
    }
    public static DefiCount toDefiCount(IDefiCount defiCou){
        DefiCount c = new DefiCount();
        c.auteurId = defiCou.getAuteurId();
        c.count = defiCou.getCount();
        return c;
    }
}
