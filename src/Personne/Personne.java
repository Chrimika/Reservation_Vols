package Personne;

import Passagers.Passager;
import Vols.Vol;
import com.mysql.cj.xdevapi.Type;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Personne {
    private int id;
    private String nom;
    private String addresse;
    private String NumPassport;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getAddresse(){
        return addresse;
    }
    public void setAddresse(String adresse){
        this.addresse = adresse;
    }
    public String getNumPassport(){
        return NumPassport;
    }
    public void setNumPassport(String NumPassport){
        this.NumPassport = NumPassport;
    }

    public Personne(String nom, String address, String NumPassport){
        this.nom = nom;
        this.addresse = address;
        this.NumPassport = NumPassport;
    }
    public static void enregistrerPersonne(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nom");
        String nom = sc.nextLine();
        System.out.println("Entrez l'addresse");
        String addresse = sc.nextLine();
        System.out.println("Entrez le Numero de Passport");
        String NumPassport = sc.nextLine();

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to db successfuly...

            Statement stat  = conn.createStatement();
            String sql = "INSERT INTO personne (nom, addresse, NumPassport) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, addresse);
            preparedStatement.setString(3, NumPassport);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0){
                System.out.println("["+nom+"] a ete enregistre avec success");
            }
            stat.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void AfficherPersonne(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM personne";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Personnes Repertorie \n");
            System.out.println("id | Personne ");
            while (resultSet.next()) {
                int id_pers = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String addresse = resultSet.getString("addresse");
                String numPassport = resultSet.getString("NumPassport");

                System.out.println(id_pers + " | " + nom);
                System.out.println("--------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void consulterVols(){
        Vol.afficherVols();
    }
    public static void RechercheVols(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("*Destination*");
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to db successfuly...

            Statement stat  = conn.createStatement();
            String sql = "SELECT * FROM vol";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Destinations Repertories ");
            while (resultSet.next()) {
                String destination = resultSet.getString("destination");

                System.out.println(destination);

                System.out.println("--------------------");
            }
            System.out.println("Veillez saisir la destination : ");
            String destination = sc.nextLine();
            System.out.println("\n*Date*");
            System.out.println("Entrez la date prevu pour le vol(yyyy-MM-dd HH:mm:ss)");
            String DateString = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateDepart = LocalDateTime.parse(DateString, formatter);

            Statement stat2  = conn.createStatement();
            String sql2 = "SELECT * FROM vol WHERE destination = ? ";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1, destination.toLowerCase());
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            //int rowcount = preparedStatement2.executeUpdate();
            System.out.println("\n");
            System.out.println("vols disponible : ");
            System.out.println("\n");
            while (resultSet2.next()) {
                // Récupération des valeurs des colonnes
                int num_vol = resultSet2.getInt("num_vol");
                String compagnie = resultSet2.getString("compagnie");
                LocalDateTime DateDep = resultSet2.getObject("dateDep", Timestamp.class).toLocalDateTime();
                String destinationBD = resultSet2.getString("destination");
                // Affichage des valeurs dans la console
                if(DateDep.isAfter(dateDepart)){
                    System.out.println("Num Vol : " + num_vol);
                    System.out.println("Compagnie : " + compagnie);
                    System.out.println("Depart : " + DateDep);
                    System.out.println("Destination : " + destinationBD);
                    // Ajoutez ici les autres colonnes si nécessaire

                    System.out.println("\n--------------------");
                }
                else{
                    System.out.println("\nLes vols pour cette date sont indisponible");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean RechercheVolsBool(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("*Destination*");
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //Connected to db successfuly...

            Statement stat  = conn.createStatement();
            String sql = "SELECT * FROM vol";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Destinations Repertories ");
            while (resultSet.next()) {
                String destination = resultSet.getString("destination");

                System.out.println(destination);

                System.out.println("--------------------");
            }
            System.out.println("Veillez saisir la destination : ");
            String destination = sc.nextLine();
            System.out.println("\n*Date*");
            System.out.println("Entrez la date prevu pour le vol(yyyy-MM-dd HH:mm:ss)");
            String DateString = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateDepart = LocalDateTime.parse(DateString, formatter);

            Statement stat2  = conn.createStatement();
            String sql2 = "SELECT * FROM vol WHERE destination = ? ";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.setString(1, destination.toLowerCase());
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            //int rowcount = preparedStatement2.executeUpdate();
            System.out.println("\n");
            System.out.println("vols disponible : ");
            System.out.println("\n");

            while (resultSet2.next()) {
                // Récupération des valeurs des colonnes
                int num_vol = resultSet2.getInt("num_vol");
                String compagnie = resultSet2.getString("compagnie");
                LocalDateTime DateDep = resultSet2.getObject("dateDep", Timestamp.class).toLocalDateTime();
                String destinationBD = resultSet2.getString("destination");
                // Affichage des valeurs dans la console
                if(DateDep.isAfter(dateDepart)){
                    System.out.println("Num Vol : " + num_vol);
                    System.out.println("Compagnie : " + compagnie);
                    System.out.println("Depart : " + DateDep);
                    System.out.println("Destination : " + destinationBD);
                    // Ajoutez ici les autres colonnes si nécessaire

                    System.out.println("\n--------------------");
                    return true;
                }
                else{
                    System.out.println("\nLes vols pour cette date sont indisponible");
                    return false;
                }
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static void ReserverVols(){
        final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
        final String USERNAME = "root";
        final String PASSWORD = "";
        Scanner sc = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stat = conn.createStatement();
            String sql = "SELECT * FROM personne";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Personnes Repertories \n");
            System.out.println("id | Repertories ");
            while (resultSet.next()) {
                int id_pers = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String addresse = resultSet.getString("addresse");
                String numPassport = resultSet.getString("NumPassport");

                System.out.println(id_pers + " | " + nom);
                System.out.println("--------------------");
            }
            System.out.println("identifiant qui reserve : ");
            int id_pers = sc.nextInt();
            Personne.RechercheVols();
                System.out.println("Num Vol a Reserver: ");
                int num_vol = sc.nextInt();

                Statement stat2  = conn.createStatement();
                String sql2 = "INSERT INTO reservation (DateReservation, id_personne, num_vol)" +
                        "VALUES(?,?,?)";
                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                preparedStatement2.setObject(1,LocalDateTime.now(),Types.TIMESTAMP);
                preparedStatement2.setInt(2,id_pers);
                preparedStatement2.setInt(3,num_vol);
                int addedRows = preparedStatement2.executeUpdate();
                System.out.println("\n");
                if (addedRows>0){
                    System.out.println("Success");
                }
                else{
                    System.out.println("identifant ou numero de vol erronee");
                }

                Statement stat3  = conn.createStatement();
                String sql3 = "SELECT * FROM personne WHERE id = ?";
                PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
                preparedStatement3.setInt(1,id_pers);
                ResultSet resultSet1 = preparedStatement3.executeQuery();
                System.out.println("\n");
                while (resultSet1.next()){
                    String nomPassager = resultSet1.getString("nom");
                    String addressePassager = resultSet1.getString("addresse");
                    String numPassPassager = resultSet1.getString("NumPassport");

                    Statement stat4  = conn.createStatement();
                    String sql4 = "INSERT INTO passager(nom, addresse, NumPassport)" +
                            "VALUES(?,?,?)";
                    PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
                    preparedStatement4.setString(1,nomPassager);
                    preparedStatement4.setString(2,addressePassager);
                    preparedStatement4.setString(3,numPassPassager);
                    int rows = preparedStatement4.executeUpdate();
                    System.out.println("\n");
                    if (rows>0){
                        System.out.println("Le vol Num ["+num_vol+"] a ete reserve par ["+nomPassager+"]");
                    }else{
                        System.out.println("Echec dinsertion");
                    }
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*System.out.println("*Enrejistrement Personne*");
        Personne.enregistrerPersonne();
        /*System.out.println("*Consultation Vols*");
        Personne.consulterVols();*/
        /*System.out.println("*Recherche Vols*");
        Personne.RechercheVols();*/
        /*System.out.println("*Reserver Vols*");
        Personne.ReserverVols();*/
        /*System.out.println("*Affichage Personne*");
        Personne.AfficherPersonne();*/
    }
}
