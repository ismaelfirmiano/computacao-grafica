import java.util.ArrayList;
import java.util.List;

public class Face {
    private String nome;
    private List<Aresta> arestas;
    private List<Vertice> vertices;
    private Ponto normal;

    public Face(String nome) {

        this.nome = nome;
        this.arestas = new ArrayList<>();
        this.vertices = new ArrayList<>();

    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void addAresta(Aresta aresta) {

        arestas.add(aresta);


    }

    public void addVertice(Vertice vertice) {

        vertices.add(vertice);

    }

    public String getNome(){
            return nome;

    }
    public void setNome(String nome){
        this.nome = nome;

    }
}
