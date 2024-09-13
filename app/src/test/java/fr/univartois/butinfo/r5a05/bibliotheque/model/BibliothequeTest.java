package fr.univartois.butinfo.r5a05.bibliotheque.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BibliothequeTest {

    private Bibliotheque bibliotheque;

    @BeforeEach
    public void setUp() {
        bibliotheque = new Bibliotheque();
    }

    @Test
    public void testAjouterLivre() {
        Livre livre = new Livre("1984", "George Orwell", 1949, "987654321", "Littérature");
        bibliotheque.ajouterLivre(livre);

        List<Livre> livres = bibliotheque.getLivres();
        assertEquals(1, livres.size());
        assertEquals("1984", livres.get(0).getTitre());
    }

    @Test
    public void testSupprimerLivre() {
        Livre livre = new Livre("1984", "George Orwell", 1949, "987654321", "Littérature");
        bibliotheque.ajouterLivre(livre);
        bibliotheque.supprimerLivre(livre);

        List<Livre> livres = bibliotheque.getLivres();
        assertEquals(0, livres.size());
    }

    @Test
    public void testLivresDisponibles() {
        Livre livre1 = new Livre("1984", "George Orwell", 1949, "987654321", "Littérature");
        Livre livre2 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", 1943, "123456789", "Littérature");

        bibliotheque.ajouterLivre(livre1);
        bibliotheque.ajouterLivre(livre2);
        livre1.setDisponible(false);

        List<Livre> disponibles = bibliotheque.getLivresDisponibles();
        assertEquals(1, disponibles.size());
        assertEquals("Le Petit Prince", disponibles.get(0).getTitre());
    }
}
