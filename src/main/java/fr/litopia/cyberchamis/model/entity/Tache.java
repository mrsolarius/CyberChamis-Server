package fr.litopia.cyberchamis.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.*;

@Entity
public class Tache extends Etape {

    @Column(name = "question", nullable = false, length = 360)
    public String question;

    @Column(name = "secret", nullable = false, length = 50)
    public String secret;

    @Column(name = "point", nullable = false)
    @Min(0)
    public Integer point;

    @OneToMany(orphanRemoval = true)
    public Set<Indice> indices;

    public Tache(String question, String secret, Integer point) {
        this.question = question;
        this.secret = secret;
        this.point = point;
    }

    public Tache() {
        super();
        this.indices = new HashSet<>();
    }
    public Tache(String question, Integer point){
        this.question=question;
        this.point=point;

    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (question.length() > 360) {
            throw new IllegalArgumentException("Question cannot be longer than 360 characters");
        }
        this.question = question;
    }

    public void setSecret(String secret) {
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("Secret cannot be null or empty");
        }
        if (secret.length() > 50) {
            throw new IllegalArgumentException("Secret cannot be longer than 50 characters");
        }
        this.secret = secret;
    }

    /**
     * Verifie si la tache est valide
     * @param reponse la reponse de l'utilisateur
     * @return true si la reponse est correcte, false sinon
     */
    public boolean isSecret(String reponse) {
        return this.secret.equals(reponse);
    }

    public Integer getPoint() {
        return point;
    }

    public void setIndices(Set<Indice> indices) {
        this.indices = indices;
    }

    public void setPoint(Integer point) {
        if (point == null) {
            throw new IllegalArgumentException("Point cannot be null");
        }
        if (point < 0) {
            throw new IllegalArgumentException("Point cannot be negative");
        }
        this.point = point;
    }

    public Set<Indice> getIndices() {
        return indices;
    }

    public List<Indice> getSortedIndices() {
        List<Indice> sortEtapes = new ArrayList<>(indices);
        sortEtapes.sort(Comparator.comparing(Indice::getNumIndice));
        return sortEtapes;
    }

    public void addIndice(Indice indice){
        if (!indices.contains(indice)) {
            int index = indices.size()+1;
            indice.setNumIndice(index);
            this.indices.add(indice);
        }
    }



    public void removeIndice(Indice indice){
        if (indices.contains(indice)) {
            int index = indice.getNumIndice();
            for ( Indice i : indices) {
                if (i.getNumIndice() > index) {
                    i.setNumIndice(i.getNumIndice() - 1);
                }
            }
            this.indices.remove(indice);
        }
    }

    public void moveIndice(Indice indice, int index){
        if(!indices.contains(indice)){
            throw new IllegalArgumentException("Etape not found");
        }
        if (index <= 0 || index >= indices.size()+1) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        for (Indice i : indices) {
            if (i.getNumIndice() == index) {
                i.setNumIndice(indice.getNumIndice());
                indice.setNumIndice(index);
            }
        }
    }
}
