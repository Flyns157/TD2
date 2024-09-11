package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private String nom;
    private String prenom;
    private String numeroEtudiant;
    private String email;
    private List<Emprunt> emprunts;

    public Etudiant(String nom, String prenom, String numeroEtudiant, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroEtudiant = numeroEtudiant;
        this.email = email;
        this.emprunts = new ArrayList<>();
    }

    public void inscrire(String nom, String prenom, String numeroEtudiant, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroEtudiant = numeroEtudiant;
        this.email = email;
    }

    public void emprunterLivre(Livre livre) {
        Emprunt emprunt = new Emprunt(livre, this);
        emprunts.add(emprunt);
        livre.marquerIndisponible();
    }

    public void retournerLivre(Livre livre) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getLivre().equals(livre) && emprunt.getDateRetour() == null) {
                emprunt.enregistrerRetour();
                livre.marquerDisponible();
                break;
            }
        }
    }

    public List<Emprunt> getHistoriqueEmprunts() {
        return emprunts;
    }
}
