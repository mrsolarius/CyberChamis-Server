package fr.litopia.Integrateur.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArretTest {

    private static Arret a;

    @BeforeAll
    static void beforeAll() {
        a = new Arret();
    }

    @Test
    public void setCodeShouldThrowExceptionIfLengthIsMoreThan12() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> a.setCodeArret("1234567890123"));
        assertEquals("CodeArret must be 12 characters long", e.getMessage());
    }

    @Test
    public void setCodeShouldBeSetIfLengthIsLessThan12() {
        a.setCodeArret("123456789012");
        assert(a.getCodeArret().equals("123456789012"));
    }

    @Test
    public void setNomArretShouldThrowExceptionIfLengthIsMoreThan50(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> a.setNomArret("12345678910121415182021232355487484754sd564s56d4454"));
        assertEquals("NomArret must be 50 characters long", e.getMessage());
    }

    @Test
    public void setNomArretShouldBeSetIfLengthIsLessThan50(){
        a.setNomArret("12345678910121415182021232355487484754sd564s56d445");
        assert(a.getNomArret().equals("12345678910121415182021232355487484754sd564s56d445"));
    }
}
