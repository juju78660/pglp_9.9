import DAO.CercleDAO;
import DAO.DAO;
import DAO.FormeDejaExistenteException;
import Formes.Cercle;
import Formes.Point;
import org.junit.Test;

import java.sql.SQLException;
import DAO.FormeInexistanteException;
import DAO.FormeDejaExistenteException;

import static org.junit.Assert.assertEquals;

public class CercleDAOTest {
    DAO<Cercle> cercleDAO = new CercleDAO();

    @Test
    public void testCercleDAOCreate(){
        Cercle forme = new Cercle("CercleTest", new Point(1, 2), 10);
        // CREATE
        try {
            cercleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Cercle recupForme1 = null;
        try {
            recupForme1 = cercleDAO.find("CercleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(forme.toString(), recupForme1.toString());
    }

    @Test
    public void testCercleDAOUpdate(){
        Cercle forme = new Cercle("CercleTest", new Point(1, 2), 10);
        DAO<Cercle> cercleDAO = new CercleDAO();
        // CREATE
        try {
            cercleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // UPDATE
        Cercle updateForme1 = new Cercle("CercleTest", new Point(10, 20), 10);
        try {
            cercleDAO.update(updateForme1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Cercle recupForme1Update = null;
        try {
            recupForme1Update = cercleDAO.find("CercleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(updateForme1.toString(), recupForme1Update.toString());
    }

    @Test (expected = FormeInexistanteException.class)
    public void testCercleDAODelete() throws SQLException, FormeInexistanteException {
        Cercle forme = new Cercle("CercleTest", new Point(1, 2), 10);
        // CREATE
        try {
            cercleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            cercleDAO.delete("CercleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Cercle recupForme1Update = null;
        recupForme1Update = cercleDAO.find("CercleTest");

        assertEquals(recupForme1Update, null);
    }

    @Test (expected = FormeDejaExistenteException.class)
    public void testCercleDAOCreateDejaExistant() throws FormeDejaExistenteException {
        Cercle forme = new Cercle("CercleTest", new Point(1, 2), 10);
        // CREATE
        try {
            cercleDAO.create(forme);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cercle forme2 = new Cercle("CercleTest", new Point(5, 8), 100);
        // CREATE
        try {
            cercleDAO.create(forme2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = FormeInexistanteException.class)
    public void testCercleDAOFindInexistant() throws FormeInexistanteException, SQLException {
        // FIND
        Cercle recupForme1Update = null;
        recupForme1Update = cercleDAO.find("CercleTest");

    }

    @Test (expected = FormeInexistanteException.class)
    public void testCercleDAOUpdateInexistant() throws FormeInexistanteException, SQLException {
        // FIND
        Cercle forme = new Cercle("CercleTest", new Point(1, 2), 10);
        cercleDAO.update(forme);
    }
}