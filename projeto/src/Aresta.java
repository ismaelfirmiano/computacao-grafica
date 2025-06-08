public class Aresta {

    private String nome;
    private Vertice v1;
    private Vertice v2;

    public Aresta(String nome, Vertice v1, Vertice v2) {

        this.nome = nome;
        this.v1 = v1;
        this.v2 = v2;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
