package fr.univartois.butinfo.r5a05.bibliotheque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LivreTest {

    @Test
    public void testLivreCreation() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "123456789", "Littérature");

        assertEquals("Le Petit Prince", livre.getTitre());
        assertEquals("Antoine de Saint-Exupéry", livre.getAuteur());
        assertEquals(1943, livre.getAnneePublication());
        assertEquals("123456789", livre.getIsbn());
        assertEquals("Littérature", livre.getCategorie());
    }

    @Test
    public void testLivreDisponible() {
        Livre livre = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "123456789", "Littérature");

        assertTrue(livre.isDisponible());

        livre.setDisponible(false);
        assertFalse(livre.isDisponible());
    }
}
