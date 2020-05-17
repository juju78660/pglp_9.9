package DAO;

import Formes.Rectangle;
import Formes.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RectangleDAO extends DAO<Rectangle> {
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";
    private Map<String, Rectangle> listeRectangles;

    @Override
    public boolean create(Rectangle obj) throws FormeDejaExistenteException, SQLException {
        if(!listeRectangles.containsKey(obj.nom)){
            listeRectangles.put(obj.nom, obj);
            String contenuRequete = "INSERT INTO ListeFormes (NomForme, NomTable) VALUES (?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setString(2, "Rectangle");
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            String contenuRequete2 = "INSERT INTO Rectangle (Nom, PosX, PosY, Longueur, Largeur) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement requete2 = null;
            try {
                requete2 = connect.prepareStatement(contenuRequete2);
                requete2.setString(1, obj.nom);
                requete2.setInt(2, obj.p.x);
                requete2.setInt(3, obj.p.y);
                requete2.setInt(4, obj.longueur);
                requete2.setInt(5, obj.largeur);
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
            String contenuRequete = "DELETE FROM Rectangle WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
            listeRectangles.remove(nom);
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
    public Rectangle update(Rectangle obj) throws SQLException, FormeInexistanteException {
        if(listeRectangles.containsKey(obj.nom)){
            try {
                connect = DriverManager.getConnection(dbURL);
                String contenuRequete = "UPDATE Rectangle SET PosX = ?, PosY = ?, Longueur = ?, Largeur = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.longueur);
                requete.setInt(4, obj.largeur);
                requete.setString(5, obj.nom);
                requete.executeUpdate();
                requete.close();
                listeRectangles.replace(obj.nom, obj);
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
    public Rectangle find(String nom) throws SQLException, FormeInexistanteException {
        Rectangle rectangle = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Rectangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()){
                rectangle = new Rectangle(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("PosX"), resultat.getInt("PosY")),
                        resultat.getInt("Longueur"), resultat.getInt("Largeur"));
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
        return rectangle;
    }

    @Override
    public void init() throws SQLException {
        recuperationDonnees();
    }

    public ArrayList<Rectangle> recuperationDonnees() throws SQLException {
        ArrayList<Rectangle> donnees = new ArrayList<>();
        listeRectangles = new HashMap<>();
        connect = DriverManager.getConnection(dbURL);
        String contenuRequete = "SELECT * FROM Rectangle";
        PreparedStatement requete = connect.prepareStatement(contenuRequete);
        ResultSet resultat = requete.executeQuery();
        while(resultat.next()) {
            donnees.add(new Rectangle(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Longueur"), resultat.getInt("Largeur")));
            listeRectangles.put(resultat.getString("Nom"), new Rectangle(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Longueur"), resultat.getInt("Largeur")));
        }
        return donnees;
    }
}