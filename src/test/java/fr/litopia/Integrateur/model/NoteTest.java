package fr.litopia.Integrateur.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Test
    void setNote() {
        Chami chami = new Chami("Charmis");
        Note note = new Note( 1,chami);
        note.setNote(5);
        assertEquals(5,note.getNote());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> note.setNote(0));
        assertEquals("Note must be between 1 and 5", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> note.setNote(6));
        assertEquals("Note must be between 1 and 5", e.getMessage());
    }
}