
import Formes.Carre;
import Formes.CompositeFormeVideException;
import Formes.Forme;
import Formes.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarreTest {

    @Test
    public void testForme(){
        Forme forme = new Carre("Carre", new Point(1, 2), 10);
        assertEquals(forme.toString(), "Carre: Carre(position=(1,2), cote=10)");
    }

    @Test
    public void testFormeDeplacement() throws CompositeFormeVideException {
        Forme forme = new Carre("Carre", new Point(1, 2), 10);
        forme.deplacer(new Point(4, 4));
        assertEquals(forme.toString(), "Carre: Carre(position=(4,4), cote=10)");
    }

    @Test
    public void testFormeDeplacementNegatif() throws CompositeFormeVideException {
        Forme forme = new Carre("Carre", new Point(1, 2), 10);
        forme.deplacer(new Point(-2, -1));
        assertEquals(forme.toString(), "Carre: Carre(position=(-2,-1), cote=10)");
    }
}
