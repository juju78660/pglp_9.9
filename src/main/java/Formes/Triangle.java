package Formes;

public class Triangle extends Forme{

    public Point sommet1;
    public Point sommet2;
    public Point sommet3;

    public Triangle(String nom, Point sommet1, Point sommet2, Point sommet3) {
        super(nom);
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.sommet3 = sommet3;
    }

    /* CE DEPLACEMENT EST UN PEU PARTICULIER:
     * POUR CHAQUE SOMMET DU TRIANGLE,
     * ON AJOUTE LA VALEUR X DU SOMMET P A LA VALEUR X DE CE SOMMET
     * ON AJOUTE LA VALEUR Y DU SOMMET P A LA VALEUR Y DE CE SOMMET
     */
    public void deplacer(Point p) {
        int decalageX = p.getX();
        int decalageY = p.getY();

        int sommet1x = sommet1.getX();
        int sommet1y = sommet1.getY();
        int sommet2x = sommet2.getX();
        int sommet2y = sommet2.getY();
        int sommet3x = sommet3.getX();
        int sommet3y = sommet3.getY();

        sommet1.setX(sommet1x + decalageX);
        sommet1.setY(sommet1y + decalageY);
        sommet2.setX(sommet2x + decalageX);
        sommet2.setY(sommet2y + decalageY);
        sommet3.setX(sommet3x + decalageX);
        sommet3.setY(sommet3y + decalageY);
    }

    public String toString(){
        return("Formes.Triangle(sommet 1=" + sommet1+ ", sommet 2=" + sommet2+ ", sommet 3=" + sommet3 + ")");
    }
}
