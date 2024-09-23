package fr.univartois.butinfo.r5a05.bibliotheque.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.FutureOrPresent;


public class Livre {
    @NotBlank(message = "Le titre du livre est obligatoire")
    @Size(min = 1, max = 100, message = "Le titre doit avoir entre 1 et 100 caractères")
    private String titre;

    @NotBlank(message = "Le nom de l'auteur est obligatoire")
    @Size(min = 1, max = 100, message = "Le nom de l'auteur doit avoir entre 1 et 100 caractères")
    private String auteur;

    @FutureOrPresent(message = "L'année de publication doit être inférieure ou égale à l'année actuelle")
    private int anneePublication;

    @NotBlank(message = "L'ISBN est obligatoire")
    @Size(min = 4, max = 13, message = "L'ISBN doit avoir entre 4 et 13 caractères")
    private String isbn;

    @NotBlank(message = "La catégorie du livre est obligatoire")
    private String categorie;

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
