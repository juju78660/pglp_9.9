public class Triangle extends Forme{

    Point sommet1;
    Point sommet2;
    Point sommet3;

    public Triangle(String nom, Point sommet1, Point sommet2, Point sommet3) {
        super(nom);
        this.sommet1 = sommet1;
        this.sommet2 = sommet2;
        this.sommet3 = sommet3;
    }
    public void deplacer(Point p) {
        this.p = p;
    }

    public String toString(){
        return ("Triangle(sommet 1=" + sommet1+ ", sommet 2=" + sommet2+ ", sommet 3=" + sommet3 + ")");
    }
}
