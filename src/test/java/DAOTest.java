import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DAOTest {

    @Test (expected = FormeInexistanteException.class)
    public void testDAOInit() throws FormeInexistanteException, SQLException {
        DAO<Carre> carreDAO = new CarreDAO();
        Carre forme = new Carre("CarreTest", new Point(1, 2), 10);
        // CREATE
        try {
            carreDAO.create(forme);
        } catch (SQLException | FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        DAO<Carre> carreDAO2 = new CarreDAO();
        Carre recupForme1 = null;
        recupForme1 = carreDAO2.find("CarreTest");


        assertEquals(recupForme1, null);
    }
}
