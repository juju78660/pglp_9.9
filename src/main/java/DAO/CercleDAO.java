package DAO;

import Formes.Carre;
import Formes.Cercle;
import Formes.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CercleDAO extends DAO<Cercle>{
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";
    private Map<String, Cercle> listeCercles;

    @Override
    public boolean create(Cercle obj) throws FormeDejaExistenteException, SQLException {
        if(!listeCercles.containsKey(obj.nom)){
            listeCercles.put(obj.nom, obj);
            String contenuRequete = "INSERT INTO ListeFormes (NomForme, NomTable) VALUES (?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setString(2, "Cercle");
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            String contenuRequete2 = "INSERT INTO Cercle (Nom, PosX, PosY, Rayon) VALUES (?, ?, ?, ?)";
            PreparedStatement requete2 = null;
            try {
                requete2 = connect.prepareStatement(contenuRequete2);
                requete2.setString(1, obj.nom);
                requete2.setInt(2, obj.p.x);
                requete2.setInt(3, obj.p.y);
                requete2.setInt(4, obj.rayon);
                requete2.executeUpdate();
                requete2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return true;
        }
        else throw new FormeDejaExistenteException();
    }

    @Override
    public boolean delete(String nom) throws SQLException {
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "DELETE FROM Cercle WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
            listeCercles.remove(nom);
        } catch (SQLException e) {
            e.printStackTrace();
            connect.commit();
            return false;
        }
        connect.commit();
        connect.close();
        return true;
    }

    @Override
    public Cercle update(Cercle obj) throws SQLException, FormeInexistanteException {
        if(listeCercles.containsKey(obj.nom)){
            try {
                connect = DriverManager.getConnection(dbURL);
                String contenuRequete = "UPDATE Cercle SET PosX = ?, PosY = ?, Rayon = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.rayon);
                requete.setString(4, obj.nom);
                requete.executeUpdate();
                requete.close();
                listeCercles.replace(obj.nom, obj);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return obj;
        }
        else{
            throw new FormeInexistanteException();
        }
    }

    @Override
    public Cercle find(String nom) throws SQLException, FormeInexistanteException {
        Cercle cercle = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Cercle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()){
                cercle = new Cercle(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("PosX"), resultat.getInt("PosY")),
                        resultat.getInt("Rayon"));
            }
            else{
                throw new FormeInexistanteException();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connect.commit();
        connect.close();
        return cercle;
    }

    @Override
    public void init() throws SQLException {
        recuperationDonnees();
    }

    public ArrayList<Cercle> recuperationDonnees() throws SQLException {
        ArrayList<Cercle> donnees = new ArrayList<>();
        listeCercles = new HashMap<>();
        connect = DriverManager.getConnection(dbURL);
        String contenuRequete = "SELECT * FROM Cercle";
        PreparedStatement requete = connect.prepareStatement(contenuRequete);
        ResultSet resultat = requete.executeQuery();
        while(resultat.next()) {
            donnees.add(new Cercle(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Rayon")));
            listeCercles.put(resultat.getString("Nom"), new Cercle(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Rayon")));
        }
        return donnees;
    }
}
