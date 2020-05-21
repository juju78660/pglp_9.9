package Command;

import DAO.*;

import java.sql.SQLException;
import java.util.Map;

public class CommandeSuppressionGroupe implements Commande {
    String nomForme = "";
    String typeForme = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();

    @Override
    public void execute() throws FormeInexistanteException, SQLException {
        System.out.println("supp groupe");
        listeFormes = carreDAO.recupListeFormes();
        if(listeFormes.containsKey(nomForme)){
            listeFormes.remove(nomForme);
        }
        else throw new FormeInexistanteException("La forme " + nomForme + " n'existe pas");
        //compo.init();
        //carreDAO.delete(nomForme);
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
