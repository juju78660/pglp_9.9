package DAO;

import Command.CommandeException;
import Formes.*;

import java.sql.*;
import java.util.*;

public class CompositeFormeDAO extends DAO<CompositeForme>{

    private Connection connect = null;
    private String dbURL = "jdbc:derby:DB;create=true";
    private Map<String, HashMap<String, Forme>> listeComposites;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public boolean create(CompositeForme obj) throws FormeDejaExistenteException, SQLException {
        if(!listeComposites.containsKey(obj.nom)){
            listeComposites.put(obj.nom, new HashMap<>());
            String contenuRequete = "INSERT INTO ListeFormes (NomForme, NomTable) VALUES (?, ?)";
            PreparedStatement requete = null;
            connect = DriverManager.getConnection(dbURL);
            try {
                requete = connect.prepareStatement(contenuRequete);
                requete.setString(1, obj.nom);
                requete.setString(2, "Composite");
                requete.executeUpdate();
                requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            String contenuRequete2 = "INSERT INTO Composite (NomComposite, NomForme, TableForme) VALUES (?, ?, ?)";
            PreparedStatement requete2 = null;
            connect = DriverManager.getConnection(dbURL);
            try {
                requete2 = connect.prepareStatement(contenuRequete2);
                requete2.setString(1, obj.nom);
                requete2.setString(2, "CREATION");
                requete2.setString(3, "CREATION");
                requete2.executeUpdate();
                requete2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connect.commit();
            connect.close();
            return true;
        }
        else throw new FormeDejaExistenteException("L'objet");
    }

    @Override
    public boolean delete(String nom) throws FormeInexistanteException, SQLException {
        if(listeComposites.containsKey(nom)) {
            listeComposites.remove(nom);
        }
        else throw new FormeInexistanteException("Le composite" + nom + " n'existe pas");
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "DELETE FROM Composite WHERE NomComposite = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connect.close();
            return false;
        }
        connect.commit();
        connect.close();

        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "DELETE FROM ListeFormes WHERE NomForme = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            requete.executeUpdate();
            requete.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public CompositeForme update(CompositeForme obj) throws SQLException, FormeInexistanteException, FormeDejaExistenteException, CommandeException, CompositeFormeVideException {
        HashMap<String, Forme> listeFormes;
        HashMap<String, Forme> ancienneListeFormes;
        if(listeComposites.containsKey(obj.nom)){
            ancienneListeFormes = listeComposites.get(obj.nom);
            listeFormes  = listeComposites.get(obj.nom);
        }
        else throw new FormeInexistanteException("Le composite " + obj.nom + " n'existe pas");

        if(obj.getTypeUpdate()==1){ // AJOUT D'UNE FORME AU COMPOSITE
            if(!listeFormes.containsKey(obj.formeAUpdate.nom)){ // L'OBJET N'EST PAS DEJA DANS LE COMPOSITE
                String contenuRequete2 = "INSERT INTO Composite (NomComposite, NomForme, TableForme) VALUES (?, ?, ?)";
                PreparedStatement requete2 = null;
                connect = DriverManager.getConnection(dbURL);
                try {
                    requete2 = connect.prepareStatement(contenuRequete2);
                    requete2.setString(1, obj.nom);
                    requete2.setString(2, obj.formeAUpdate.nom);
                    requete2.setString(3, obj.nomTableForme);
                    requete2.executeUpdate();
                    requete2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connect.commit();
                connect.close();
                listeFormes.put(obj.formeAUpdate.nom, obj.formeAUpdate);
                listeComposites.replace(obj.nom, ancienneListeFormes, listeFormes);
            }
            else throw new FormeDejaExistenteException("La forme " + obj.formeAUpdate.nom + " est deja dans le composite"); // L'OBJET EST  DEJA DANS LE COMPOSITE --> ERREUR
        }
        else if(obj.getTypeUpdate()==2){    // UPDATE D'UNE FORME DU COMPOSITE
            if(listeFormes.containsKey(obj.formeAUpdate.nom)){ // L'OBJET EST DEJA DANS LE COMPOSITE, ON PEUT UPDATE
                Forme recupForme = null;
                Forme forme = obj.formeAUpdate;
                switch (obj.nomTableForme) {
                    case "Carre":
                        carreDAO.init();
                        recupForme = carreDAO.update((Carre)forme);
                        break;
                    case "Cercle":
                        cercleDAO.init();
                        recupForme = cercleDAO.update((Cercle)forme);
                        break;
                    case "Rectangle":
                        rectangleDAO.init();
                        recupForme = rectangleDAO.update((Rectangle)forme);
                        break;
                    case "Triangle":
                        triangleDAO.init();
                        recupForme = triangleDAO.update((Triangle)forme);
                        break;
                }
                listeFormes.replace(obj.formeAUpdate.nom, obj.ancienneFormeAUpdate, recupForme);
                listeComposites.replace(obj.nom, ancienneListeFormes, listeFormes);
            }
            else throw new FormeInexistanteException("La forme " + obj.formeAUpdate + " n'existe pas/ne fait pas partie de ce composite");
        }
        else if (obj.getTypeUpdate()==3){   // DEPLACEMENT DE TOUTE LES FORMES DU COMPOSITE
            if(listeFormes.size() > 0){
                Point p = obj.getPointDeplacement();
                Set cles = listeFormes.keySet();
                Iterator it = cles.iterator();
                while (it.hasNext()){
                    Object cle = it.next();
                    Forme forme = listeFormes.get(cle);
                    Forme formeAModifier = null;
                    if(forme instanceof Carre){
                        obj.setNomTableForme("Carre");
                        formeAModifier = new Carre(forme.nom, p, ((Carre) forme).cote);
                    }
                    else if(forme instanceof Cercle){
                        obj.setNomTableForme("Cercle");
                        formeAModifier = new Cercle(forme.nom, p, ((Cercle) forme).rayon);
                    }
                    else if(forme instanceof Rectangle){
                        obj.setNomTableForme("Rectangle");
                        formeAModifier = new Rectangle(forme.nom, p, ((Rectangle) forme).longueur, ((Rectangle) forme).longueur);
                    }
                    else{
                        obj.setNomTableForme("Triangle");
                        Triangle triangle = (Triangle) forme;
                        Point sommet1 = new Point(triangle.sommet1.x + p.x, triangle.sommet1.y + p.y);
                        Point sommet2 = new Point(triangle.sommet2.x + p.x, triangle.sommet2.y + p.y);
                        Point sommet3 = new Point(triangle.sommet3.x + p.x, triangle.sommet3.y + p.y);
                        formeAModifier = new Triangle(forme.nom, sommet1, sommet2, sommet3);
                    }
                    System.out.println(formeAModifier);
                    obj.setFormeAUpdate(forme);
                    obj.setTypeUpdate(2);
                    update(obj);
                }
            }
            else throw new CompositeFormeVideException();
            //compositeFormeDAO.update((Forme)obj.formeAUpdate);
        }
        else if (obj.getTypeUpdate()==4){       // SUPPRESSION D'UNE FORME DU COMPOSITE
            if(listeFormes.containsKey(obj.formeAUpdate.nom)){ // L'OBJET EST BIEN DANS LE COMPOSITE, ON PEUT SUPPRIMER
                String contenuRequete2 = "DELETE FROM Composite WHERE NomForme = ?";
                PreparedStatement requete2 = null;
                connect = DriverManager.getConnection(dbURL);
                try {
                    requete2 = connect.prepareStatement(contenuRequete2);
                    requete2.setString(1, obj.formeAUpdate.nom);
                    requete2.executeUpdate();
                    requete2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connect.commit();
                connect.close();
                listeFormes.remove(obj.formeAUpdate);
                listeComposites.replace(obj.nom, ancienneListeFormes, listeFormes);            }
            else throw new FormeInexistanteException("La forme " + obj.formeAUpdate + " n'existe pas/ne fait pas partie de ce composite");
        }
        else throw new CommandeException("La commande n'est pas correctement utilisee");

        obj.setNomTableForme(null);
        obj.setFormeAUpdate(null);
        obj.setTypeUpdate(0);
        return obj;
    }

    @Override
    public CompositeForme find(String nom) throws SQLException, FormeInexistanteException {
        CompositeForme composite = null;
        try {
            connect = DriverManager.getConnection(dbURL);
            String contenuRequete = "SELECT * FROM Composite WHERE NomComposite = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nom);
            ResultSet resultat = requete.executeQuery();
            if(resultat.next()) {
                composite = new CompositeForme(resultat.getString("NomComposite"));
                while(resultat.next()) {
                    String nomForme = resultat.getString("NomForme");
                    String tableForme = resultat.getString("TableForme");
                    Forme recupForme = null;
                    switch (tableForme) {
                        case "Carre":
                            recupForme = carreDAO.find(nomForme);
                            break;
                        case "Cercle":
                            recupForme = cercleDAO.find(nomForme);
                            break;
                        case "Rectangle":
                            recupForme = rectangleDAO.find(nomForme);
                            break;
                        case "Triangle":
                            recupForme = triangleDAO.find(nomForme);
                            break;
                    }
                    composite.ajouter(recupForme);
                }
            }
            else{
                throw new FormeInexistanteException("Le composite " + nom + " n'existe pas" );
            }
            resultat.close();
            requete.close();
        } catch (SQLException | CompositeFormeContientDejaException e) {
            e.printStackTrace();
        }
        connect.commit();
        connect.close();
        return composite;
    }

        @Override
    public void init() throws SQLException {
        try {
            recuperationDonnees();
        } catch (FormeInexistanteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CompositeForme> recuperationDonnees() throws SQLException, FormeInexistanteException {
        ArrayList<CompositeForme> donnees = new ArrayList<>();
        listeComposites = new HashMap<String, HashMap<String, Forme>>();
        connect = DriverManager.getConnection(dbURL);
        String contenuRequete = "SELECT * FROM Composite";
        PreparedStatement requete = connect.prepareStatement(contenuRequete);
        ResultSet resultat = requete.executeQuery();
        while(resultat.next()) {
            String nomComposite = resultat.getString("NomComposite");
            String nomForme = resultat.getString("NomForme");
            String tableForme = resultat.getString("TableForme");
            Forme recupForme = null;
            if(!nomForme.equals("CREATION")){
                switch(tableForme){
                    case "Carre":
                        recupForme = carreDAO.find(nomForme);
                        break;
                    case "Cercle":
                        recupForme = cercleDAO.find(nomForme);
                        break;
                    case "Rectangle":
                        recupForme = rectangleDAO.find(nomForme);
                        break;
                    case "Triangle":
                        recupForme = triangleDAO.find(nomForme);
                        break;
                }
                if(listeComposites.containsKey(nomComposite)){
                    HashMap listeFormes = listeComposites.get(nomComposite);
                    listeFormes.put(recupForme.nom, recupForme);
                    listeComposites.put(nomComposite, listeFormes);
                }
                else{
                    HashMap listeFormes = new HashMap();
                    listeFormes.put(recupForme.nom, recupForme);
                    listeComposites.put(nomComposite, listeFormes);
                }
            }
            else{
                listeComposites.put(nomComposite, new HashMap());
            }
        }
        return donnees;
    }
}
