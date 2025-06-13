import java.awt.*;
import java.util.List;


public class Face {
    private List<Vertice> contorno;
    private List<List<Vertice>> buracos;
    private Vetor normal = new Vetor(0, 0, 0);
    private int matiz = 0;
    private Color cor;

    public Face(List<Vertice> vetices, List<List<Vertice>> buracos, int matiz) {
        this.contorno = vetices;
        this.buracos = buracos;
        this.matiz = matiz;
    }

    public Vetor getNormal() {
        return normal;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Color getCor() {
        return cor;
    }

    public List<Vertice> getContorno() {
        return contorno;
    }

    public void setNormal(Vetor normal) {
        this.normal = normal;
    }

    public List<List<Vertice>> getBuracos() {
        return buracos;
    }

    public void iluminar(Vetor vetor) {
        double cos = Math.max(0.5, 0.49+(vetor.produtoEscalar(normal)/2.0));
        setCor(Color.getHSBColor(matiz, 1, (float) cos));
    }
}
