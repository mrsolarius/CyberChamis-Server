package fr.litopia.Integrateur.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Indice {
    @Id
    @Column(name = "idIndice", nullable = false)
    public long id;

    @Column(name = "numIndice", nullable = false)
    @Min(1)
    public int numIndice;

    @Column(name = "indice", nullable = false, length = 1024)
    public String indice;

    @Column(name = "pointsPerdus", nullable = false)
    @Min(0)
    public Integer pointsPerdus;

    public Indice() {
    }

    public int getNumIndice() {
        return numIndice;
    }

    protected void setNumIndice(int numIndice) {
        if(numIndice < 1) {
            throw new IllegalArgumentException("numIndice must be greater than 0");
        }
        this.numIndice = numIndice;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        if(indice == null || indice.isEmpty()) {
            throw new IllegalArgumentException("indice must not be null or empty");
        }
        if (indice.length() > 1024) {
            throw new IllegalArgumentException("indice must be less than 1024 characters");
        }
        this.indice = indice;
    }

    public Integer getPointsPerdus() {
        return pointsPerdus;
    }

    public void setPointsPerdus(Integer pointsPerdus) {
        if(pointsPerdus < 0) {
            throw new IllegalArgumentException("pointsPerdus must be positive");
        }
        this.pointsPerdus = pointsPerdus;
    }
}
