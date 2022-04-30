package fr.litopia.Integrateur.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndiceTest {

    @Test
    void setNumIndice() {
        Indice indice = new Indice();
        indice.setNumIndice(1);
        assertEquals(1, indice.getNumIndice());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> indice.setNumIndice(0));
        assertEquals("numIndice must be greater than 0", e.getMessage());
    }

    @Test
    void setIndice() {
        Indice indice = new Indice();
        indice.setIndice("indice");
        assertEquals("indice", indice.getIndice());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> indice.setIndice(""));
        assertEquals("indice must not be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> indice.setIndice(null));
        assertEquals("indice must not be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> indice.setIndice("a".repeat(1025)));
        assertEquals("indice must be less than 1024 characters", e.getMessage());
    }

    @Test
    void setPointsPerdus() {
        Indice indice = new Indice();
        indice.setPointsPerdus(0);
        assertEquals(0, indice.getPointsPerdus());

        indice.setPointsPerdus(1);
        assertEquals(1, indice.getPointsPerdus());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> indice.setPointsPerdus(-1));
        assertEquals("pointsPerdus must be positive", e.getMessage());
    }
}