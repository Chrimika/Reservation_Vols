package Vols;

import Personne.Personne;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Vol {
    private int NoVol;
    private String compagnie;
    private LocalDateTime DateDepart;
    private String destination;
    public String getdestination(){
        return destination;
    }
    public LocalDateTime getDateDepart(){
        return DateDepart;
    }
    public String getCompagnie(){
        return compagnie;
    }
    public int getNoVol(){
        return NoVol;
    }


    public Vol(String compagnie, String destination, LocalDateTime DateDepart )
    {
        this.NoVol=NoVol;
        this.compagnie=compagnie;
        this.DateDepart = DateDepart;
        this.destination=destination;
    }
    public static void enregistrerVol(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer le nom de la compagnie");
        String compagnie = sc.nextLine();

        System.out.println("Entrez la date et l'heure de depart (au format 'yyyy-MM-dd HH:mm:ss') :");
        String userInput = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateDepart = LocalDateTime.parse(userInput, formatter);
        //LocalDateTime dateDepart = LocalDateTime.parse(userInput);

        System.out.println("Entrez la distination");
        String destination = sc.nextLine();

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to db successfuly...

            Statement stat  = conn.createStatement();
            String sql = "INSERT INTO vol (compagnie, dateDep, destination) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, compagnie);
            preparedStatement.setObject(2, dateDepart, Types.TIMESTAMP);
            preparedStatement.setString(3, destination.toLowerCase());

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                System.out.println("Success");
            }
            stat.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void afficherVols(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to db successfuly...

            Statement stat  = conn.createStatement();
            String sql = "SELECT * FROM vol";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Récupération des valeurs des colonnes
                int num_vol = resultSet.getInt("num_vol");
                String compagnie = resultSet.getString("compagnie");
                LocalDateTime DateDep = resultSet.getObject("dateDep", Timestamp.class).toLocalDateTime();
                String destination = resultSet.getString("destination");
                // Affichage des valeurs dans la console
                System.out.println("Num Vol : " + num_vol);
                System.out.println("Compagnie : " + compagnie);
                System.out.println("Depart : " + DateDep);
                System.out.println("Destination : " + destination);
                // Ajoutez ici les autres colonnes si nécessaire

                System.out.println("\n--------------------");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /*System.out.println("*Enrejistrement Vols*");
        Vol.enregistrerVol();*/
        System.out.println("*Affichage Vols*");
        Vol.afficherVols();
    }
    /*
    public static void enregistrerVol(Vol[] vols){
        int i;
        Scanner sc = new Scanner(System.in);
        for(i=0;i<vols.length;i++){
            System.out.println("Entrer le numero du vol du vol"+(i+1));
            int NoVol = sc.nextInt();

            System.out.println("Entrer le nom de la compagnie du vol"+(i+1));
            String compagnie = sc.next();

            System.out.println("Entrer la jour de depart du vol"+(i+1));
            int jour = sc.nextInt();

            System.out.println("Entrer le mois de depart du vol"+(i+1));
            int mois = sc.nextInt();

            System.out.println("Entrer l'anne de depart du vol"+(i+1));
            int annee = sc.nextInt();

            System.out.println("Entrer l'heure de depart du vol"+(i+1));
            int heure = sc.nextInt();

            System.out.println("Entrez la minute de depart du vol"+(i+1));
            int minute = sc.nextInt();

            System.out.println("Entrez la distination du vol"+(i+1));
            String destination = sc.next();

            vols[i] = new Vol(NoVol, compagnie, annee, mois, jour, heure, minute, destination);
        }
    }
    public static void afficherVol(Vol[] vols){
        int i;
        for(i=0;i<vols.length;i++){
            System.out.println("Info Vol["+(i+1)+"] : " +
                    "\n Compagnie :"+vols[i].compagnie+
                    "\n Date de Depart : "+vols[i].DateDepart+
                    "\n Destination : "+vols[i].destination);
        }
    }
    public static void afficheVolsDisponible(Vol[] vol, String destination, LocalDateTime DateDepart){
        afficherVol(vol);
        for(int i=0; i==vol.length ;i++){
            if(vol[i].getdestination() == destination && vol[i].getDateDepart().isAfter(DateDepart)){
                System.out.println("Info Vol["+(i+1)+"] : " +
                        "\n Compagnie :"+vol[i].compagnie+
                        "\n Date de Depart : "+vol[i].DateDepart+
                        "\n Destination : "+vol[i].destination);
            }
        }

    }
*/

}
