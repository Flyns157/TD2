package fr.univartois.butinfo.r5a05.bibliotheque.services;

import org.springframework.stereotype.Service;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Emprunt;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BibliothequeService {
    private List<Livre> livres = new ArrayList<>();
    private List<Etudiant> etudiants = new ArrayList<>();
    private List<Emprunt> empruntsEnCours = new ArrayList<>();
    private List<Emprunt> emprunts;
    private static final int DUREE_MAX_EMPRUNT_JOURS = 30; // Durée maximale d'emprunt en jours

    public BibliothequeService() {
        // Initialisation avec quelques données de test
        livres.add(new Livre("Livre 1", "Auteur 1", 2020, "1234", "Littérature"));
        livres.add(new Livre("Livre 2", "Auteur 2", 2021, "5678", "Sciences"));

        etudiants.add(new Etudiant("John", "Doe", "E123", "john.doe@example.com"));
        etudiants.add(new Etudiant("Jane", "Doe", "E124", "jane.doe@example.com"));
    }

    // Gestion des livres
    public List<Livre> getAllLivres() { return livres; }

    public Optional<Livre> getLivreByIsbn(String isbn) {
        return livres.stream().filter(livre -> livre.getIsbn().equals(isbn)).findFirst();
    }

    public List<Livre> getLivresDisponibles() {
        return livres.stream().filter(Livre::isDisponible).collect(Collectors.toList());
    }

    public List<Livre> getLivresEmpruntes() {
        return livres.stream().filter(livre -> !livre.isDisponible()).collect(Collectors.toList());
    }

    public List<Livre> getLivresParCategorie(String categorie) {
        return livres.stream().filter(livre -> livre.getCategorie().equalsIgnoreCase(categorie)).collect(Collectors.toList());
    }

    public void supprimerLivre(String isbn) {
        livres.removeIf(livre -> livre.getIsbn().equals(isbn));
    }

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
    }

    // Gestion des étudiants
    public List<Etudiant> getAllEtudiants() { return etudiants; }

    public Optional<Etudiant> getEtudiantByNumero(String numeroEtudiant) {
        return etudiants.stream().filter(etudiant -> etudiant.getNumeroEtudiant().equals(numeroEtudiant)).findFirst();
    }

    public void supprimerEtudiant(String numeroEtudiant) {
        etudiants.removeIf(etudiant -> etudiant.getNumeroEtudiant().equals(numeroEtudiant));
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    // Gestion des emprunts
    public void emprunterLivre(String numeroEtudiant, String isbn) {
        Optional<Etudiant> etudiant = getEtudiantByNumero(numeroEtudiant);
        Optional<Livre> livre = getLivreByIsbn(isbn);

        if (etudiant.isPresent() && livre.isPresent() && livre.get().isDisponible()) {
            empruntsEnCours.add(new Emprunt(livre.get(), etudiant.get(), new Date()));
            livre.get().setDisponible(false);
        }
    }

    public void retournerLivre(String isbn) {
        Optional<Livre> livre = getLivreByIsbn(isbn);

        if (livre.isPresent()) {
            Emprunt emprunt = empruntsEnCours.stream()
                .filter(e -> e.getLivre().getIsbn().equals(isbn))
                .findFirst().orElse(null);

            if (emprunt != null) {
                empruntsEnCours.remove(emprunt);
                livre.get().setDisponible(true);
            }
        }
    }

    public List<Emprunt> getEmpruntsEnCours() {
        return empruntsEnCours;
    }

    // Fonction pour obtenir la liste des emprunts en retard
    public List<Emprunt> getEmpruntsHorsDelai() {
        Date maintenant = new Date(); // Date actuelle

        return emprunts.stream()
            .filter(emprunt -> estHorsDelai(emprunt, maintenant))
            .collect(Collectors.toList());
    }

    // Vérifie si l'emprunt est hors délai en utilisant java.util.Date
    private boolean estHorsDelai(Emprunt emprunt, Date maintenant) {
        // Ajoute la durée maximale d'emprunt à la date d'emprunt
        Calendar calendrier = Calendar.getInstance();
        calendrier.setTime(emprunt.getDateEmprunt());
        calendrier.add(Calendar.DAY_OF_YEAR, DUREE_MAX_EMPRUNT_JOURS); // Ajout des jours

        Date dateLimiteRetour = calendrier.getTime(); // Date limite de retour

        // Si la date actuelle est après la date limite, l'emprunt est en retard
        return maintenant.after(dateLimiteRetour);
    }
}
