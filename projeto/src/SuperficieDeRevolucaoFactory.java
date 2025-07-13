import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SuperficieDeRevolucaoFactory {

    /**
     * Cria um objeto 3D girando uma curva 2D em torno do eixo Y.
     *
     * @param perfil Função que define o raio da superfície em uma dada altura y. Ex: y -> Math.sqrt(1 - y*y) para uma esfera.
     * @param segmentosLongitude Número de fatias ao redor do eixo Y.
     * @param segmentosLatitude  Número de anéis ao longo do eixo Y.
     * @param matiz              Cor base da superfície.
     * @return Um Objeto 3D representando a superfície de revolução.
     */
    public static Objeto criarSuperficie(Function<Double, Double> perfil, int segmentosLongitude, int segmentosLatitude, int matiz) {
        List<Vertice> vertices = new ArrayList<>();
        List<Face> faces = new ArrayList<>();

        // Gerar Vértices
        for (int i = 0; i <= segmentosLatitude; i++) {
            double phi = Math.PI * i / (double) segmentosLatitude; // Varia de 0 a PI (de cima para baixo)
            double y = Math.cos(phi); // Coordenada Y
            double raio = Math.sin(phi); // Raio do anel horizontal

            for (int j = 0; j <= segmentosLongitude; j++) {
                double theta = 2 * Math.PI * j / (double) segmentosLongitude; // Varia de 0 a 2PI (ao redor)
                double x = Math.cos(theta) * raio;
                double z = Math.sin(theta) * raio;

                Vertice v = new Vertice(x, y, z);

                // Para sombreamento suave, o normal do vértice em uma esfera aponta para fora do centro.
                v.setNormal(new Vetor(x, y, z).normalizado());
                vertices.add(v);
            }
        }

        // Gerar Faces (quadriláteros)
        for (int i = 0; i < segmentosLatitude; i++) {
            for (int j = 0; j < segmentosLongitude; j++) {
                int primeiro = i * (segmentosLongitude + 1) + j;
                int segundo = primeiro + segmentosLongitude + 1;

                List<Vertice> contornoFace = new ArrayList<>();
                contornoFace.add(vertices.get(primeiro));
                contornoFace.add(vertices.get(primeiro + 1));
                contornoFace.add(vertices.get(segundo + 1));
                contornoFace.add(vertices.get(segundo));

                Face face = new Face(contornoFace, matiz);

                // Adiciona um material polido a esta face
                face.setMaterial(new Material(true)); // Passaremos a criar essa classe

                faces.add(face);
            }
        }

        Objeto superficie = new Objeto(vertices, faces);

        // A normal da face ainda pode ser útil para outras coisas (como back-face culling)
        for (Face f : superficie.getFaces()) {
            f.setNormal(Vetor.calcularNormal(f));
        }

        return superficie;
    }
}