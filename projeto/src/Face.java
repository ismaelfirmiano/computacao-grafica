import java.awt.*;
import java.util.List;

public class Face {
    private List<Vertice> contorno;
    private List<List<Vertice>> buracos;
    private Vetor normal = new Vetor(0, 0, 0);
    private Color cor;

    public Face(List<Vertice> vetices, List<List<Vertice>> buracos, Color cor) {
        this.contorno = vetices;
        this.buracos = buracos;
        this.cor = cor;
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
}
