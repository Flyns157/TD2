package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.Date;

public class Emprunt {
    private Livre livre;
    private Etudiant etudiant;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Livre livre, Etudiant etudiant) {
        this.livre = livre;
        this.etudiant = etudiant;
        this.dateEmprunt = new Date();
        this.dateRetour = null;
    }

    public void enregistrerRetour() {
        this.dateRetour = new Date();
    }

    public Livre getLivre() {
        return livre;
    }

    public Date getDateRetour() {
        return dateRetour;
    }
}
