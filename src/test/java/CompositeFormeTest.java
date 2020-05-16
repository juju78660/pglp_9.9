import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompositeFormeTest {

    @Test
    public void testCompositeFormes(){
        Forme forme1 = new Cercle("Cercle", new Point(3, 5), 10);
        Forme forme2 = new Carre("Carre", new Point(1, 2), 10);

        CompositeForme c1 = new CompositeForme("test");
        c1.ajouter(forme1);
        c1.ajouter(forme2);
        assertEquals(c1.toString(),"Cercle(centre=(3,5), rayon=10),Carre(position=(1,2), cote=10)");
    }

    @Test
    public void testCompositeFormesDeplacement(){
        Forme forme1 = new Cercle("Cercle", new Point(3, 5), 10);
        Forme forme2 = new Carre("Carre", new Point(1, 2), 10);

        CompositeForme c1 = new CompositeForme("test");
        c1.ajouter(forme1);
        c1.ajouter(forme2);
        c1.deplacer(new Point(8, 8));
        assertEquals(c1.toString(),"Cercle(centre=(8,8), rayon=10),Carre(position=(8,8), cote=10)");
    }
}
