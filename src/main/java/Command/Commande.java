package Command;

import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;

import java.sql.SQLException;

public interface Commande {
    public void execute() throws FormeDejaExistenteException, SQLException, CommandeException, NomDejaUtiliseException;
    public void recupDonnees(String s);

    void print() throws SQLException, FormeInexistanteException;
}
