package Formes;

public class Cercle extends Forme{

    public int rayon;

    public Cercle(String nom, Point p, int rayon){
        super(nom, p);
        this.rayon = rayon;
    }

    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return("Formes.Cercle(centre=" + p.toString() + ", rayon=" + rayon + ")");
    }
}
