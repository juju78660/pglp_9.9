package Command;

import DAO.*;
import Formes.*;

import java.sql.SQLException;
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
            typeForme = listeFormes.get(nomForme);
            listeFormes.remove(nomForme);
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
    public void print() throws SQLException, FormeInexistanteException {
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
