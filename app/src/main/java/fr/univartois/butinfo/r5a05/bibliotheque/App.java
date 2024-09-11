package fr.univartois.butinfo.r5a05.bibliotheque;

import java.util.List;
import java.util.Scanner;

import fr.univartois.butinfo.r5a05.bibliotheque.model.Bibliotheque;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Categorie;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Emprunt;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Etudiant;
import fr.univartois.butinfo.r5a05.bibliotheque.model.Livre;

public class App {

    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            afficherMenu();
            int choix = lireEntierDepuisConsole(); // Utiliser une méthode sécurisée pour lire un entier
            scanner.nextLine(); // Consommer la ligne vide après avoir lu un entier
    
            switch (choix) {
                case 1:
                    ajouterLivre();
                    break;
                case 2:
                    supprimerLivre();
                    break;
                case 3:
                    afficherLivres();
                    break;
                case 4:
                    afficherLivresDisponibles();
                    break;
                case 5:
                    inscrireEtudiant();
                    break;
                case 6:
                    emprunterLivre();
                    break;
                case 7:
                    retournerLivre();
                    break;
                case 8:
                    afficherEmpruntsEnCours();
                    break;
                case 9:
                    afficherCategories();
                    break;
                case 0:
                    running = false;
                    System.out.println("Merci d'avoir utilisé l'application de gestion de bibliothèque.");
                    break;
                default:
                    System.out.println("Choix non valide, veuillez réessayer.");
            }
        }
    }
    
    private static int lireEntierDepuisConsole() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Veuillez entrer un nombre entier valide.");
                scanner.nextLine(); // Nettoyer l'entrée incorrecte
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=== Menu de la Bibliothèque ===");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Supprimer un livre");
        System.out.println("3. Afficher tous les livres");
        System.out.println("4. Afficher les livres disponibles");
        System.out.println("5. Inscrire un étudiant");
        System.out.println("6. Emprunter un livre");
        System.out.println("7. Retourner un livre");
        System.out.println("8. Afficher les emprunts en cours");
        System.out.println("9. Afficher les catégories de livres");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterLivre() {
        System.out.print("Titre : ");
        String titre = scanner.nextLine();
        System.out.print("Auteur : ");
        String auteur = scanner.nextLine();
        System.out.print("Année de publication : ");
        int annee = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne vide
        System.out.print("ISBN : ");
        String isbn = scanner.nextLine();
        System.out.println("Choisissez une catégorie parmi : ");
        List<String> categories = Categorie.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + 1 + ". " + categories.get(i));
        }
        int choixCategorie = scanner.nextInt();
        scanner.nextLine(); // Consommer la ligne vide
        String categorie = categories.get(choixCategorie - 1);

        Livre livre = new Livre(titre, auteur, annee, isbn, categorie);
        bibliotheque.ajouterLivre(livre);
        System.out.println("Livre ajouté avec succès.");
    }

    private static void supprimerLivre() {
        System.out.print("Entrez le titre du livre à supprimer : ");
        String titre = scanner.nextLine();
        Livre livre = trouverLivreParTitre(titre);
        if (livre != null) {
            bibliotheque.supprimerLivre(livre);
            System.out.println("Livre supprimé avec succès.");
        } else {
            System.out.println("Livre non trouvé.");
        }
    }

    private static void afficherLivres() {
        List<Livre> livres = bibliotheque.getLivres();
        if (livres.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            System.out.println("\nListe des livres :");
            for (Livre livre : livres) {
                System.out.println(livre.getTitre() + " par " + livre.getAuteur() + " (Catégorie : " + livre.getCategorie() + ")");
            }
        }
    }

    private static void afficherLivresDisponibles() {
        List<Livre> disponibles = bibliotheque.getLivresDisponibles();
        if (disponibles.isEmpty()) {
            System.out.println("Aucun livre disponible.");
        } else {
            System.out.println("\nLivres disponibles :");
            for (Livre livre : disponibles) {
                System.out.println(livre.getTitre() + " par " + livre.getAuteur());
            }
        }
    }

    private static void inscrireEtudiant() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Numéro étudiant : ");
        String numeroEtudiant = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();

        Etudiant etudiant = new Etudiant(nom, prenom, numeroEtudiant, email);
        System.out.println("Étudiant " + nom + " " + prenom + " inscrit avec succès.");
    }

    private static void emprunterLivre() {
        System.out.print("Nom de l'étudiant : ");
        String nom = scanner.nextLine();
        // On suppose que l'étudiant est déjà inscrit. 
        // Une gestion plus poussée pourrait permettre d'enregistrer l'étudiant dans l'application.
        System.out.print("Titre du livre à emprunter : ");
        String titre = scanner.nextLine();
        Livre livre = trouverLivreParTitre(titre);
        if (livre != null && livre.isDisponible()) {
            System.out.print("Entrez le numéro de l'étudiant : ");
            String numeroEtudiant = scanner.nextLine();
            Etudiant etudiant = new Etudiant(nom, "", numeroEtudiant, "");
            bibliotheque.enregistrerEmprunt(etudiant, livre);
            System.out.println("Le livre a été emprunté avec succès.");
        } else {
            System.out.println("Le livre est soit non disponible, soit introuvable.");
        }
    }

    private static void retournerLivre() {
        System.out.print("Entrez le titre du livre à retourner : ");
        String titre = scanner.nextLine();
        Livre livre = trouverLivreParTitre(titre);
        if (livre != null && !livre.isDisponible()) {
            bibliotheque.enregistrerRetour(livre);
            System.out.println("Le livre a été retourné.");
        } else {
            System.out.println("Livre non trouvé ou déjà retourné.");
        }
    }

    private static void afficherEmpruntsEnCours() {
        List<Emprunt> emprunts = bibliotheque.getEmpruntsEnCours();
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt en cours.");
        } else {
            System.out.println("\nEmprunts en cours :");
            for (Emprunt emprunt : emprunts) {
                System.out.println(emprunt.getLivre().getTitre() + " emprunté par " + emprunt.getEtudiant().getNom());
            }
        }
    }

    private static void afficherCategories() {
        List<String> categories = Categorie.getCategories();
        System.out.println("\nCatégories disponibles :");
        for (String categorie : categories) {
            System.out.println("- " + categorie);
        }
    }

    private static Livre trouverLivreParTitre(String titre) {
        for (Livre livre : bibliotheque.getLivres()) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null;
    }
}
