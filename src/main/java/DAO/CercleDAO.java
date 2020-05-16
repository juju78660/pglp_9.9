package DAO;

import Formes.Cercle;
import Formes.Point;

import java.sql.*;

public class CercleDAO extends DAO<Cercle>{
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";

    @Override
    public boolean create(Cercle obj) throws FormeDejaExistenteException, SQLException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Cercle WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, obj.nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
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
        if(!objetDejaExistant){
            String contenuRequete = "INSERT INTO Cercle (Nom, PosX, PosY, Rayon) VALUES (?, ?, ?, ?)";
            PreparedStatement requete = null;
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setInt(2, obj.p.x);
                requete.setInt(3, obj.p.y);
                requete.setInt(4, obj.rayon);
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
            String contenuRequete = "DELETE FROM Cercle WHERE nom = ?";
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
    public Cercle update(Cercle obj) throws SQLException, FormeInexistanteException {
        boolean objetDejaExistant = false;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Cercle WHERE Nom = ?";
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
                String contenuRequete = "UPDATE Cercle SET PosX = ?, PosY = ?, Rayon = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.rayon);
                requete.setString(4, obj.nom);
                requete.executeUpdate();
                requete.close();
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
}
