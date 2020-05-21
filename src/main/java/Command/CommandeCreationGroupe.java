package Command;

import DAO.*;
import Formes.CompositeForme;

import java.sql.SQLException;
import java.util.Map;

public class CommandeCreationGroupe implements Commande {

    String nomGroupe = "";
    Map<String, String> listeFormes;

    CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();

    @Override
    public void execute() throws SQLException, NomDejaUtiliseException, FormeDejaExistenteException {
        listeFormes = compositeFormeDAO.recupListeFormes();
        if(listeFormes.containsKey(nomGroupe)) throw new NomDejaUtiliseException();
        else{
            CompositeForme compositeForme = new CompositeForme(nomGroupe);
            listeFormes.put(nomGroupe, "Composite");
            compositeFormeDAO.init();
            compositeFormeDAO.create(compositeForme);
        }
    }

    @Override
    public void recupDonnees(String donnees) throws CommandeException {
        try{
            nomGroupe = donnees.split("\\(")[1].replaceAll("\\)", "");
        } catch (Exception e) {
            throw new CommandeException("La commande n'est pas correctement entrée");
        }
    }

    @Override
    public void print(){
        System.out.println("Le composite " + nomGroupe + " a ete cree");
    }
}