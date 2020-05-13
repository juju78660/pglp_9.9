public abstract class Forme {
    Point p;
    String nom;

    Forme(String nom, Point p){
        this.nom = nom;
        this.p = p;
    }

    //
    Forme(String nom) {
        this.nom = nom;
    }

    public void deplacer(Point p) {
        this.p = p;
    }
    public abstract String toString();
}
