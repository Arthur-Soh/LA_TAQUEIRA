import java.util.*;

public class ProduitSimple implements Article {
    private final String nom;
    private final String description;
    private final double prix;
    private final Categorie categorie;

    public ProduitSimple(String nom, String description, double prix, Categorie categorie)
    {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.categorie = categorie;
        this;
    }

    @Override
    public String getNom()
    {
        return nom;
    }

    @Override
    public double getPrix()
    {
        return prix;
    }

    @Override
    public List<String> getDetails()
    {
        return List.of(description);
    }

    @Override
    public Categorie getCategorie() {
        return categorie;
    }

    @Override
    public ProduitSimple copier() {
        return new ProduitSimple(nom, description, prix, categorie);
    }


}