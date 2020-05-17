import DAO.DAO;
import DAO.FormeDejaExistenteException;
import DAO.FormeInexistanteException;
import Formes.Point;
import Formes.Rectangle;
import org.junit.Test;

import java.sql.SQLException;
import DAO.RectangleDAO;

import static org.junit.Assert.assertEquals;

public class RectangleDAOTest {
    DAO<Rectangle> rectangleDAO = new RectangleDAO();

    @Test
    public void testRectangleDAOCreate() throws SQLException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        Rectangle forme = new Rectangle("RectangleTest", new Point(1, 2), 10, 20);
        // CREATE
        try {
            rectangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Rectangle recupForme1 = null;
        try {
            recupForme1 = rectangleDAO.find("RectangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(forme.toString(), recupForme1.toString());
    }

    @Test
    public void testRectangleDAOUpdate() throws SQLException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        Rectangle forme = new Rectangle("RectangleTest", new Point(1, 2), 10, 20);
        // CREATE
        try {
            rectangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // UPDATE
        Rectangle updateForme1 = new Rectangle("RectangleTest", new Point(10, 20), 100, 200);
        try {
            rectangleDAO.update(updateForme1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Rectangle recupForme1Update = null;
        try {
            recupForme1Update = rectangleDAO.find("RectangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(updateForme1.toString(), recupForme1Update.toString());
    }

    @Test (expected = FormeInexistanteException.class)
    public void testRectangleDAODelete() throws SQLException, FormeInexistanteException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        Rectangle forme = new Rectangle("RectangleTest", new Point(1, 2), 10, 20);
        // CREATE
        try {
            rectangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            rectangleDAO.delete("RectangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Rectangle recupForme1Update = null;
        recupForme1Update = rectangleDAO.find("RectangleTest");

        assertEquals(recupForme1Update, null);
    }

    @Test (expected = FormeDejaExistenteException.class)
    public void testCarreDAOCreateDejaExistant() throws FormeDejaExistenteException, SQLException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        Rectangle forme = new Rectangle("RectangleTest", new Point(1, 2), 10, 20);
        // CREATE
        try {
            rectangleDAO.create(forme);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Rectangle forme2 = new Rectangle("RectangleTest", new Point(5, 8), 100, 200);
        // CREATE
        try {
            rectangleDAO.create(forme2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = FormeInexistanteException.class)
    public void testRectangleDAOFindInexistant() throws FormeInexistanteException, SQLException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        // FIND
        Rectangle recupForme1Update = null;
        recupForme1Update = rectangleDAO.find("RectangleTest");

    }

    @Test (expected = FormeInexistanteException.class)
    public void testRectangleDAOUpdateInexistant() throws FormeInexistanteException, SQLException {
        rectangleDAO.initBD();
        rectangleDAO.init();
        // FIND
        Rectangle forme = new Rectangle("RectangleTest", new Point(1, 2), 10, 10);
        rectangleDAO.update(forme);
    }
}
