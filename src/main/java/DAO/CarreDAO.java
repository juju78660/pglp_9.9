package DAO;

import Formes.Carre;
import Formes.Point;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CarreDAO extends DAO<Carre>{
    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";
    private Map<String, Carre> listeCarres;

    @Override
    public boolean create(Carre obj) throws FormeDejaExistenteException, SQLException {
        if(!listeCarres.containsKey(obj.nom)){
            listeCarres.put(obj.nom, obj);
            String contenuRequete = "INSERT INTO ListeFormes (NomForme, NomTable) VALUES (?, ?)";
            PreparedStatement requete = null;
            connect = DriverManager.getConnection(dbURL);
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setString(2, "Carre");
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            String contenuRequete2 = "INSERT INTO Carre (Nom, PosX, PosY, Cote) VALUES (?, ?, ?, ?)";
            PreparedStatement requete2 = null;
            try {
                requete2 = connect.prepareStatement(contenuRequete2);
                requete2.setString(1, obj.nom);
                requete2.setInt(2, obj.p.x);
                requete2.setInt(3, obj.p.y);
                requete2.setInt(4, obj.cote);
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
            String contenuRequete = "DELETE FROM Carre WHERE nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
            listeCarres.remove(nom);
        } catch (SQLException e) {
            e.printStackTrace();
            connect.close();
            return false;
        }
        connect.commit();
        connect.close();
        return true;
    }

    @Override
    public Carre update(Carre obj) throws SQLException, FormeInexistanteException {
        if(listeCarres.containsKey(obj.nom)){
            try {
                connect = DriverManager.getConnection(dbURL);
                String contenuRequete = "UPDATE Carre SET PosX = ?, PosY = ?, Cote = ? WHERE Nom = ?";
                PreparedStatement requete = connect.prepareStatement(contenuRequete);
                requete.setInt(1, obj.p.x);
                requete.setInt(2, obj.p.y);
                requete.setInt(3, obj.cote);
                requete.setString(4, obj.nom);
                requete.executeUpdate();
                requete.close();
                listeCarres.replace(obj.nom, obj);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return obj;
        }
        else{
            throw new FormeInexistanteException("La forme " + obj.nom + " n'existe pas");
        }
    }

    @Override
    public Carre find(String nom) throws SQLException, FormeInexistanteException {
        Carre carre = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Carre WHERE Nom = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                carre = new Carre(
                        resultat.getString("Nom"),
                        new Point(resultat.getInt("PosX"), resultat.getInt("PosY")),
                        resultat.getInt("Cote"));
            }
            else{
                throw new FormeInexistanteException("La forme " + nom + " n'existe pas" );
            }
            resultat.close();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connect.commit();
        connect.close();
        return carre;
    }

    @Override
    public void init() throws SQLException {
        recuperationDonnees();
    }

    public ArrayList<Carre> recuperationDonnees() throws SQLException {
        ArrayList<Carre> donnees = new ArrayList<>();
        listeCarres = new HashMap<>();
        connect = DriverManager.getConnection(dbURL);
        String contenuRequete = "SELECT * FROM Carre";
        PreparedStatement requete = connect.prepareStatement(contenuRequete);
        ResultSet resultat = requete.executeQuery();
        while(resultat.next()) {
            donnees.add(new Carre(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Cote")));
            listeCarres.put(resultat.getString("Nom"), new Carre(resultat.getString("Nom"), new Point(resultat.getInt("PosX"), resultat.getInt("PosY")), resultat.getInt("Cote")));
        }
        return donnees;
    }
}
