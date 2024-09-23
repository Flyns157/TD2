package fr.univartois.butinfo.r5a05.bibliotheque.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Livre {
    @NotBlank(message = "Le titre ne peut pas être vide")
    private String titre;

    @NotBlank(message = "Le nom de l'auteur ne peut pas être vide")
    private String auteur;

    @Size(min = 4, max = 4, message = "L'année de publication doit être au format AAAA")
    private String anneePublication;

    @NotBlank(message = "La catégorie ne peut pas être vide")
    private String categorie;

    
    private String isbn;
    private boolean disponible = true;

    public Livre(String titre, String auteur, int anneePublication, String isbn, String categorie) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.isbn = isbn;
        this.categorie = categorie;
        this.disponible = true;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
