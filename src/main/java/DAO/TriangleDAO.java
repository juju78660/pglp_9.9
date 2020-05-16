package DAO;

import java.sql.*;

import Formes.Point;
import Formes.Triangle;

public class TriangleDAO extends DAO<Triangle>{
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";

    @Override
    public boolean create(Triangle obj) throws FormeDejaExistenteException, SQLException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Triangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()) {
                objetDejaExistant = true;
                resultat.close();
                requete.close();
                connect.commit();
                connect.close();
                throw new FormeDejaExistenteException();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!objetDejaExistant) {
            String contenuRequete = "INSERT INTO Triangle (Nom, Pos1X, Pos1Y, Pos2X, Pos2Y, Pos3X, Pos3Y) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setInt(2, obj.sommet1.x);
                requete.setInt(3, obj.sommet1.y);
                requete.setInt(4, obj.sommet2.x);
                requete.setInt(5, obj.sommet2.y);
                requete.setInt(6, obj.sommet3.x);
                requete.setInt(7, obj.sommet3.y);
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return true;
        }
        connect.commit();
        connect.close();
        return false;
    }

    @Override
    public boolean delete(String nom) throws SQLException {
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "DELETE FROM Triangle WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
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
    public Triangle update(Triangle obj) throws SQLException, FormeInexistanteException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Triangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                objetDejaExistant = true;
                resultat.close();
                requete.close();
                connect.commit();
                connect.close();
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(objetDejaExistant) {
            try {
                connect = DriverManager.getConnection(dbURL);
                String contenuRequete = "UPDATE Triangle SET Pos1X = ?, Pos1Y = ?, Pos2X = ?, Pos2Y = ?, Pos3X = ?, Pos3Y = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.sommet1.x);
                requete.setInt(2, obj.sommet1.y);
                requete.setInt(3, obj.sommet2.x);
                requete.setInt(4, obj.sommet2.y);
                requete.setInt(5, obj.sommet3.x);
                requete.setInt(6, obj.sommet3.y);
                requete.setString(7, obj.nom);
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            throw new FormeInexistanteException();
        }
        connect.commit();
        connect.close();
        return obj;
    }

    @Override
    public Triangle find(String nom) throws SQLException, FormeInexistanteException {
        Triangle triangle = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Triangle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()){
                triangle = new Triangle(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("Pos1X"), resultat.getInt("Pos1Y")), new Point(resultat.getInt("Pos2X"), resultat.getInt("Pos2Y")), new Point(resultat.getInt("Pos3X"), resultat.getInt("Pos3Y")));
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
        return triangle;
    }
}