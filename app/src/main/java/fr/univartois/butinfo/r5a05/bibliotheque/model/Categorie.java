package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private String nom;
    private List<Livre> listeLivres;

    public Categorie(String nom) {
        this.nom = nom;
        this.listeLivres = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
    }

    public void supprimerLivre(Livre livre) {
        listeLivres.remove(livre);
    }

    public List<Livre> getLivres() {
        return listeLivres;
    }
}
