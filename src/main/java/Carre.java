public class Carre extends Forme{

    int cote;

    public Carre(String nom, Point p, int cote) {
        super(nom, p);
        this.cote = cote;
    }

    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return ("Carre(position=" + p.toString() + ", cote=" + cote + ")");
    }
}

