package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Emprunt> emprunts;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    public void supprimerLivre(Livre livre) {
        livres.remove(livre);
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public List<Livre> getLivresDisponibles() {
        List<Livre> disponibles = new ArrayList<>();
        for (Livre livre : livres) {
            if (livre.isDisponible()) {
                disponibles.add(livre);
            }
        }
        return disponibles;
    }

    public void enregistrerEmprunt(Etudiant etudiant, Livre livre) {
        if (livre.isDisponible()) {
            livre.setDisponible(false);
            Emprunt emprunt = new Emprunt(livre, etudiant, new Date());
            emprunts.add(emprunt);
        } else {
            System.out.println("Le livre est déjà emprunté.");
        }
    }

    public void enregistrerRetour(Livre livre) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getLivre().equals(livre) && emprunt.getDateRetour() == null) {
                emprunt.enregistrerRetour(new Date());
                break;
            }
        }
    }

    public List<Emprunt> getEmpruntsEnCours() {
        List<Emprunt> enCours = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getDateRetour() == null) {
                enCours.add(emprunt);
            }
        }
        return enCours;
    }

    public List<Emprunt> getHistoriqueEmprunts(Etudiant etudiant) {
        List<Emprunt> historique = new ArrayList<>();
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getEtudiant().equals(etudiant)) {
                historique.add(emprunt);
            }
        }
        return historique;
    }
}
