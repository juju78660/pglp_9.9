
import Formes.CompositeFormeVideException;
import Formes.Forme;
import Formes.Point;
import Formes.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    @Test
    public void testForme(){
        Forme forme = new Triangle("Triangle", new Point(3, 5), new Point(1, 2), new Point(7, 6));
        assertEquals(forme.toString(), "Triangle: Triangle(sommet 1=(3,5), sommet 2=(1,2), sommet 3=(7,6))");
    }

    @Test
    public void testFormeDeplacement() throws CompositeFormeVideException {
        Forme forme = new Triangle("Triangle", new Point(3, 5), new Point(1, 2), new Point(7, 6));
        forme.deplacer(new Point(2, 1));
        assertEquals(forme.toString(), "Triangle: Triangle(sommet 1=(5,6), sommet 2=(3,3), sommet 3=(9,7))");
    }

    @Test
    public void testFormeDeplacementNegatif() throws CompositeFormeVideException {
        Forme forme = new Triangle("Triangle", new Point(3, 5), new Point(1, 2), new Point(7, 6));
        forme.deplacer(new Point(-2, -3));
        assertEquals(forme.toString(), "Triangle: Triangle(sommet 1=(1,2), sommet 2=(-1,-1), sommet 3=(5,3))");
    }
}
