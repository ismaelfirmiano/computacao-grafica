import java.util.List;


public class Face {
    private final List<Vertice> contorno;
    private Vetor normal = new Vetor(0, 0, 0);
    private int matiz;
    private Material material;

    public Face(List<Vertice> vertices, int matiz) {
        this.contorno = vertices;
        this.matiz = matiz;
        // Material padrão não polido
        this.material = new Material(false);
    }

    public List<Vertice> getContorno() {
        return contorno;
    }


    public Material getMaterial() { return material; }
    public void setMaterial(Material material) { this.material = material; }

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
