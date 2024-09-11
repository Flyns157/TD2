package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.Date;

public class Emprunt {
    private Livre livre;
    private Etudiant etudiant;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Livre livre, Etudiant etudiant, Date dateEmprunt) {
        this.livre = livre;
        this.etudiant = etudiant;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = null; // le livre n'est pas encore retourné
    }

    public Livre getLivre() {
        return livre;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void enregistrerRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
        this.livre.setDisponible(true); // le livre devient disponible à nouveau
    }

    public boolean estEnRetard(Date dateActuelle) {
        // Suppose que la durée maximale d'emprunt est de 30 jours
        long difference = dateActuelle.getTime() - dateEmprunt.getTime();
        return difference > (30L * 24 * 60 * 60 * 1000);
    }
}
