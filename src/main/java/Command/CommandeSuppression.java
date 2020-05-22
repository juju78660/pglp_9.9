package Command;

import DAO.*;
import Formes.*;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CommandeSuppression implements Commande {
    String nomForme = "";
    String typeForme = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();
    CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();

    @Override
    public void execute() throws SQLException, FormeInexistanteException {
        listeFormes = carreDAO.recupListeFormes();
        if(listeFormes.containsKey(nomForme)){
            listeFormes.remove(nomForme);
            Connection connect = DriverManager.getConnection("jdbc:derby:DB;create=true");
            String contenuRequete = "SELECT * FROM Composite WHERE NomForme = ?";
            PreparedStatement requete = connect.prepareStatement(contenuRequete);
            requete.setString(1, nomForme);
            ResultSet resultat = requete.executeQuery();
            while(resultat.next()) {
                String nomComposite = resultat.getString("NomComposite");
                String nomForme = resultat.getString("NomForme");
                String tableForme = resultat.getString("TableForme");
                if(!nomForme.equals("CREATION")){
                    Forme recupForme = null;
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
                    compositeFormeDAO.init();
                    CompositeForme compositeForme = compositeFormeDAO.find(nomComposite);
                    compositeForme.setFormeAUpdate(recupForme);
                    compositeForme.setNomTableForme(typeForme);
                    compositeForme.setTypeUpdate(4);
                    try {
                        compositeFormeDAO.update(compositeForme);
                    } catch (FormeDejaExistenteException e) {
                        e.printStackTrace();
                    } catch (CommandeException e) {
                        e.printStackTrace();
                    } catch (CompositeFormeVideException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else throw new FormeInexistanteException("La forme " + nomForme + " n'existe pas");
        switch(typeForme) {
            case "Carre":
                carreDAO.init();
                carreDAO.delete(nomForme);
                break;
            case "Cercle":
                cercleDAO.init();
                cercleDAO.delete(nomForme);
                break;
            case "Rectangle":
                rectangleDAO.init();
                rectangleDAO.delete(nomForme);
                break;
            case "Triangle":
                triangleDAO.init();
                triangleDAO.delete(nomForme);
                break;
            case "Composite":
                compositeFormeDAO.init();
                compositeFormeDAO.delete(nomForme);
                break;
        }
    }

    @Override
    public void recupDonnees(String donnees) throws CommandeException {
        try{
            nomForme = donnees.split("\\(")[1].replace(")", "");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandeException("La commande n'est pas correctement entree (ex: remove(nomForme)");
        }
    }

    @Override
    public void print(){
        System.out.println("La forme " + nomForme + " a ete supprimee");
        Set cles = listeFormes.keySet();
        Iterator it = cles.iterator();
        System.out.println("Formes restantes:");
        while (it.hasNext()) {
            Object cle = it.next();
            System.out.println("    " + cle + " - " + listeFormes.get(cle));
        }
    }
}