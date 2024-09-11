package fr.univartois.butinfo.r5a05.bibliotheque.view;

import java.util.List;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;

public class BibliothequeVue {
    public void afficherListeLivres(List<Livre> livres) {
        for (Livre livre : livres) {
            System.out.println(livre.getDetails());
        }
    }

    public void afficherDetailsLivre(Livre livre) {
        System.out.println(livre.getDetails());
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
