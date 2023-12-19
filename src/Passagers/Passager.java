package Passagers;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Passager {
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

        public Passager(String nom, String address, String NumPassport, int numPlace){
            this.nom = nom;
            this.addresse = address;
            this.NumPassport = NumPassport;
        }

        public static void AfficherPassagers(){
            final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
            final String USERNAME = "root";
            final String PASSWORD = "";
            Scanner sc = new Scanner(System.in);

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                Statement stat = conn.createStatement();
                String sql = "SELECT * FROM passager";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("Passagers Repertories \n");
                System.out.println("id | Passager ");
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
        public static void AnnulerReservation(){
            final String DB_URL = "jdbc:mysql://localhost/gestion_vols";
            final String USERNAME = "root";
            final String PASSWORD = "";
            Scanner sc = new Scanner(System.in);

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

                Statement stat = conn.createStatement();
                String sql = "SELECT num_reservation, nom, destination, dateDep FROM personne p, reservation r, vol v WHERE (p.id = r.id_personne) AND (r.num_vol = v.num_vol)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("Passagers Repertories \n");
                System.out.println("num_reservation | Passagers  | destination | Depart");
                while (resultSet.next()) {
                    int num_reservation = resultSet.getInt("num_reservation");
                    String nom = resultSet.getString("nom");
                    String destination = resultSet.getString("destination");
                    LocalDateTime Depart = resultSet.getObject("dateDep", Timestamp.class).toLocalDateTime();
                    /*String addresse = resultSet.getString("addresse");
                    String numPassport = resultSet.getString("NumPassport");*/

                    System.out.println(num_reservation + "               | " + nom + "       | " + destination + "  | " + Depart);
                    System.out.println("--------------------");
                }
                System.out.println("\nnum_reservation a supprimer : ");
                int num_reservation = sc.nextInt();


                    Statement stat2 = conn.createStatement();

                    String sql3 = "SELECT NumPassport FROM reservation r, personne p WHERE(r.id_personne = p.id) AND( num_reservation = ?)";

                        PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
                        preparedStatement3.setInt(1, num_reservation);
                        ResultSet resultSet2 = preparedStatement3.executeQuery();
                        while (resultSet2.next()) {
                            String NumPassport = resultSet2.getString("NumPassport");
                            String sql4 = "DELETE FROM passager WHERE NumPassport = ?";
                            PreparedStatement preparedStatement4 = conn.prepareStatement(sql4);
                            preparedStatement4.setString(1, NumPassport);
                            int addedRows2 = preparedStatement4.executeUpdate();
                            if(addedRows2>0){
                                System.out.println("Supprime avec succes des passagers");
                                String sql2 = "DELETE FROM reservation WHERE num_reservation = ?";
                                PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
                                preparedStatement2.setInt(1, num_reservation);
                                int addedRows = preparedStatement2.executeUpdate();
                                if(addedRows>0){
                                    System.out.println("reservation supprimee avec succes");
                                }
                            }
                        }

                }catch (Exception e){
                    e.printStackTrace();
                }
        }

    public static void main(String[] args) {
        System.out.println("Affichage passagers");
        Passager.AfficherPassagers();
        System.out.println("Annuler reservation");
        Passager.AnnulerReservation();
    }
}
