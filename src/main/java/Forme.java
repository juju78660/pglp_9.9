public abstract class Forme {
    Point p;
    String nom;

    Forme(String nom, Point p){
        this.nom = nom;
        this.p = p;
    }

    Forme(String nom) {
        this.nom = nom;
    }

    public abstract void deplacer(Point p);
    public abstract String toString();
}
