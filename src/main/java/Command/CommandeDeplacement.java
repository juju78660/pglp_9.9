package Command;

import DAO.*;
import Formes.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CommandeDeplacement implements Commande {
    String nomForme = "";
    String typeForme = "";
    Point p = null;
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();
    CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();

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
                case "Composite":
                    compositeFormeDAO.init();
                    HashMap<String, Forme> listeFormesDAO = compositeFormeDAO.recupListeFormesPourDeplacement(nomForme);
                    Set cles = listeFormesDAO.keySet();
                    Iterator it = cles.iterator();
                    while (it.hasNext()) {
                        String cle = (String)it.next();
                        String nomFormeTemp = cle;
                        typeForme = listeFormes.get(cle);
                        switch (typeForme) {
                            case "Carre":
                                carreDAO.init();
                                Carre carreAModifier2 = carreDAO.find(nomFormeTemp);
                                Carre carreDeplace2 = new Carre(nomFormeTemp, new Point(p.x, p.y), carreAModifier2.cote);
                                carreDAO.update(carreDeplace2);
                                break;
                            case "Cercle":
                                cercleDAO.init();
                                Cercle cercleAModifier2 = cercleDAO.find(nomFormeTemp);
                                Cercle cercleDeplace2 = new Cercle(nomFormeTemp, new Point(p.x, p.y), cercleAModifier2.rayon);
                                cercleDAO.update(cercleDeplace2);
                                break;
                            case "Rectangle":
                                rectangleDAO.init();
                                Rectangle rectangleAModifier2 = rectangleDAO.find(nomFormeTemp);
                                Rectangle rectangleDeplace2 = new Rectangle(nomFormeTemp, new Point(p.x, p.y), rectangleAModifier2.longueur, rectangleAModifier2.largeur);
                                rectangleDAO.update(rectangleDeplace2);
                                break;
                            case "Triangle":
                                triangleDAO.init();
                                Triangle triangleAModifier2 = triangleDAO.find(nomFormeTemp);
                                triangleAModifier2.deplacer(p);
                                triangleDAO.update(triangleAModifier2);
                                break;
                        }
                        typeForme = "Composite";
                    }
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
        System.out.println(typeForme);
        System.out.println(nomForme);
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
            case "Composite":
                break;
        }
    }
}
