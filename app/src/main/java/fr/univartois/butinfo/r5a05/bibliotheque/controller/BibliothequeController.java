package fr.univartois.butinfo.r5a05.bibliotheque.controller;

import java.util.List;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;

public class BibliothequeController {
    private List<Livre> livres;
    private List<Etudiant> etudiants;

    public void ajouterLivre(String titre, String auteur, int anneePublication, String ISBN, String categorie) {
        Livre livre = new Livre(titre, auteur, anneePublication, ISBN, categorie);
        livres.add(livre);
    }

    public void modifierLivre(Livre livre, String titre, String auteur, int anneePublication, String ISBN, String categorie) {
        livre.setDetails(titre, auteur, anneePublication, ISBN, categorie);
    }

    public void supprimerLivre(Livre livre) {
        livres.remove(livre);
    }

    public List<Livre> listerLivres() {
        return livres;
    }

    public List<Livre> listerLivresDisponibles() {
        return livres.stream().filter(Livre::estDisponible).toList();
    }

    public List<Livre> listerLivresEmpruntes() {
        return livres.stream().filter(livre -> !livre.estDisponible()).toList();
    }

    public List<Livre> listerRetards() {
        // TODO : Implémentation pour lister les retards
        return null;
    }
}
