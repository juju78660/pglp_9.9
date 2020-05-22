package Command;

import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;

import java.sql.SQLException;

/**
 * The interface Commande.
 */
public interface Commande {
    /**
     * Execute la commande
     *
     * @throws FormeDejaExistenteException the forme deja existente exception
     * @throws SQLException                the sql exception
     * @throws CommandeException           the commande exception
     * @throws NomDejaUtiliseException     the nom deja utilise exception
     * @throws FormeInexistanteException   the forme inexistante exception
     */
    public void execute() throws FormeDejaExistenteException, SQLException, CommandeException, NomDejaUtiliseException, FormeInexistanteException;

    /**
     * Recuperation des donnees utilisees pour le bon fonctionnement de la commande
     *
     * @param s donnees recuperees en entrees
     * @throws CommandeException the commande exception
     */
    public void recupDonnees(String s) throws CommandeException;

    /**
     * Affiche les modifications apportees par la commande
     *
     * @throws SQLException              the sql exception
     * @throws FormeInexistanteException the forme inexistante exception
     * @throws CommandeException         the commande exception
     */
    void print() throws SQLException, FormeInexistanteException, CommandeException;
}
