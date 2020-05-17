package Command;


import DAO.*;
import Formes.*;

import java.sql.SQLException;
import java.util.List;

public class CommandeCreation implements Commande {
    String nomForme = "";
    String typeForme = "";
    String donneesForme = "";

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();
    private List<String> listeFormes;

    @Override
    public void execute() throws FormeDejaExistenteException, SQLException, CommandeException, NomDejaUtiliseException {
        listeFormes = carreDAO.recupListeFormes();
        String point1X;
        String point1Y;
        String point2X;
        String point2Y;
        String point3X;
        String point3Y;
        if(listeFormes.contains(nomForme)) throw new NomDejaUtiliseException();
        else{
            switch(typeForme){
                case "Carre":
                    point1X = donneesForme.split("\\)")[0].replace("((", "").split(",")[0];
                    point1Y = donneesForme.split("\\)")[0].replace("((", "").split(",")[1];
                    String cote = donneesForme.split("\\)")[1].replace(",", "");
                    Carre carre = new Carre(nomForme, new Point(Integer.parseInt(point1X), Integer.parseInt(point1Y)), Integer.parseInt(cote));
                    carreDAO.init();
                    carreDAO.create(carre);
                    break;
                case "Cercle":
                    point1X = donneesForme.split("\\)")[0].replace("((", "").split(",")[0];
                    point1Y = donneesForme.split("\\)")[0].replace("((", "").split(",")[1];
                    String rayon = donneesForme.split("\\)")[1].replace(",", "");
                    Cercle cercle = new Cercle(nomForme, new Point(Integer.parseInt(point1X), Integer.parseInt(point1Y)), Integer.parseInt(rayon));
                    cercleDAO.init();
                    cercleDAO.create(cercle);
                    break;
                case "Rectangle":
                    point1X = donneesForme.split("\\)")[0].replace("((", "").split(",")[0];
                    point1Y = donneesForme.split("\\)")[0].replace("((", "").split(",")[1];
                    String longueur = donneesForme.split("\\)")[1].replaceFirst(",", "").split(",")[0];
                    String largeur = donneesForme.split("\\)")[1].replaceFirst(",", "").split(",")[1];
                    Rectangle rectangle = new Rectangle(nomForme, new Point(Integer.parseInt(point1X), Integer.parseInt(point1Y)), Integer.parseInt(longueur), Integer.parseInt(largeur));
                    rectangleDAO.init();
                    rectangleDAO.create(rectangle);
                    break;
                case "Triangle":
                    String point1 = donneesForme.split("\\)")[0].replaceAll("\\(", "");
                    String point2 = donneesForme.split("\\)")[1].replaceAll("\\(", "").replaceFirst(",", "");
                    String point3 = donneesForme.split("\\)")[2].replaceAll("\\(", "").replaceFirst(",", "");
                    point1X = point1.split(",")[0];
                    point1Y = point1.split(",")[1];
                    point2X = point2.split(",")[0];
                    point2Y = point2.split(",")[1];
                    point3X = point3.split(",")[0];
                    point3Y = point3.split(",")[1];
                    Triangle triangle = new Triangle(nomForme, new Point(Integer.parseInt(point1X), Integer.parseInt(point1Y)), new Point(Integer.parseInt(point2X), Integer.parseInt(point2Y)), new Point(Integer.parseInt(point3X), Integer.parseInt(point3Y)));
                    triangleDAO.init();
                    triangleDAO.create(triangle);
                    break;
                default:
                    throw new CommandeException("La forme entree n'est pas reconnue !");
            }
        }
    }

    @Override
    public void recupDonnees(String donnees) {
        nomForme = donnees.split("=")[0];
        donneesForme = donnees.split("=")[1];
        typeForme = donneesForme.split("\\(")[0];
        donneesForme = donneesForme.replace(donneesForme.split("\\(")[0], "");
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {
        Forme recupForme = null;
        switch(typeForme){
            case "Carre":
                recupForme = carreDAO.find(nomForme);
                System.out.println("Forme cree: " + recupForme.toString());
                break;
            case "Cercle":
                recupForme = cercleDAO.find(nomForme);
                System.out.println("Forme cree: " + recupForme.toString());
                break;
            case "Rectangle":
                recupForme = rectangleDAO.find(nomForme);
                System.out.println("Forme cree: " + recupForme.toString());
                break;
            case "Triangle":
                recupForme = triangleDAO.find(nomForme);
                System.out.println("Forme cree: " + recupForme.toString());
                break;
        }
        recupForme.toString();
    }
    //      c1=Carre((1,10),30)
    //      c1=Cercle((1,10),30)
    //      r1=Rectangle((1,10),30,40)
    //      t1=Triangle((1,10),(2,5),(3,7))
}
