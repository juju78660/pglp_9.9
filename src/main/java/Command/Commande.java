package Command;

import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;

import java.sql.SQLException;

public interface Commande {
    public void execute() throws FormeDejaExistenteException, SQLException, CommandeException, NomDejaUtiliseException, FormeInexistanteException;
    public void recupDonnees(String s) throws CommandeException;

    void print() throws SQLException, FormeInexistanteException, CommandeException;
}
