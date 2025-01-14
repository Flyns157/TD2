package fr.univartois.butinfo.r5a05.bibliotheque.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Etudiant {
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @Email(message = "Adresse e-mail invalide")
    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

    private String numeroEtudiant;

    public Etudiant(String nom, String prenom, String numeroEtudiant, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
