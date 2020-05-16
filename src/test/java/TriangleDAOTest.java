import DAO.DAO;
import Formes.Point;
import Formes.Triangle;
import org.junit.Test;

import java.sql.SQLException;
import DAO.TriangleDAO;
import DAO.FormeInexistanteException;
import DAO.FormeDejaExistenteException;

import static org.junit.Assert.assertEquals;

public class TriangleDAOTest {
    DAO<Triangle> triangleDAO = new TriangleDAO();

    @Test
    public void testTriangleDAOCreate(){
        Triangle forme = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        // CREATE
        try {
            triangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FIND
        Triangle recupForme1 = null;
        try {
            recupForme1 = triangleDAO.find("TriangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(forme.toString(), recupForme1.toString());
    }

    @Test
    public void testTriangleDAOUpdate(){
        Triangle forme = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        DAO<Triangle> triangleDAO = new TriangleDAO();
        // CREATE
        try {
            triangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // UPDATE
        Triangle updateForme1 = new Triangle("TriangleTest", new Point(10, 20), new Point(30, 40),new Point(50, 60));
        try {
            triangleDAO.update(updateForme1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Triangle recupForme1Update = null;
        try {
            recupForme1Update = triangleDAO.find("TriangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(updateForme1.toString(), recupForme1Update.toString());
    }

    @Test (expected = FormeInexistanteException.class)
    public void testTriangleDAODelete() throws SQLException, FormeInexistanteException {
        Triangle forme = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        DAO<Triangle> triangleDAO = new TriangleDAO();
        // CREATE
        try {
            triangleDAO.create(forme);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DELETE
        try {
            triangleDAO.delete("TriangleTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        Triangle recupForme1Update = null;

        recupForme1Update = triangleDAO.find("TriangleTest");

        assertEquals(recupForme1Update, null);
    }

    @Test (expected = FormeDejaExistenteException.class)
    public void testCarreDAOCreateDejaExistant() throws FormeDejaExistenteException {
        Triangle forme = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        // CREATE
        try {
            triangleDAO.create(forme);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Triangle forme2 = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        // CREATE
        try {
            triangleDAO.create(forme2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = FormeInexistanteException.class)
    public void testCercleDAOFindInexistant() throws FormeInexistanteException, SQLException {
        // FIND
        Triangle recupForme1Update = null;
        recupForme1Update = triangleDAO.find("TriangleTest");

    }

    @Test (expected = FormeInexistanteException.class)
    public void testCercleDAOUpdateInexistant() throws FormeInexistanteException, SQLException {
        // FIND
        Triangle forme = new Triangle("TriangleTest", new Point(1, 2), new Point(3, 4),new Point(5, 6));
        triangleDAO.update(forme);
    }
}
