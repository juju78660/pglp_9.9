package Command;

import DAO.CompositeFormeDAO;
import DAO.FormeInexistanteException;

import java.sql.SQLException;

public class CommandeRemiseZeroBD implements Commande {
    @Override
    public void execute() throws SQLException {
        CompositeFormeDAO compositeFormeDAO = new CompositeFormeDAO();
        compositeFormeDAO.initBD();
    }

    @Override
    public void recupDonnees(String s) {}

    @Override
    public void print() throws SQLException, FormeInexistanteException {
        CommandeAffichageListeFormes c = new CommandeAffichageListeFormes();
        c.execute();
    }
}
