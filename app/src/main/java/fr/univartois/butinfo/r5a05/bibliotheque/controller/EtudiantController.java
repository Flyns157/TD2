package fr.univartois.butinfo.r5a05.bibliotheque.controller;

import java.util.List;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Emprunt;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;

public class EtudiantController {
    private List<Etudiant> etudiants;

    public void inscrireEtudiant(String nom, String prenom, String numeroEtudiant, String email) {
        Etudiant etudiant = new Etudiant(nom, prenom, numeroEtudiant, email);
        etudiants.add(etudiant);
    }

    public void emprunterLivre(String numeroEtudiant, String ISBN) {
        Etudiant etudiant = etudiants.stream().filter(e -> e.getNumeroEtudiant().equals(numeroEtudiant)).findFirst().orElse(null);
        Livre livre = livres.stream().filter(l -> l.getISBN().equals(ISBN)).findFirst().orElse(null);
        if (etudiant != null && livre != null && livre.estDisponible()) {
            etudiant.emprunterLivre(livre);
        }
    }

    public void retournerLivre(String numeroEtudiant, String ISBN) {
        Etudiant etudiant = etudiants.stream().filter(e -> e.getNumeroEtudiant().equals(numeroEtudiant)).findFirst().orElse(null);
        Livre livre = livres.stream().filter(l -> l.getISBN().equals(ISBN)).findFirst().orElse(null);
        if (etudiant != null && livre != null) {
            etudiant.retournerLivre(livre);
        }
    }

    public List<Emprunt> afficherHistoriqueEmprunts(String numeroEtudiant) {
        Etudiant etudiant = etudiants.stream().filter(e -> e.getNumeroEtudiant().equals(numeroEtudiant)).findFirst().orElse(null);
        return etudiant != null ? etudiant.getHistoriqueEmprunts() : null;
    }
}
