package Formes;

public class Rectangle extends Forme {

    public int longueur;
    public int largeur;

    public Rectangle(String nom, Point p, int longueur, int largeur) {
        super(nom, p);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return(nom + ": Rectangle(position=" + p.toString() + ", longueur=" + longueur + ", largeur=" + largeur + ")");
    }
}
