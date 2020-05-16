package Formes;

public class Carre extends Forme{

    public int cote;

    public Carre(String nom, Point p, int cote) {
        super(nom, p);
        this.cote = cote;
    }

    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return ("Formes.Carre(position=" + p.toString() + ", cote=" + cote + ")");
    }
}

