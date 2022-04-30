package fr.litopia.Integrateur.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DefiTest {

    private Defi defi;

    @BeforeEach
    void BeforeEach() {
        defi = new Defi();
    }

    @Test
    void setTitre() {
        defi.setTitre("titre");
        assertEquals("titre", defi.getTitre());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> defi.setTitre(""));
        assertEquals("Titre cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> defi.setTitre(null));
        assertEquals("Titre cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> defi.setTitre("a".repeat(46)));
        assertEquals("Titre cannot be longer than 45 characters", e.getMessage());
    }

    @Test
    void setDescription() {
        defi.setDescription("description");
        assertEquals("description", defi.getDescription());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> defi.setDescription(""));
        assertEquals("Description cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> defi.setDescription(null));
        assertEquals("Description cannot be null or empty", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> defi.setDescription("a".repeat(129)));
        assertEquals("Description cannot be longer than 128 characters", e.getMessage());
    }

    @Test
    void setAuteur() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> defi.setAuteur(null));
        assertEquals("Auteur cannot be null", e.getMessage());

        Chami chami = new Chami("Chamois");
        defi.setAuteur(chami);
        assertEquals(chami, defi.getAuteur());

        Chami newAuthorQuestionMark = new Chami("?who?");
        e = assertThrows(IllegalArgumentException.class, () -> defi.setAuteur(newAuthorQuestionMark));
        assertEquals("Auteur cannot be changed", e.getMessage());
    }

    @Test
    void addTag() {
        Tag tag = new Tag("montagne");
        defi.addTag(tag);
        assertTrue(defi.getTags().contains(tag));
        assertTrue(tag.getDefis().contains(defi));
    }

    @Test
    void removeTag() {
        Tag tag = new Tag("dammeBlance");
        defi.addTag(tag);
        assertTrue(defi.getTags().contains(tag));
        assertTrue(tag.getDefis().contains(defi));

        defi.removeTag(tag);
        assertFalse(defi.getTags().contains(tag));
        assertFalse(tag.getDefis().contains(defi));
    }

    @Test
    void getMoyenne() {
        Chami chami = new Chami("Chamois");
        Chami chami2 = new Chami("Bouquetin");
        Chami chami3 = new Chami("Cerf");
        Chami chami4 = new Chami("Chevreille");

        Note note = new Note(4, chami);
        Note note2 = new Note(3, chami2);
        Note note3 = new Note(5, chami3);
        Note note4 = new Note(5, chami4);

        defi.ajouterOuModifierNote(note);
        defi.ajouterOuModifierNote(note2);
        defi.ajouterOuModifierNote(note3);
        defi.ajouterOuModifierNote(note4);

        assertEquals(4.25, defi.getMoyenne());
    }

    @Test
    void addEtape() {
        Tache tache = new Tache();
        defi.addEtape(tache);
        assertTrue(defi.getEtapes().contains(tache));
        assertEquals(0, (int) tache.getNumero());

        Tache tache2 = new Tache();
        defi.addEtape(tache2);
        assertTrue(defi.getEtapes().contains(tache2));
        assertEquals(1, (int) tache2.getNumero());
    }

    @Test
    void removeEtape() {
        Tache tache1 = new Tache();
        Tache tache2 = new Tache();
        Tache tache3 = new Tache();
        Tache tache4 = new Tache();
        Tache tache5 = new Tache();

        defi.addEtape(tache1);
        defi.addEtape(tache2);
        defi.addEtape(tache3);
        defi.addEtape(tache4);
        defi.addEtape(tache5);

        assertTrue(defi.getEtapes().contains(tache1));
        assertTrue(defi.getEtapes().contains(tache2));
        assertTrue(defi.getEtapes().contains(tache3));
        assertTrue(defi.getEtapes().contains(tache4));
        assertTrue(defi.getEtapes().contains(tache5));
        assertEquals(0, (int) tache1.getNumero());
        assertEquals(1, (int) tache2.getNumero());
        assertEquals(2, (int) tache3.getNumero());
        assertEquals(3, (int) tache4.getNumero());
        assertEquals(4, (int) tache5.getNumero());

        defi.removeEtape(tache3);
        assertFalse(defi.getEtapes().contains(tache3));
        assertEquals(0, (int) tache1.getNumero());
        assertEquals(1, (int) tache2.getNumero());
        assertEquals(2, (int) tache4.getNumero());
        assertEquals(3, (int) tache5.getNumero());
    }

    @Test
    void moveEtape() {
        Tache tache1 = new Tache();
        Tache tache2 = new Tache();
        Tache tache3 = new Tache();
        Tache tache4 = new Tache();

        defi.addEtape(tache1);
        defi.addEtape(tache2);
        defi.addEtape(tache3);
        defi.addEtape(tache4);

        assertEquals(0, (int) tache1.getNumero());
        assertEquals(1, (int) tache2.getNumero());
        assertEquals(2, (int) tache3.getNumero());
        assertEquals(3, (int) tache4.getNumero());

        defi.moveEtape(tache3, 1);
        assertEquals(0, (int) tache1.getNumero());
        assertEquals(1, (int) tache3.getNumero());
        assertEquals(2, (int) tache2.getNumero());
        assertEquals(3, (int) tache4.getNumero());
    }

    @Test
    void getSortEtapes() {
        Tache tache1 = new Tache();
        Tache tache2 = new Tache();
        Tache tache3 = new Tache();
        Tache tache4 = new Tache();


        defi.addEtape(tache2);
        defi.addEtape(tache4);
        defi.addEtape(tache1);
        defi.addEtape(tache3);

        assertEquals(0, (int) tache2.getNumero());
        assertEquals(1, (int) tache4.getNumero());
        assertEquals(2, (int) tache1.getNumero());
        assertEquals(3, (int) tache3.getNumero());

        ArrayList<Etape> liste = new ArrayList<>();
        liste.add(0,tache2);
        liste.add(1,tache4);
        liste.add(2,tache1);
        liste.add(3,tache3);

        assertEquals(liste, defi.getSortEtapes());
    }
}