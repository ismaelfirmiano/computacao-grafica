import java.util.List;

public class Vetor {
    private final double x, y, z;

    public Vetor(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vetor normalizado() {
        double comprimento = Math.sqrt(x * x + y * y + z * z);
        if (comprimento == 0) return new Vetor(0, 0, 0);
        return new Vetor(x / comprimento, y / comprimento, z / comprimento);
    }

    public static Vetor calcularNormal(Face face) {

        if(face == null)
            return null;

        List<Vertice> contorno = face.getContorno();

        if (contorno.size() < 3) {
            return new Vetor(0, 0, 0);
        }

        Vertice v0 = contorno.get(0);
        Vertice v1 = contorno.get(1);
        Vertice v2 = contorno.get(2);

        double ux = v1.getX() - v0.getX();
        double uy = v1.getY() - v0.getY();
        double uz = v1.getZ() - v0.getZ();

        double vx = v2.getX() - v0.getX();
        double vy = v2.getY() - v0.getY();
        double vz = v2.getZ() - v0.getZ();

        double nx = uy * vz - uz * vy;
        double ny = uz * vx - ux * vz;
        double nz = ux * vy - uy * vx;

        return new Vetor(nx, ny, nz).normalizado();
    }

    public Vetor multiplica(double escalar) {
        return new Vetor(x * escalar, y * escalar, z * escalar);
    }

    public Vetor subtrai(Vetor outro) {
        return new Vetor(x - outro.x, y - outro.y, z - outro.z);
    }

    public Vetor soma(Vetor outro) {
         return new Vetor(x + outro.x, y + outro.y, z + outro.z);
     }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double produtoEscalar(Vetor vetor) {
        return x * vetor.getX() + y * vetor.getY() + z * vetor.getZ();
    }
}
