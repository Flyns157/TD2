package fr.univartois.butinfo.r5a05.bibliotheque.model;

import java.util.Arrays;
import java.util.List;
public class Categorie {
    private static final List<String> categories = Arrays.asList(
        "Littérature", "Sciences", "Histoire", "Informatique", "Arts", "Philosophie", "Économie", "Droit"
    );

    public static List<String> getCategories() {
        return categories;
    }
}
