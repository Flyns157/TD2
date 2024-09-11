package fr.univartois.butinfo.r5a05.bibliotheque.model;

public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String ISBN;
    private String categorie;
    private boolean disponible;

    public Livre(String titre, String auteur, int anneePublication, String ISBN, String categorie) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
        this.categorie = categorie;
        this.disponible = true;
    }

    public String getDetails() {
        return titre + ", " + auteur + ", " + anneePublication + ", " + ISBN + ", " + categorie;
    }

    public void setDetails(String titre, String auteur, int anneePublication, String ISBN, String categorie) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
        this.categorie = categorie;
    }

    public void marquerIndisponible() {
        this.disponible = false;
    }

    public void marquerDisponible() {
        this.disponible = true;
    }

    public boolean estDisponible() {
        return disponible;
    }
}
