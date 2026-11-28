package ui;

import java.util.Scanner;
import service.Restaurant;

public class ConsoleApp {

    private Restaurant restaurant;
    private Scanner clavier = new Scanner(System.in);

    public ConsoleApp(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void menuPrincipal() {
        String choix = "";
        // On répète le menu tant que l'utilisateur ne tape pas 0
        while (!choix.equals("0")) {
            System.out.println("1 = Client, 2 = Restaurant, 0 = Quitter");
            choix = clavier.nextLine();

            if (choix.equals("1")) {
                espaceClient();
            }
            if (choix.equals("2")) {
                espaceRestaurant();
            }
        }
    }

    public void espaceClient() {
        System.out.println("A faire");
    }

    public void espaceRestaurant() {
        System.out.println("A faire");
    }
}