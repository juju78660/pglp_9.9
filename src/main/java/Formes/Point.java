package Formes;

public class Point {

    /**
     * Coordonnée X
     */
    public int x;
    /**
     * Coordonnée Y
     */
    public int y;

    /**
     * Instancie un nouveau Point
     *
     * @param x Coordonnée X
     * @param y Coordonnée Y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Retourne la coordonnée X
     *
     * @return coordonnée X
     */
    public int getX(){
        return x;
    }

    /**
     * Retourne la coordonnée Y
     *
     * @return coordonnée Y
     */
    public int getY() {
        return y;
    }

    /**
     * Modifie la coordonnée X
     *
     * @return coordonnée X
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Modifie la coordonnée Y
     *
     * @return coordonnée Y
     */
    public void setY(int y) {
        this.y = y;
    }
}
