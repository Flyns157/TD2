package fr.univartois.butinfo.r5a05.bibliotheque.controller;

import org.springframework.web.bind.annotation.*;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Emprunt;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;
import fr.univartois.butinfo.r5a05.bibliotheque.services.BibliothequeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bibliotheque")
public class BibliothequeController {

    private final BibliothequeService bibliothequeService;

    public BibliothequeController(BibliothequeService bibliothequeService) {
        this.bibliothequeService = bibliothequeService;
    }

    // Route pour retourner l'ensemble des livres
    @GetMapping("/livres")
    public List<Livre> getAllLivres() {
        return bibliothequeService.getAllLivres();
    }

    // Route pour retourner les étudiants inscrits
    @GetMapping("/etudiants")
    public List<Etudiant> getAllEtudiants() {
        return bibliothequeService.getAllEtudiants();
    }

    // Route pour retourner les livres disponibles
    @GetMapping("/livres/disponibles")
    public List<Livre> getLivresDisponibles() {
        return bibliothequeService.getLivresDisponibles();
    }

    // Route pour retourner les livres empruntés
    @GetMapping("/livres/empruntes")
    public List<Livre> getLivresEmpruntes() {
        return bibliothequeService.getLivresEmpruntes();
    }

    // Route pour retourner les livres hors délai
    @GetMapping("/emprunts/hors-delai")
    public List<Emprunt> getEmpruntsHorsDelai() {
        return bibliothequeService.getEmpruntsHorsDelai();
    }

    // Route pour retourner les livres par catégorie
    @GetMapping("/livres/categorie/{categorie}")
    public List<Livre> getLivresParCategorie(@PathVariable String categorie) {
        return bibliothequeService.getLivresParCategorie(categorie);
    }

    // Route pour retourner un livre par ISBN
    @GetMapping("/livres/{isbn}")
    public Livre getLivreByIsbn(@PathVariable String isbn) {
        return bibliothequeService.getLivreByIsbn(isbn).orElse(null);
    }

    // Route pour retourner un étudiant par numéro d'étudiant
    @GetMapping("/etudiants/{numeroEtudiant}")
    public Etudiant getEtudiantByNumero(@PathVariable String numeroEtudiant) {
        return bibliothequeService.getEtudiantByNumero(numeroEtudiant).orElse(null);
    }

    // Route pour emprunter un livre
    @PostMapping("/emprunter")
    public void emprunterLivre(@RequestParam String numeroEtudiant, @RequestParam String isbn) {
        bibliothequeService.emprunterLivre(numeroEtudiant, isbn);
    }

    // Route pour retourner un livre emprunté
    @PostMapping("/retourner/{isbn}")
    public void retournerLivre(@PathVariable String isbn) {
        bibliothequeService.retournerLivre(isbn);
    }

    // Route pour supprimer un livre par son ISBN
    @DeleteMapping("/livres/{isbn}")
    public void supprimerLivre(@PathVariable String isbn) {
        bibliothequeService.supprimerLivre(isbn);
    }

    // Route pour supprimer un étudiant par son numéro d'étudiant
    @DeleteMapping("/etudiants/{numeroEtudiant}")
    public void supprimerEtudiant(@PathVariable String numeroEtudiant) {
        bibliothequeService.supprimerEtudiant(numeroEtudiant);
    }

    // Route pour créer un nouveau livre
    @PostMapping("/livres")
    public void ajouterLivre(@RequestBody Livre livre) {
        bibliothequeService.ajouterLivre(livre);
    }

    // Route pour créer un nouvel étudiant
    @PostMapping("/etudiants")
    public void ajouterEtudiant(@RequestBody Etudiant etudiant) {
        bibliothequeService.ajouterEtudiant(etudiant);
    }

    // Route pour mettre à jour un livre par son ISBN
    @PutMapping("/livres/{isbn}")
    public void mettreAJourLivre(@PathVariable String isbn, @RequestBody Livre livreDetails) {
        Optional<Livre> livre = bibliothequeService.getLivreByIsbn(isbn);

        if (livre.isPresent()) {
            Livre livreExistant = livre.get();
            livreExistant.setTitre(livreDetails.getTitre());
            livreExistant.setAuteur(livreDetails.getAuteur());
            livreExistant.setAnneePublication(livreDetails.getAnneePublication());
            livreExistant.setCategorie(livreDetails.getCategorie());
            livreExistant.setDisponible(livreDetails.isDisponible());
            // Pas besoin d'ajouter le livre de nouveau, il est déjà dans la liste
        }
    }

    // Route pour mettre à jour un étudiant par son numéro d'étudiant
    @PutMapping("/etudiants/{numeroEtudiant}")
    public void mettreAJourEtudiant(@PathVariable String numeroEtudiant, @RequestBody Etudiant etudiantDetails) {
        Optional<Etudiant> etudiant = bibliothequeService.getEtudiantByNumero(numeroEtudiant);

        if (etudiant.isPresent()) {
            Etudiant etudiantExistant = etudiant.get();
            etudiantExistant.setNom(etudiantDetails.getNom());
            etudiantExistant.setPrenom(etudiantDetails.getPrenom());
            etudiantExistant.setEmail(etudiantDetails.getEmail());
            // Pas besoin d'ajouter l'étudiant de nouveau, il est déjà dans la liste
        }
    }

    // Route pour afficher tous les livres publiés une année donnée
    @GetMapping("/livres/annee/{annee}")
    public List<Livre> getLivresParAnnee(@PathVariable int annee) {
        return bibliothequeService.getAllLivres().stream()
            .filter(livre -> livre.getAnneePublication() == annee)
            .collect(Collectors.toList());
    }
}
