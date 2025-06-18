import java.awt.*;
import java.util.List;


public class Face {
    private List<Vertice> contorno;
    private Vetor normal = new Vetor(0, 0, 0);
    private int matiz = 0;

    public Face(List<Vertice> vetices) {
        this.contorno = vetices;
        this.matiz = 0;
    }

    public List<Vertice> getContorno() {
        return contorno;
    }

    public Vetor getNormal() {
        return normal;
    }
    public void setNormal(Vetor normal) {
        this.normal = normal;
    }

    public int getMatiz() {
        return matiz;
    }
    public void setMatiz(int matiz) {
        this.matiz = matiz;
    }
}
