package Command;

import DAO.*;
import java.sql.SQLException;
import java.util.Map;

public class CommandeSuppressionGroupe implements Commande {
    String nomGroupe = "";
    Map<String, String> listeFormes;

    CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();

    @Override
    public void execute() throws FormeInexistanteException, SQLException {
        listeFormes = compositeFormeDAO.recupListeFormes();
        if(listeFormes.containsKey(nomGroupe)){
            listeFormes.remove(nomGroupe);
            compositeFormeDAO.init();
            compositeFormeDAO.delete(nomGroupe);
        }
        else throw new FormeInexistanteException("Le composite " + nomGroupe + " n'existe pas");
    }

    @Override
    public void recupDonnees(String donnees) throws CommandeException {
        try{
            nomGroupe = donnees.split("\\(")[1].replace(")", "");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandeException("La commande n'est pas correctement entree (ex: deleteGroup(nomGroupe)");
        }
    }

    @Override
    public void print(){
        System.out.println("Le composite " + nomGroupe + " a ete supprimee");
    }
}
