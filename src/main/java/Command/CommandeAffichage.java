package Command;

import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeAffichage implements Commande {
    @Override
    public void execute() {
        System.out.println("affichage");
    }

    @Override
    public void recupDonnees(String donnees) {
        System.out.println(donnees);
    }

    @Override
    public void print() throws SQLException, FormeInexistanteException {

    }
}
