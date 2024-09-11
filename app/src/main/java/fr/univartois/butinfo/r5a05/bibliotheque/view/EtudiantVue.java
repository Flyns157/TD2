package fr.univartois.butinfo.r5a05.bibliotheque.view;

import java.util.List;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Emprunt;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;

public class EtudiantVue {
    public void afficherDetailsEtudiant(Etudiant etudiant) {
        System.out.println(etudiant.getNom() + " " + etudiant.getPrenom() + ", " + etudiant.getNumeroEtudiant() + ", " + etudiant.getEmail());
    }

    public void afficherHistoriqueEmprunts(List<Emprunt> emprunts) {
        for (Emprunt emprunt : emprunts) {
            System.out.println(emprunt.getLivre().getDetails() + " emprunté le " + emprunt.getDateEmprunt() + (emprunt.getDateRetour() != null ? ", retourné le " + emprunt.getDateRetour() : ""));
        }
    }

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
