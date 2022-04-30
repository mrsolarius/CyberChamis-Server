package fr.litopia.Integrateur.model;

import fr.litopia.Integrateur.model.entity.Reponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReponseTest {

    private Reponse reponse;

    @BeforeEach
    void setUp() {
        reponse = new Reponse();
    }

    @Test
    void addIndiceUtilise() {
        assertEquals(0, reponse.getNbIndicesUtilises());

        reponse.addIndiceUtilise();
        assertEquals(1, reponse.getNbIndicesUtilises());

        reponse.addIndiceUtilise();
        assertEquals(2, reponse.getNbIndicesUtilises());
    }

    @Test
    void setReponseUtilisateur() {
        reponse.setReponseUtilisateur("Bonjour");
        assertEquals("Bonjour", reponse.getReponseUtilisateur());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> reponse.setReponseUtilisateur("Au revoir"));
        assertEquals("Reponse could not be changed", e.getMessage());
    }
}