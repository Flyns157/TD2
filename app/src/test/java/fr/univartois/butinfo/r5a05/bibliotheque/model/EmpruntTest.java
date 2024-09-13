package fr.univartois.butinfo.r5a05.bibliotheque.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpruntTest {

    private Bibliotheque bibliotheque;
    private Etudiant etudiant;
    private Livre livre;

    @BeforeEach
    public void setUp() {
        bibliotheque = new Bibliotheque();
        etudiant = new Etudiant("Dupont", "Jean", "12345", "jean.dupont@example.com");
        livre = new Livre("1984", "George Orwell", 1949, "987654321", "Littérature");
        bibliotheque.ajouterLivre(livre);
    }

    @Test
    public void testEmpruntLivre() {
        bibliotheque.enregistrerEmprunt(etudiant, livre);
        assertFalse(livre.isDisponible());
        assertEquals(1, bibliotheque.getEmpruntsEnCours().size());
    }

    @Test
    public void testRetourLivre() {
        bibliotheque.enregistrerEmprunt(etudiant, livre);
        bibliotheque.enregistrerRetour(livre);

        assertTrue(livre.isDisponible());
        assertEquals(0, bibliotheque.getEmpruntsEnCours().size());
    }
}
