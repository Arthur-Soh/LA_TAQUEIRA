package service;

import model.*;

public class TestRestaurant {
    public static void main(String[] args) {
        Restaurant resto = new Restaurant("Burger ECE", new Carte());

        // Test 1 : commande vide refusée
        Commande vide = resto.demarrerCommande("Vide", ModeConsommation.SUR_PLACE);
        try {
            resto.valider(vide);
            System.out.println("ÉCHEC : la commande vide a été validée");
        } catch (IllegalStateException e) {
            System.out.println("OK test 1 : " + e.getMessage());
        }

        // Test 2 : numéros 1, 2, 3 avec une commande abandonnée au milieu
        Commande a = resto.demarrerCommande("Lucas", ModeConsommation.SUR_PLACE);
        a.ajouter(null, 1);
        resto.valider(a);

        Commande abandon = resto.demarrerCommande("Tom", ModeConsommation.A_EMPORTER);
        abandon.ajouter(null, 1);
        resto.abandonner(abandon);

        Commande b = resto.demarrerCommande("Sarah", ModeConsommation.A_EMPORTER);
        b.ajouter(null, 2);
        resto.valider(b);

        Commande c = resto.demarrerCommande("Mehdi", ModeConsommation.SUR_PLACE);
        c.ajouter(null, 1);
        resto.valider(c);

        System.out.println("Numéros attendus 1 2 3 : "
                + a.getNumero() + " " + b.getNumero() + " " + c.getNumero());

        // Test 3 : service dans l'ordre (FIFO)
        System.out.println("Servie : " + resto.servirProchaine().getNomClient());
        System.out.println("Servie : " + resto.servirProchaine().getNomClient());
        System.out.println("Servie : " + resto.servirProchaine().getNomClient());

        // Test 4 : file vide
        try {
            resto.servirProchaine();
            System.out.println("ÉCHEC : service avec file vide");
        } catch (IllegalStateException e) {
            System.out.println("OK test 4 : " + e.getMessage());
        }

        // Test 5 : retrouver une commande servie, puis un numéro inconnu
        System.out.println("Commande n°2 retrouvée : " + resto.retrouver(2).getNomClient());
        try {
            resto.retrouver(99);
            System.out.println("ÉCHEC : numéro 99 trouvé");
        } catch (IllegalArgumentException e) {
            System.out.println("OK test 5 : " + e.getMessage());
        }
    }
}