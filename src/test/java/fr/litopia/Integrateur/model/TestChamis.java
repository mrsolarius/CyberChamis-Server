package fr.litopia.Integrateur.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestChamis {

    static Chami c;

    @BeforeAll
    public static void init() {
        c = new Chami("Chamillio");
    }

    @Test
    public void setUsernameShouldThrowExceptionIfUsernameLengthIsMoreThan20(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> c.setUsername("a".repeat(21)));
        assertEquals("Login must be 20 characters long", e.getMessage());
    }

    @Test
    public void setUsernameShouldSetUsername(){
        StringBuilder username = new StringBuilder();
        username.append("a".repeat(20));
        c.setUsername(username.toString());
        assertEquals(username.toString(), c.getUsername());
    }

    @Test
    public void setAgeShouldThrowExceptionIfAgeIsLessThan13(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> c.setAge(12));
        assertEquals("Age must be at least 13", e.getMessage());
    }

    @Test
    public void setAgeShouldSetAge(){
        c.setAge(14);
        assertEquals(14, c.getAge());
    }

    @Test
    public void setBioShouldSetBio(){
        StringBuilder bio = new StringBuilder();
        bio.append("a".repeat(200));
        c.setBio(bio.toString());
        assertEquals(bio.toString(), c.getBio());
    }

    @Test
    public void setBioShouldThrowExceptionIfBioIsMoreThan255(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> c.setBio("a".repeat(256)));
        assertEquals("Bio must be 255 characters long", e.getMessage());
    }
}
