package Command;

import DAO.*;
import Formes.CompositeForme;
import Formes.CompositeFormeVideException;
import Formes.Forme;

import java.sql.SQLException;
import java.util.Map;

public class CommandeAjoutFormeGroupe implements Commande {
    String nomForme = "";
    String nomComposite = "";
    String typeForme = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();
    CercleDAO cercleDAO = new CercleDAO();
    RectangleDAO rectangleDAO = new RectangleDAO();
    TriangleDAO triangleDAO = new TriangleDAO();
    CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();

    @Override
    public void execute() throws SQLException, FormeInexistanteException {
        listeFormes = compositeFormeDAO.recupListeFormes();
        if(listeFormes.containsKey(nomForme)){
            compositeFormeDAO.init();
            CompositeForme compositeForme = compositeFormeDAO.find(nomComposite);
            typeForme = listeFormes.get(nomForme);
            Forme recupForme = null;
            switch(typeForme){
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
            compositeForme.setFormeAUpdate(recupForme);
            compositeForme.setNomTableForme(typeForme);
            compositeForme.setTypeUpdate(1);

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
        else throw new FormeInexistanteException("La forme " + nomForme + " n'existe pas");
    }

    @Override
    public void recupDonnees(String donnees) throws CommandeException {
        try{
            donnees = donnees.replace(donnees.split("\\(")[0],"").replace("\\(", "").replace(")","");
            nomForme = donnees.split(",")[0].replaceAll("\\(", "");
            nomComposite = donnees.split(",")[1];
        } catch (Exception e) {
            throw new CommandeException("La commande n'est pas correctement entrée");
        }
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {
        CompositeForme compositeForme = compositeFormeDAO.find(nomComposite);
        System.out.println(compositeForme);
    }
}