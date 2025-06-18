import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Objeto {
    private List<Vertice> vertices;
    private List<Face> faces;

    public Objeto(List<Vertice> vertices, List<Face> faces) {
        this.vertices = vertices;
        this.faces = faces;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void multiplicaMatriz(double[][] matriz) {
        for (Vertice v : vertices) {
            double[] original = {v.getX(), v.getY(), v.getZ(), 1};
            double[] novo = new double[4];

            for (int i = 0; i < 4; i++) {
                novo[i] = 0;
                for (int j = 0; j < 4; j++) {
                    novo[i] += matriz[i][j] * original[j];
                }
            }

            v.setX(novo[0]);
            v.setY(novo[1]);
            v.setZ(novo[2]);
        }

        for (Face f : faces) {
            f.setNormal(Vetor.calcularNormal(f));
        }
    }

    public void rotacionarX(double angulo) {
        double rad = Math.toRadians(angulo);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Vertice p : vertices) {
            double y = p.getY();
            double z = p.getZ();

            double novoY = y * cos + z * sin;
            double novoZ = -y * sin + z * cos;

            p.setY(novoY);
            p.setZ(novoZ);
        }


        for (Face f :faces) {
            f.setNormal(Vetor.calcularNormal(f));
        }


    }

    public void rotacionarY(double angulo) {
        double rad = Math.toRadians(angulo);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Vertice p : vertices) {
            double x = p.getX();
            double z = p.getZ();

            double novoX = x * cos + z * sin;
            double novoZ = -x * sin + z * cos;

            p.setX(novoX);
            p.setZ(novoZ);
        }

        for (Face f :faces) {
            f.setNormal(Vetor.calcularNormal(f));
        }

    }

}

