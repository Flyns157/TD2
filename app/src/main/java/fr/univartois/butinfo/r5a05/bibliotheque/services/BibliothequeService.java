package fr.univartois.butinfo.r5a05.bibliotheque.sevices;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BibliothequeService {
    private List<Livre> livres = new ArrayList<>();
    private List<Etudiant> etudiants = new ArrayList<>();
    private List<Emprunt> empruntsEnCours = new ArrayList<>();

    public BibliothequeService() {
        // Initialisation avec quelques données de test
        livres.add(new Livre("Livre 1", "Auteur 1", 2020, "1234", "Littérature", true));
        livres.add(new Livre("Livre 2", "Auteur 2", 2021, "5678", "Sciences", true));

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
            empruntsEnCours.add(new Emprunt(etudiant.get(), livre.get(), LocalDate.now()));
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

    public List<Emprunt> getEmpruntsHorsDelai() {
        return empruntsEnCours.stream()
            .filter(emprunt -> emprunt.getDateEmprunt().plusDays(30).isBefore(LocalDate.now()))
            .collect(Collectors.toList());
    }
}
