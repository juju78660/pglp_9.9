package DAO;

import Command.CommandeException;
import Formes.CompositeFormeVideException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DAO<T> {
    public Connection connect = null;
    String dbURL = "jdbc:derby:DB;create=true";

    private void creationTables() throws SQLException {
        connect = DriverManager.getConnection(dbURL);
        PreparedStatement requete11 = this.connect.prepareStatement("DROP TABLE Carre");
        requete11.executeUpdate();
        this.connect.commit();
        PreparedStatement requete12 = this.connect.prepareStatement("DROP TABLE Cercle");
        requete12.executeUpdate();
        this.connect.commit();
        PreparedStatement requete13 = this.connect.prepareStatement("DROP TABLE Rectangle");
        requete13.executeUpdate();
        this.connect.commit();
        PreparedStatement requete14 = this.connect.prepareStatement("DROP TABLE Triangle");
        requete14.executeUpdate();
        this.connect.commit();
        PreparedStatement requete15 = this.connect.prepareStatement("DROP TABLE ListeFormes");
        requete15.executeUpdate();
        this.connect.commit();
        PreparedStatement requete1 = this.connect.prepareStatement("CREATE TABLE Carre (Nom VARCHAR(50) NOT NULL, PosX VARCHAR(10) NOT NULL, PosY VARCHAR(10) NOT NULL, Cote INTEGER NOT NULL)");
        requete1.executeUpdate();
        this.connect.commit();
        PreparedStatement requete2 = this.connect.prepareStatement("CREATE TABLE Cercle (Nom VARCHAR(50) NOT NULL, PosX VARCHAR(10) NOT NULL, PosY VARCHAR(10) NOT NULL, Rayon INTEGER NOT NULL)");
        requete2.executeUpdate();
        this.connect.commit();
        PreparedStatement requete3 = this.connect.prepareStatement("CREATE TABLE Rectangle (Nom VARCHAR(50) NOT NULL, PosX VARCHAR(10) NOT NULL, PosY VARCHAR(10) NOT NULL, Longueur INTEGER NOT NULL, Largeur INTEGER NOT NULL)");
        requete3.executeUpdate();
        this.connect.commit();
        PreparedStatement requete4 = this.connect.prepareStatement("CREATE TABLE Triangle (Nom VARCHAR(50) NOT NULL, Pos1X VARCHAR(10) NOT NULL, Pos1Y VARCHAR(10) NOT NULL, Pos2X VARCHAR(10) NOT NULL, Pos2Y VARCHAR(10) NOT NULL, Pos3X VARCHAR(10) NOT NULL, Pos3Y VARCHAR(10) NOT NULL)");
        requete4.executeUpdate();
        this.connect.commit();
        PreparedStatement requete5= this.connect.prepareStatement("CREATE TABLE ListeFormes (NomForme VARCHAR(50) NOT NULL, NomTable VARCHAR(50) NOT NULL)");
        requete5.executeUpdate();
        this.connect.commit();
        connect = DriverManager.getConnection(dbURL);
        PreparedStatement requete6 = this.connect.prepareStatement("CREATE TABLE Composite (NomComposite VARCHAR(50) NOT NULL, NomForme VARCHAR(50) NOT NULL, TableForme VARCHAR(50) NOT NULL)");
        requete6.executeUpdate();
    }

    public void initBD() throws SQLException {
        connect = DriverManager.getConnection(dbURL);
        try{
            PreparedStatement requete1 = this.connect.prepareStatement("TRUNCATE TABLE Carre");
            requete1.executeUpdate();

            PreparedStatement requete2 = this.connect.prepareStatement("TRUNCATE TABLE Cercle");
            requete2.executeUpdate();

            PreparedStatement requete3 = this.connect.prepareStatement("TRUNCATE TABLE Rectangle");
            requete3.executeUpdate();

            PreparedStatement requete4 = this.connect.prepareStatement("TRUNCATE TABLE Triangle");
            requete4.executeUpdate();

            PreparedStatement requete5 = this.connect.prepareStatement("TRUNCATE TABLE ListeFormes");
            requete5.executeUpdate();

            PreparedStatement requete6 = this.connect.prepareStatement("TRUNCATE TABLE Composite");
            requete6.executeUpdate();
            this.connect.commit();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (connect != null) {
            try {
                connect.commit();
                connect.close();
            }
            catch (SQLException except) {
                except.printStackTrace();
            }
        }
    }

    public void affichageTable(String nomTable){
        try {
            connect = DriverManager.getConnection(dbURL);
            PreparedStatement requete = connect.prepareStatement("SELECT * FROM " + nomTable);
            ResultSet resultat = requete.executeQuery();

            ResultSetMetaData resultMeta = resultat.getMetaData();

            System.out.println("\n**************************************************");
            //On affiche le nom des colonnes
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

            System.out.println("\n**************************************************");

            while(resultat.next()){
                for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                    System.out.print("\t" + resultat.getObject(i).toString() + "\t |");

                System.out.println("\n-----------------------------------------------");

            }
            resultat.close();
            requete.close();
            this.connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> recupListeFormes() throws SQLException {
        Map<String, String> listeFormes = new HashMap();

        connect = DriverManager.getConnection(dbURL);
        String contenuRequete = "SELECT * FROM ListeFormes";
        PreparedStatement requete = connect.prepareStatement(contenuRequete);
        ResultSet resultat = requete.executeQuery();
        while(resultat.next()) {
            listeFormes.put(resultat.getString("NomForme"), resultat.getString("NomTable"));
        }
        return listeFormes;
    }

    public abstract boolean create(T obj) throws FormeDejaExistenteException, SQLException;


    public abstract boolean delete(String nom) throws SQLException, FormeInexistanteException;


    public abstract T update(T obj) throws SQLException, FormeInexistanteException, FormeDejaExistenteException, CommandeException, CompositeFormeVideException;


    public abstract T find(String nom) throws SQLException, FormeInexistanteException;

    public abstract void init() throws SQLException;
}
