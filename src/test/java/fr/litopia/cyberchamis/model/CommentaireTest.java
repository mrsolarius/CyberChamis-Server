package fr.litopia.cyberchamis.model;

import fr.litopia.cyberchamis.model.entity.Commentaire;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentaireTest {

    @Test
    void setText() {
        Commentaire c = new Commentaire();
        c.setText("test");
        assertEquals("test", c.getText());

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> c.setText("a".repeat(129)));
        assertEquals("Commentaire should be less than 128 characters", e.getMessage());
    }

    @Test
    void getText() {
        Commentaire c = new Commentaire();
        c.setText("test");
        assertEquals("test", c.getText());
    }
}