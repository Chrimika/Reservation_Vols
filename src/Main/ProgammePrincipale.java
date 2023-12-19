package Main;

import Passagers.Passager;
import Personne.Personne;
import Vols.Vol;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ProgammePrincipale {
    public static void annulerReservation(){
        Scanner sc = new Scanner(System.in);
        int choix = 0, rec = 0;
        do{
            Passager.AnnulerReservation();
            System.out.println("Voulez vous recomencer ? :\n" +
                    "1 - oui\n" +
                    "0 - non\n");
            rec =sc.nextInt();
        }while (rec == 1);
        System.out.println("Tapper 0 (Retour)");
        choix = sc.nextInt();
        switch (choix){
            case 0:
                ProgammePrincipale.mains();
                break;
            default:
                System.out.println("\nChoix non pris en compte");
        }
    }
    public static void enregistrements(){
        Scanner sc = new Scanner(System.in);
        int choix = 0, rec = 0;

        System.out.println("*enregistrements*\n\n" +
                "Voulez vous enregistrer : \n\n" +
                "1 - Nouvelle Personnes \n" +
                "2 - Un Vol\n" +
                "3 - Retour");
        choix = sc.nextInt();
        switch (choix){
            case 1:
                do{
                    System.out.println("Enregistrer Personne");
                    Personne.enregistrerPersonne();
                    System.out.println("Re-enregistrer ? :\n" +
                            "1 - oui\n" +
                            "0 - non\n");
                    rec =sc.nextInt();
                }while (rec == 1);
                ProgammePrincipale.enregistrements();
                break;
            case 2:
                do{
                    System.out.println("Enregistrer Vols");
                    Vol.enregistrerVol();
                    System.out.println("Re-enregistrer ? :\n" +
                            "1 - oui\n" +
                            "0 - non\n");
                    rec = sc.nextInt();
                }while (rec == 1);
                ProgammePrincipale.enregistrements();
                break;
            case 3:
                ProgammePrincipale.mains();
                break;
            default:
                System.out.println("choix non pris en compte");
                ProgammePrincipale.consultations();
        }
    }

    public static void consultations(){
        Scanner sc = new Scanner(System.in);
        int choix = 0, rec = 0;

        System.out.println("*consultations*\n\n" +
                "Voulez vous consulter : \n\n" +
                "1 - Personnes enregisree\n" +
                "2 - Tous les Vols disponible\n" +
                "3 - Passagers\n" +
                "4 - Retour");
        choix = sc.nextInt();
        switch (choix){
            case 1:
                do{
                    System.out.println("Affichage Personne");
                    Personne.AfficherPersonne();
                    System.out.println("Voulez vous recomencer ? :\n" +
                            "1 - oui\n" +
                            "0 - non\n");
                    rec =sc.nextInt();
                }while (rec == 1);
                ProgammePrincipale.consultations();
                break;
            case 2:
                do{
                    System.out.println("Affichage Vols");
                    Vol.afficherVols();
                    System.out.println("Voulez vous recomencer ? :\n" +
                            "1 - oui\n" +
                            "0 - non\n");
                    rec = sc.nextInt();
                }while (rec == 1);
                ProgammePrincipale.consultations();
                break;
            case 3:
                do{
                    System.out.println("Affichage Passagers");
                    Passager.AfficherPassagers();
                    System.out.println("Voulez vous recomencer ? :\n" +
                            "1 - oui\n" +
                            "0 - non\n");
                    rec =sc.nextInt();
                }while (rec == 1);
                ProgammePrincipale.consultations();
                break;
            case 4:
                ProgammePrincipale.mains();
                break;
            default:
                System.out.println("choix non pris en compte");
                ProgammePrincipale.consultations();
        }
    }

    public static void reservation(){
        Scanner sc = new Scanner(System.in);
        int choix = 0, rec = 0;
        do{
            Personne.ReserverVols();
            System.out.println("Voulez vous recomencer ? :\n" +
                    "1 - oui\n" +
                    "0 - non\n");
            rec =sc.nextInt();
        }while (rec == 1);
        System.out.println("Tapper 0 (Retour)");
        choix = sc.nextInt();
        switch (choix){
            case 0:
                ProgammePrincipale.mains();
                break;
            default:
                System.out.println("\nChoix non pris en compte");
        }
    }
    public static void mains() {
        Scanner sc = new Scanner(System.in);
        int choix = 0, rec = 0;
        System.out.println("__________Bienvenue__________\n\n" +
                "*Menue Principale*\n\n" +
                "Que Voulez vous faire ? :\n" +
                "1 - Consultations\n" +
                "2 - Reservation\n" +
                "3 - Enregistrements\n" +
                "4 - Annuler Reservation\n" +
                "5 - Quiter");
        choix = sc.nextInt();
        switch (choix){
            case 1:
                consultations();
            case 2:
                reservation();
                break;
            case 3:
                enregistrements();
                break;
            case 4:
                annulerReservation();
                break;

            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Choix non pris en compte reccomencez");
                ProgammePrincipale.mains();
        }
    }

    public static void main(String[] args) {
        ProgammePrincipale.mains();
    }
}
