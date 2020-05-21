package Command;

import DAO.*;

import java.sql.SQLException;
import java.util.Map;

public class CommandeAjoutFormeGroupe implements Commande {
    String nomForme = "";
    String typeForme = "";
    String donneesForme = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public void execute() {
        System.out.println("ajout forme groupe");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
