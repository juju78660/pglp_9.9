import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CercleTest {
    @Test
    public void testForme(){
        Forme forme = new Cercle("Cercle", new Point(3, 5), 10);
        assertEquals(forme.toString(), "Cercle(centre=(3,5), rayon=10)");
    }

    @Test
    public void testFormeDeplacement(){
        Forme forme = new Cercle("Cercle", new Point(3, 5), 10);
        forme.deplacer(new Point(4, 4));
        assertEquals(forme.toString(), "Cercle(centre=(4,4), rayon=10)");
    }

    @Test
    public void testFormeDeplacementNegatif(){
        Forme forme = new Cercle("Cercle", new Point(3, 5), 10);
        forme.deplacer(new Point(-2, -1));
        assertEquals(forme.toString(), "Cercle(centre=(-2,-1), rayon=10)");
    }
}
