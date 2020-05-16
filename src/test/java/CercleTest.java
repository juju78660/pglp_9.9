import Formes.Cercle;
import Formes.CompositeFormeVideException;
import Formes.Forme;
import Formes.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CercleTest {
    @Test
    public void testForme(){
        Forme forme = new Cercle("Formes.Cercle", new Point(3, 5), 10);
        assertEquals(forme.toString(), "Formes.Cercle(centre=(3,5), rayon=10)");
    }

    @Test
    public void testFormeDeplacement() throws CompositeFormeVideException {
        Forme forme = new Cercle("Formes.Cercle", new Point(3, 5), 10);
        forme.deplacer(new Point(4, 4));
        assertEquals(forme.toString(), "Formes.Cercle(centre=(4,4), rayon=10)");
    }

    @Test
    public void testFormeDeplacementNegatif() throws CompositeFormeVideException {
        Forme forme = new Cercle("Formes.Cercle", new Point(3, 5), 10);
        forme.deplacer(new Point(-2, -1));
        assertEquals(forme.toString(), "Formes.Cercle(centre=(-2,-1), rayon=10)");
    }
}
