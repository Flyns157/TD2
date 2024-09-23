package fr.univartois.butinfo.r5a05.bibliotheque.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> getAllLivres() {
        return bibliothequeService.getAllLivres();
    }

    // Route pour retourner les étudiants inscrits
    @GetMapping("/etudiants")
    @ResponseStatus(HttpStatus.OK)
    public List<Etudiant> getAllEtudiants() {
        return bibliothequeService.getAllEtudiants();
    }

    // Route pour retourner les livres disponibles
    @Operation(summary = "Obtenir tous les livres disponibles")
    @ApiResponse(responseCode = "200", description = "Succès")
    @GetMapping("/livres/disponibles")
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> getLivresDisponibles() {
        return bibliothequeService.getLivresDisponibles();
    }

    // Route pour retourner les livres empruntés
    @GetMapping("/livres/empruntes")
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> getLivresEmpruntes() {
        return bibliothequeService.getLivresEmpruntes();
    }

    // Route pour retourner les livres hors délai
    @GetMapping("/emprunts/hors-delai")
    @ResponseStatus(HttpStatus.OK)
    public List<Emprunt> getEmpruntsHorsDelai() {
        return bibliothequeService.getEmpruntsHorsDelai();
    }

    // Route pour retourner les livres par catégorie
    @GetMapping("/livres/categorie/{categorie}")
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> getLivresParCategorie(@PathVariable String categorie) {
        return bibliothequeService.getLivresParCategorie(categorie);
    }

    // Route pour retourner un livre par ISBN
    @GetMapping("/livres/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Livre getLivreByIsbn(@PathVariable String isbn) {
        return bibliothequeService.getLivreByIsbn(isbn)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé avec ISBN: " + isbn));
    }

    // Route pour retourner un étudiant par numéro d'étudiant
    @GetMapping("/etudiants/{numeroEtudiant}")
    @ResponseStatus(HttpStatus.OK)
    public Etudiant getEtudiantByNumero(@PathVariable String numeroEtudiant) {
        return bibliothequeService.getEtudiantByNumero(numeroEtudiant)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé avec numéro: " + numeroEtudiant));
    }

    // Route pour emprunter un livre
    @PostMapping("/emprunter")
    @ResponseStatus(HttpStatus.OK)
    public void emprunterLivre(@RequestParam String numeroEtudiant, @RequestParam String isbn) {
        if (!bibliothequeService.getEtudiantByNumero(numeroEtudiant).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé avec numéro: " + numeroEtudiant);
        }
        if (!bibliothequeService.getLivreByIsbn(isbn).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé avec ISBN: " + isbn);
        }
        bibliothequeService.emprunterLivre(numeroEtudiant, isbn);
    }

    // Route pour retourner un livre emprunté
    @PostMapping("/retourner/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void retournerLivre(@PathVariable String isbn) {
        if (!bibliothequeService.getLivreByIsbn(isbn).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé avec ISBN: " + isbn);
        }
        bibliothequeService.retournerLivre(isbn);
    }

    // Route pour supprimer un livre par son ISBN
    @DeleteMapping("/livres/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerLivre(@PathVariable String isbn) {
        if (!bibliothequeService.getLivreByIsbn(isbn).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé avec ISBN: " + isbn);
        }
        bibliothequeService.supprimerLivre(isbn);
    }

    // Route pour supprimer un étudiant par son numéro d'étudiant
    @DeleteMapping("/etudiants/{numeroEtudiant}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimerEtudiant(@PathVariable String numeroEtudiant) {
        if (!bibliothequeService.getEtudiantByNumero(numeroEtudiant).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé avec numéro: " + numeroEtudiant);
        }
        bibliothequeService.supprimerEtudiant(numeroEtudiant);
    }

    // Route pour créer un nouveau livre
    @PostMapping("/livres")
    @ResponseStatus(HttpStatus.CREATED)
    public void ajouterLivre(@Valid @RequestBody Livre livre) {
        bibliothequeService.ajouterLivre(livre);
    }

    // Route pour créer un nouvel étudiant
    @PostMapping("/etudiants")
    @ResponseStatus(HttpStatus.CREATED)
    public void ajouterEtudiant(@Valid @RequestBody Etudiant etudiant) {
        bibliothequeService.ajouterEtudiant(etudiant);
    }

    // Route pour mettre à jour un livre par son ISBN
    @PutMapping("/livres/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public void mettreAJourLivre(@PathVariable String isbn,@Valid @RequestBody Livre livreDetails) {
        Livre livreExistant = bibliothequeService.getLivreByIsbn(isbn)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre non trouvé avec ISBN: " + isbn));
        
        livreExistant.setTitre(livreDetails.getTitre());
        livreExistant.setAuteur(livreDetails.getAuteur());
        livreExistant.setAnneePublication(livreDetails.getAnneePublication());
        livreExistant.setCategorie(livreDetails.getCategorie());
        livreExistant.setDisponible(livreDetails.isDisponible());
    }

    // Route pour mettre à jour un étudiant par son numéro d'étudiant
    @PutMapping("/etudiants/{numeroEtudiant}")
    @ResponseStatus(HttpStatus.OK)
    public void mettreAJourEtudiant(@PathVariable String numeroEtudiant,@Valid @RequestBody Etudiant etudiantDetails) {
        Etudiant etudiantExistant = bibliothequeService.getEtudiantByNumero(numeroEtudiant)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé avec numéro: " + numeroEtudiant));

        etudiantExistant.setNom(etudiantDetails.getNom());
        etudiantExistant.setPrenom(etudiantDetails.getPrenom());
        etudiantExistant.setEmail(etudiantDetails.getEmail());
    }

    // Route pour afficher tous les livres publiés une année donnée
    @GetMapping("/livres/annee/{annee}")
    @ResponseStatus(HttpStatus.OK)
    public List<Livre> getLivresParAnnee(@PathVariable int annee) {
        return bibliothequeService.getAllLivres().stream()
            .filter(livre -> livre.getAnneePublication() == annee)
            .collect(Collectors.toList());
    }
}
