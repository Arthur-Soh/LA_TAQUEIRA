package service;

import model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private final String nom;
    private Carte carte;
    private List<Commande> fileAttente; //file attente fifo : ajout en fin de liste , service en début de liste . 
    private List<Commande> commandes;
    private int prochainNumero;

    public Restaurant(String nom, Carte carte) {
        this.nom = nom;
        this.carte = carte;
        this.fileAttente = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.prochainNumero = 1;  //le premier numéro initialisée dans la file dattente est donc le numéro 1 .
    }

    public Commande demarrerCommande(String nomClient, ModeConsommation mode) {
        return new Commande(nomClient, mode); // méthode qui crée la commande en cours 
    }

    public void valider(Commande commande) {
        if (commande.estVide()) {
            throw new IllegalStateException("Impossible de valider une commande vide.");
        }
        commande.valider(prochainNumero, LocalDateTime.now());
        fileAttente.add(commande);
        commandes.add(commande);
        prochainNumero++; //parce que file attente est une liste , alors on utilise cette méthode pour valider la commmande et ajouter .
    }

    public void abandonner(Commande commande) {
        commande.abandonner();
    }

    public Commande servirProchaine() {
        if (fileAttente.isEmpty()) {
            throw new IllegalStateException("Aucune commande en attente.");
        }
        return fileAttente.remove(0);
    }

   public Commande retrouver(int numero) {
    for (Commande commande : commandes) {
        if (commande.getNumero() == numero) {
            return commande;
        }
    }
    throw new IllegalArgumentException("Commande n°" + numero + " introuvable.");
}
   public List<Commande> getFileAttente() {
    return new ArrayList<>(fileAttente);
} // méthode pour permettre de voir la file d attente des commandes a préparer . 

    public String getNom() { return nom; }

    public Carte getCarte() { return carte; }
}