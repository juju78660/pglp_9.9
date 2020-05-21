package Command;

import DAO.*;
import Formes.CompositeForme;

import java.sql.SQLException;
import java.util.Map;

public class CommandeCreationGroupe implements Commande {

    String nomGroupe = "";
    Map<String, String> listeFormes;

    CarreDAO carreDAO = new CarreDAO();

    @Override
    public void execute() throws SQLException, NomDejaUtiliseException {
        listeFormes = carreDAO.recupListeFormes();
        if(listeFormes.containsKey(nomGroupe)) throw new NomDejaUtiliseException();
        else{
            CompositeForme c = new CompositeForme(nomGroupe);
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
    public void print() throws SQLException, FormeInexistanteException {

    }
}
