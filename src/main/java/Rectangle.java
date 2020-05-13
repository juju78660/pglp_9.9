public class Rectangle extends Forme {

    int longueur;
    int largeur;

    public Rectangle(String nom, Point p, int longueur, int largeur) {
        super(nom, p);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return ("Rectangle(position=" + p.toString() + ", longueur=" + longueur + ", largeur=" + largeur + ")");
    }
}
