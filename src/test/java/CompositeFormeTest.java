import Formes.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeFormeTest {

    @Test
    public void testCompositeFormes() throws CompositeFormeVideException {
        Forme forme1 = new Cercle("Formes.Cercle", new Point(3, 5), 10);
        Forme forme2 = new Carre("Formes.Carre", new Point(1, 2), 10);

        CompositeForme c1 = new CompositeForme("test");
        c1.ajouter(forme1);
        c1.ajouter(forme2);
        assertEquals(c1.toString(),"Formes.Cercle(centre=(3,5), rayon=10),Formes.Carre(position=(1,2), cote=10)");
    }

    @Test
    public void testCompositeFormesDeplacement() throws CompositeFormeVideException {
        Forme forme1 = new Cercle("Formes.Cercle", new Point(3, 5), 10);
        Forme forme2 = new Carre("Formes.Carre", new Point(1, 2), 10);

        CompositeForme c1 = new CompositeForme("test");
        c1.ajouter(forme1);
        c1.ajouter(forme2);
        c1.deplacer(new Point(8, 8));
        assertEquals(c1.toString(),"Formes.Cercle(centre=(8,8), rayon=10),Formes.Carre(position=(8,8), cote=10)");
    }

    @Test (expected = CompositeFormeVideException.class)
    public void testCompositeFormesVideDeplacer() throws CompositeFormeVideException {
        CompositeForme c1 = new CompositeForme("test");
        c1.deplacer(new Point(8, 8));
    }

    @Test
    public void testCompositeFormesVideToString() throws CompositeFormeVideException {
        CompositeForme c1 = new CompositeForme("test");
        assertEquals(null, c1.toString());
    }
}
