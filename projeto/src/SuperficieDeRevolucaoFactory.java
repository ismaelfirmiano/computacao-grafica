import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SuperficieDeRevolucaoFactory {

    public static Objeto criarDeBezier(Bezier perfil, int segmentosPerfil, int segmentosRevolucao, int matiz) {
        List<Vertice> vertices = new ArrayList<>();
        List<Face> faces = new ArrayList<>();

        // Gerar Vértices
        for (int i = 0; i <= segmentosPerfil; i++) {
            double t = i / (double) segmentosPerfil; // t vai de 0 a 1

            Vetor pontoPerfil = perfil.getPoint(t);
            Vetor tangentePerfil = perfil.getTangent(t);

            Vetor normalPerfil = new Vetor(-tangentePerfil.getY(), tangentePerfil.getX(), 0);

            double raio = pontoPerfil.getX();
            double y = pontoPerfil.getY();

            for (int j = 0; j <= segmentosRevolucao; j++) {
                double theta = 2 * Math.PI * j / (double) segmentosRevolucao; // Rotação
                double cosTheta = Math.cos(theta);
                double sinTheta = Math.sin(theta);

                // Coordenadas do vértice 3D
                double x3d = raio * cosTheta;
                double z3d = raio * sinTheta;

                Vertice v = new Vertice(x3d, y, z3d);

                Vetor normal3d = new Vetor(normalPerfil.getX() * cosTheta, normalPerfil.getY(), normalPerfil.getX() * sinTheta);
                v.setNormal(normal3d.normalizado());

                vertices.add(v);
            }
        }

        for (int i = 0; i < segmentosPerfil; i++) {
            for (int j = 0; j < segmentosRevolucao; j++) {
                int primeiro = i * (segmentosRevolucao + 1) + j;
                int segundo = primeiro + segmentosRevolucao + 1;

                List<Vertice> contornoFace = new ArrayList<>();
                contornoFace.add(vertices.get(primeiro));
                contornoFace.add(vertices.get(primeiro + 1));
                contornoFace.add(vertices.get(segundo + 1));
                contornoFace.add(vertices.get(segundo));

                Face face = new Face(contornoFace, matiz);
                face.setMaterial(new Material(true)); // Material polido
                faces.add(face);
            }
        }

        return new Objeto(vertices, faces);
    }

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