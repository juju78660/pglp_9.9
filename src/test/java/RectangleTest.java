import Formes.CompositeFormeVideException;
import Formes.Forme;
import Formes.Point;
import Formes.Rectangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    @Test
    public void testForme(){
        Forme forme = new Rectangle("Rectangle", new Point(5, 6), 10, 15);
        assertEquals(forme.toString(), "Rectangle: Rectangle(position=(5,6), longueur=10, largeur=15)");
    }

    @Test
    public void testFormeDeplacement() throws CompositeFormeVideException {
        Forme forme = new Rectangle("Rectangle", new Point(5, 6), 10, 15);
        forme.deplacer(new Point(4, 4));
        assertEquals(forme.toString(), "Rectangle: Rectangle(position=(4,4), longueur=10, largeur=15)");
    }

    @Test
    public void testFormeDeplacementNegatif() throws CompositeFormeVideException {
        Forme forme = new Rectangle("Rectangle", new Point(5, 6), 10, 15);
        forme.deplacer(new Point(-2, -1));
        assertEquals(forme.toString(), "Rectangle: Rectangle(position=(-2,-1), longueur=10, largeur=15)");
    }
}
