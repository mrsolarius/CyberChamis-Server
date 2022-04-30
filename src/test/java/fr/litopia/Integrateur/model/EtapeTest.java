package fr.litopia.Integrateur.model;

import fr.litopia.Integrateur.model.entity.Tache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtapeTest {

    @Test
    void setNumero() {
        //Use tache class cause we need to instantiate a subclass of Etape to test his method
        Tache tache = new Tache();
        tache.setNumero(0);
        assertEquals(0, tache.getNumero());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tache.setNumero(null));
        assertEquals("Numero cannot be null", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setNumero(-1));
        assertEquals("Numero cannot be negative", e.getMessage());
    }

    @Test
    void setTitre() {
        Tache tache = new Tache();
        tache.setTitre("titre");
        assertEquals("titre", tache.getTitre());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tache.setTitre(null));
        assertEquals("Titre cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setTitre(""));
        assertEquals("Titre cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> tache.setTitre("a".repeat(33)));
        assertEquals("Titre cannot be longer than 32 characters", e.getMessage());
    }
}