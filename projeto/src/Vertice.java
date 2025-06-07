public class Vertice {

    private char id;
    private double x;
    private double y;
    private double z;

    public Vertice(char id, double x, double y, double z) {

        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}

