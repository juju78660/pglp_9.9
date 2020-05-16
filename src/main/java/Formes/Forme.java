package Formes;

public abstract class Forme {
    public Point p;
    public String nom;

    Forme(String nom, Point p){
        this.nom = nom;
        this.p = p;
    }

    Forme(String nom) {
        this.nom = nom;
    }

    public abstract void deplacer(Point p) throws CompositeFormeVideException;
    public abstract String toString();
}
