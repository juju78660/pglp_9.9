package Command;


import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeStop implements Commande {
    @Override
    public void execute() {
        System.out.println("STOPPING THE PROGRAM");
        System.exit(1);
    }

    @Override
    public void recupDonnees(String donnees) { }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
