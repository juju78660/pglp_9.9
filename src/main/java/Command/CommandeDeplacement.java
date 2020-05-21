package Command;

import DAO.*;
import Formes.*;

import java.sql.SQLException;
import java.util.Map;
// MARCHE
public class CommandeDeplacement implements Commande {
    String nomForme = "";
    String typeForme = "";
    Point p = null;
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public void execute() throws SQLException, FormeInexistanteException, CommandeException {
        listeFormes = carreDAO.recupListeFormes();
        if(listeFormes.containsKey(nomForme)){
            typeForme = listeFormes.get(nomForme);
            switch(typeForme) {
                case "Carre":
                    carreDAO.init();
                    Carre carreAModifier = carreDAO.find(nomForme);
                    Carre carreDeplace = new Carre(nomForme, new Point(p.x, p.y), carreAModifier.cote);
                    carreDAO.update(carreDeplace);
                    break;
                case "Cercle":
                    cercleDAO.init();
                    Cercle cercleAModifier = cercleDAO.find(nomForme);
                    Cercle cercleDeplace = new Cercle(nomForme, new Point(p.x, p.y), cercleAModifier.rayon);
                    cercleDAO.update(cercleDeplace);
                    break;
                case "Rectangle":
                    rectangleDAO.init();
                    Rectangle rectangleAModifier = rectangleDAO.find(nomForme);
                    Rectangle rectangleDeplace = new Rectangle(nomForme, new Point(p.x, p.y), rectangleAModifier.longueur, rectangleAModifier.largeur);
                    rectangleDAO.update(rectangleDeplace);
                    break;
                case "Triangle":
                    triangleDAO.init();
                    Triangle triangleAModifier = triangleDAO.find(nomForme);
                    triangleAModifier.deplacer(p);
                    triangleDAO.update(triangleAModifier);
                    break;
            }
        }
        else throw new FormeInexistanteException("La forme " + nomForme + " n'existe pas");
    }

    @Override
    public void recupDonnees(String donnees) throws CommandeException {
        try{
            donnees = donnees.replace(donnees.split("\\(")[0],"").replaceFirst("\\(", "");
            nomForme = donnees.split(",")[0];
            String x = donnees.split(",")[1].replace("(", "");
            String y = donnees.split(",")[2].replace(")", "");
            p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandeException("La commande n'est pas correctement entree (ex: move(nomForme,(CordX,CordY))");
        }
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
