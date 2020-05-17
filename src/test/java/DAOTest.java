import DAO.CarreDAO;
import DAO.DAO;
import DAO.FormeInexistanteException;
import Formes.Carre;
import Formes.Point;
import org.junit.Test;


import java.sql.SQLException;
import DAO.FormeDejaExistenteException;

import static org.junit.Assert.assertEquals;

public class DAOTest {

    @Test (expected = FormeInexistanteException.class)
    public void testDAOInit() throws FormeInexistanteException, SQLException {
        DAO<Carre> carreDAO = new CarreDAO();
        carreDAO.initBD();
        carreDAO.init();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (SQLException | FormeDejaExistenteException e) {
            e.printStackTrace();
        }

        carreDAO.initBD();
        Carre recupForme1 = null;
        recupForme1 = carreDAO.find("CarreTest");

        assertEquals(recupForme1, null);
    }
}
