package Command;

import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeSuppression implements Commande {
    @Override
    public void execute() {
        System.out.println("suppression");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
