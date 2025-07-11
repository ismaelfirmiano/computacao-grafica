import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ObjetoFactory {

    public static Objeto criarObjetoDeArquivo(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine();
            String[] dados = linha.split(" ");

            int v = Integer.parseInt(dados[0]);
            int f = Integer.parseInt(dados[1]);

            List<Vertice> vertices = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                linha = br.readLine();
                dados = linha.split(" ");

                double x = Double.parseDouble(dados[0]);
                double y = Double.parseDouble(dados[1]);
                double z = Double.parseDouble(dados[2]);

                vertices.add(new Vertice(x, y, z));
            }


            List<Face> faces = new ArrayList<>();
            for (int i = 0; i < f; i++) {
                linha = br.readLine();
                dados = linha.split(" ");

                List<Vertice> faceVertices = new ArrayList<>();

                for(String dado : dados) {
                    faceVertices.add(vertices.get(Integer.parseInt(dado)-1));
                }

                linha = br.readLine();
                String dado = linha.split(" ")[0];

                faces.add(new Face(faceVertices, Integer.parseInt(dado)));
            }

            return new Objeto(vertices, faces);

        } catch (IOException | NumberFormatException e) {
            return null;
        }

    }
}
