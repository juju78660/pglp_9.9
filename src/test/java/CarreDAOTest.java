import Command.CommandeException;
import DAO.CarreDAO;
import DAO.DAO;
import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;
import Formes.Carre;
import Formes.CompositeFormeVideException;
import Formes.Point;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CarreDAOTest {

    DAO<Carre> carreDAO = new CarreDAO();

    @Test
    public void testCarreDAOCreate() throws SQLException, FormeInexistanteException {
        carreDAO.initBD();
        carreDAO.init();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Carre recupForme1 = null;
        try {
            recupForme1 = carreDAO.find("CarreTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(forme.toString(), recupForme1.toString());
    }

    @Test
    public void testCarreDAOUpdate() throws SQLException {
        carreDAO.initBD();
        carreDAO.init();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // UPDATE
        Carre updateForme1 = new Carre("CarreTest", new Point(10, 20), 10);
        try {
            carreDAO.update(updateForme1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Carre recupForme1Update = null;
        try {
            recupForme1Update = carreDAO.find("CarreTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(updateForme1.toString(), recupForme1Update.toString());
    }

    @Test (expected = FormeInexistanteException.class)
    public void testCarreDAODelete() throws SQLException, FormeInexistanteException {
        carreDAO.initBD();
        carreDAO.init();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }
        carreDAO.init();

        // DELETE
        try {
            carreDAO.delete("CarreTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Carre recupForme1Update = null;
        recupForme1Update = carreDAO.find("CarreTest");

        assertEquals(recupForme1Update, null);
    }

    @Test (expected = FormeDejaExistenteException.class)
    public void testCarreDAOCreateDejaExistant() throws FormeDejaExistenteException, SQLException, FormeInexistanteException {
        carreDAO.initBD();
        carreDAO.init();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Carre forme2 = new Carre("CarreTest", new Point(5, 8), 100);
        // CREATE
        try {
            carreDAO.create(forme2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = FormeInexistanteException.class)
    public void testCarreDAOFindInexistant() throws FormeInexistanteException, SQLException {
        carreDAO.initBD();
        carreDAO.init();
        // FIND
        Carre recupForme1Update = null;
        recupForme1Update = carreDAO.find("CarreTest");

    }

    @Test (expected = FormeInexistanteException.class)
    public void testCarreDAOUpdateInexistant() throws FormeInexistanteException, SQLException, FormeDejaExistenteException, CommandeException, CompositeFormeVideException {
        carreDAO.initBD();
        carreDAO.init();
        // FIND
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        carreDAO.update(forme);
    }
}
