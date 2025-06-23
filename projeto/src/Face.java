import java.util.List;


public class Face {
    private final List<Vertice> contorno;
    private Vetor normal = new Vetor(0, 0, 0);
    private int matiz;

    public Face(List<Vertice> vetices, int matiz) {
        this.contorno = vetices;
        this.matiz = matiz;
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
