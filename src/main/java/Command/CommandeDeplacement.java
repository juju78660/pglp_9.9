package Command;

import DAO.*;
import Formes.Forme;

import java.sql.SQLException;

public class CommandeDeplacement implements Commande {

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public void execute() {
        System.out.println("move");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
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
