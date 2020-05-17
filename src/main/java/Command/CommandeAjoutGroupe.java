package Command;

import Command.Commande;
import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeAjoutGroupe implements Commande {
    @Override
    public void execute() {
        System.out.println("ajout groupe");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
