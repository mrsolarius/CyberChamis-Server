package fr.litopia.Integrateur.model;

import fr.litopia.Integrateur.model.entity.Indice;
import fr.litopia.Integrateur.model.entity.Tache;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TacheTest {

    static Tache tache;

    @BeforeAll
    static void beforeAll() {
        tache = new Tache();
    }

    @Test
    void setQuestion() {
        tache.setQuestion("test");
        assertEquals("test", tache.getQuestion());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tache.setQuestion(""));
        assertEquals("Question cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setQuestion(null));
        assertEquals("Question cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setQuestion("a".repeat(361)));
        assertEquals("Question cannot be longer than 360 characters", e.getMessage());
    }

    @Test
    void setSecretIsSecret() {
        tache.setSecret("test");
        assert(tache.isSecret("test"));

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () ->  tache.setSecret("") );
        assertEquals("Secret cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () ->  tache.setSecret(null));
        assertEquals("Secret cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setSecret("a".repeat(51)));
        assertEquals("Secret cannot be longer than 50 characters", e.getMessage());
    }

    @Test
    void setPoint() {
        tache.setPoint(0);
        assertEquals(0, tache.getPoint());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tache.setPoint(-1));
        assertEquals("Point cannot be negative", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setPoint(null));
        assertEquals("Point cannot be null", e.getMessage());
    }

    @Test
    void addIndice() {
        Indice indice = new Indice();
        tache.addIndice(indice);
        assertEquals(1, tache.getIndices().size());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tache.addIndice(null));
        assertEquals("Indice cannot be null", e.getMessage());
    }
}