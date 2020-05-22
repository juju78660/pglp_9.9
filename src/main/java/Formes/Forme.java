package Formes;

public abstract class Forme {
    /**
     * Point du sommet de la forme
     */
    public Point p;
    /**
     * Nom de la forme
     */
    public String nom;

    /**
     * Instancie une nouvelle forme avec ses paramètres
     *
     * @param nom nom
     * @param p   sommet
     */
    Forme(String nom, Point p){
        this.nom = nom;
        this.p = p;
    }

    /**
     * Instancie une nouvelle forme avec ses paramètres
     *
     * @param nom nom
     */
    Forme(String nom) {
        this.nom = nom;
    }

    /**
     * Deplace une forme
     *
     * @param p point de destination
     * @throws CompositeFormeVideException the composite forme vide exception
     */
    public abstract void deplacer(Point p) throws CompositeFormeVideException;
    public abstract String toString();
}
