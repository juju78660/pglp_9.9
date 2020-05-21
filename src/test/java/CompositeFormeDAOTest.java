import DAO.DAO;
import DAO.FormeInexistanteException;
import DAO.FormeDejaExistenteException;
import Formes.*;
import DAO.CompositeFormeDAO;
import DAO.CarreDAO;
import DAO.CercleDAO;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CompositeFormeDAOTest {
    DAO<CompositeForme> compositeFormeDAO = new CompositeFormeDAO();
    DAO<Carre> carreDAO = new CarreDAO();
    DAO<Cercle> cercleDAO = new CercleDAO();

    @Test
    public void testCarreDAOCreate() throws SQLException{
        compositeFormeDAO.initBD();
        compositeFormeDAO.init();
        CompositeForme composite = new CompositeForme("CompositeTest");
        // CREATE
        try {
            compositeFormeDAO.create(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite = null;
        try {
            recupComposite = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(recupComposite.toString(), "CompositeTest est vide");
    }

    @Test
    public void testCarreDAOAjouterFormes() throws SQLException {
        compositeFormeDAO.initBD();
        compositeFormeDAO.init();
        CompositeForme composite = new CompositeForme("CompositeTest");
        // CREATE
        try {
            compositeFormeDAO.create(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CARRE AU COMPOSITE
        Forme carre = new Carre("Carre1", new Point(3, 4), 5);
        carreDAO.init();
        try {
            carreDAO.create((Carre) carre);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(carre);
        composite.setNomTableForme("Carre");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CERCLE AU COMPOSITE
        Forme cercle = new Cercle("Cercle1", new Point(10, 13), 7);
        cercleDAO.init();
        try {
            cercleDAO.create((Cercle) cercle);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(cercle);
        composite.setNomTableForme("Cercle");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite = null;
        try {
            recupComposite = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(recupComposite.toString(), "CompositeTest: {\n" + "    Carre1: Carre(position=(3,4), cote=5)\n" + "    Cercle1: Cercle(centre=(10,13), rayon=7)\n" + "}");
    }

    @Test
    public void testCarreDAOUpdateFormes() throws SQLException, CompositeFormeVideException {
        compositeFormeDAO.initBD();
        compositeFormeDAO.init();
        CompositeForme composite = new CompositeForme("CompositeTest");
        // CREATE
        try {
            compositeFormeDAO.create(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CARRE AU COMPOSITE
        Forme carre = new Carre("Carre1", new Point(3, 4), 5);
        carreDAO.init();
        try {
            carreDAO.create((Carre) carre);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(carre);
        composite.setNomTableForme("Carre");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // AJOUT CERCLE AU COMPOSITE
        Forme cercle = new Cercle("Cercle1", new Point(10, 13), 7);
        cercleDAO.init();
        try {
            cercleDAO.create((Cercle) cercle);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(cercle);
        composite.setNomTableForme("Cercle");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite1 = null;
        try {
            recupComposite1 = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MODIF CERCLE DU COMPOSITE
        Forme cercleAModifier = new Cercle("Cercle1", new Point(5, 6), 11);
        recupComposite1.setFormeAUpdate(cercleAModifier);
        recupComposite1.setNomTableForme("Cercle");
        recupComposite1.setTypeUpdate(2);
        try{
            compositeFormeDAO.update(recupComposite1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite2 = null;
        try {
            recupComposite2 = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(recupComposite2.toString(), "CompositeTest: {\n" + "    Carre1: Carre(position=(3,4), cote=5)\n" + "    Cercle1: Cercle(centre=(5,6), rayon=11)\n" + "}");
    }

    @Test
    public void testCarreDAODeplacerFormes() throws SQLException, CompositeFormeVideException {
        compositeFormeDAO.initBD();
        compositeFormeDAO.init();
        CompositeForme composite = new CompositeForme("CompositeTest");
        // CREATE
        try {
            compositeFormeDAO.create(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CARRE AU COMPOSITE
        Forme carre = new Carre("Carre1", new Point(3, 4), 5);
        carreDAO.init();
        try {
            carreDAO.create((Carre) carre);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(carre);
        composite.setNomTableForme("Carre");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // AJOUT CERCLE AU COMPOSITE
        Forme cercle = new Cercle("Cercle1", new Point(10, 13), 7);
        cercleDAO.init();
        try {
            cercleDAO.create((Cercle) cercle);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(cercle);
        composite.setNomTableForme("Cercle");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite1 = null;
        try {
            recupComposite1 = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MODIF FORMES DU COMPOSITE
        recupComposite1.setPointDeplacement(new Point(77,88));
        recupComposite1.setTypeUpdate(3);
        try{
            compositeFormeDAO.update(recupComposite1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // FIND
        CompositeForme recupComposite2 = null;
        try {
            recupComposite2 = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(recupComposite2);
        compositeFormeDAO.affichageTable("Composite");
        //assertEquals(recupComposite2.toString(), "CompositeTest: {\n" + "    Carre1: Carre(position=(3,4), cote=5)\n" + "    Cercle1: Cercle(centre=(5,6), rayon=11)\n" + "}");
    }

    @Test
    public void testCarreDAOEnleverFormes() throws SQLException {
        compositeFormeDAO.initBD();
        compositeFormeDAO.init();
        CompositeForme composite = new CompositeForme("CompositeTest");
        // CREATE
        try {
            compositeFormeDAO.create(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CARRE AU COMPOSITE
        Forme carre = new Carre("Carre1", new Point(3, 4), 5);
        carreDAO.init();
        try {
            carreDAO.create((Carre) carre);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }

        composite.setFormeAUpdate(carre);
        composite.setNomTableForme("Carre");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // AJOUT CERCLE AU COMPOSITE
        Forme cercle = new Cercle("Cercle1", new Point(10, 13), 7);
        cercleDAO.init();
        try {
            cercleDAO.create((Cercle) cercle);
        } catch (FormeDejaExistenteException e) {
            e.printStackTrace();
        }
        composite.setFormeAUpdate(cercle);
        composite.setNomTableForme("Cercle");
        composite.setTypeUpdate(1);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite = null;
        try {
            recupComposite = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SUPPRESSION CARRE COMPOSITE
        composite.setFormeAUpdate(carre);
        composite.setTypeUpdate(4);
        try{
            compositeFormeDAO.update(composite);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // FIND
        CompositeForme recupComposite2 = null;
        try {
            recupComposite2 = compositeFormeDAO.find("CompositeTest");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(recupComposite2.toString(), "CompositeTest: {\n" + "    Cercle1: Cercle(centre=(10,13), rayon=7)\n" + "}");
    }
}
