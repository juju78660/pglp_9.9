package Command;

import DAO.*;
import Formes.Forme;

import java.sql.SQLException;
import java.util.Map;

public class CommandeAffichage implements Commande {
    String nomForme = "";
    String typeForme = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public void execute() throws SQLException, FormeInexistanteException {
        listeFormes = carreDAO.recupListeFormes();
        if(listeFormes.containsKey(nomForme)) typeForme = listeFormes.get(nomForme);
        else throw new FormeInexistanteException("La forme " + nomForme + " n'existe pas");
    }

    @Override
    public void recupDonnees(String donnees) {
        nomForme = donnees.split("\\(")[1].replace(")", "");
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException, CommandeException {
        Forme recupForme = null;
        switch(typeForme){
            case "Carre":
                recupForme = carreDAO.find(nomForme);
                System.out.println(recupForme.toString());
                break;
            case "Cercle":
                recupForme = cercleDAO.find(nomForme);
                System.out.println(recupForme.toString());
                break;
            case "Rectangle":
                recupForme = rectangleDAO.find(nomForme);
                System.out.println(recupForme.toString());
                break;
            case "Triangle":
                recupForme = triangleDAO.find(nomForme);
                System.out.println(recupForme.toString());
                break;
        }
    }
}
