package Command;

import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeCreationGroupe implements Commande {
    @Override
    public void execute() {
        System.out.println("creation groupe");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
