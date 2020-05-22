package Command;

import DAO.*;
import Formes.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeAffichageListeFormes implements Commande {
    @Override
    public void execute() throws SQLException, FormeInexistanteException {
        CarreDAO carreDAO = new CarreDAO();
        carreDAO.init();
        ArrayList<Carre> listeCarres =  carreDAO.recuperationDonnees();
        System.out.println("Carres:");
        if(listeCarres.size()>0){
            for (Carre c : listeCarres) {
                System.out.println("    " + c);
            }
        }
        else System.out.println("   Vide");

        CercleDAO cercleDAO = new CercleDAO();
        cercleDAO.init();
        ArrayList<Cercle> listeCercles =  cercleDAO.recuperationDonnees();
        System.out.println("Cercles:");
        if(listeCercles.size()>0){
            for (Cercle c : listeCercles) {
                System.out.println("    " + c);
            }
        }
        else System.out.println("   Vide");

        RectangleDAO rectangleDAO = new RectangleDAO();
        rectangleDAO.init();
        ArrayList<Rectangle> listeRectangle =  rectangleDAO.recuperationDonnees();
        System.out.println("Rectangles:");
        if(listeRectangle.size()>0){
            for (Rectangle r : listeRectangle) {
                System.out.println("    " + r);
            }
        }
        else System.out.println("   Vide");
        TriangleDAO triangleDAO = new TriangleDAO();
        triangleDAO.init();
        ArrayList<Triangle> listeTriangle =  triangleDAO.recuperationDonnees();
        System.out.println("Triangles:");
        if(listeTriangle.size()>0){
            for (Triangle t : listeTriangle) {
                System.out.println("    " + t);
            }
        }
        else System.out.println("   Vide");
        CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();
        compositeFormeDAO.init();
        ArrayList<CompositeForme> listeComposites = null;
        try {
            listeComposites = compositeFormeDAO.recuperationDonnees();
        } catch (CompositeFormeContientDejaException e) {
            e.printStackTrace();
        }
        System.out.println("Composites:");
        if(listeComposites.size()>0){
            for (CompositeForme c : listeComposites) {
                Forme recupForme = null;
                recupForme = compositeFormeDAO.find(c.nom);
                System.out.println("    " + recupForme.toString());
            }
        }
        else System.out.println("   Vide");
    }

    @Override
    public void recupDonnees(String s){}

    @Override
    public void print() { }
}
