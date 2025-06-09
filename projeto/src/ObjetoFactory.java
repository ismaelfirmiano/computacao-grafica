import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Retention;
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


            /*
            List<Aresta> arestas = new ArrayList<>();
            for (int i = 0; i < a; i++) {
                linha = br.readLine();
                dados = linha.split(" ");

                int x = Integer.parseInt(dados[0]);
                int y = Integer.parseInt(dados[1]);

                arestas.add(new Aresta(vertices.get(x), vertices.get(y)));
            }
            */

            List<Face> faces = new ArrayList<>();
            for (int i = 0; i < f; i++) {
                linha = br.readLine();
                dados = linha.split(" ");

                List<Vertice> faceVertices = new ArrayList<>();

                for(String dado : dados) {
                    faceVertices.add(vertices.get(Integer.parseInt(dado)-1));
                }

                linha = br.readLine();

                int b = Integer.parseInt(linha.split(" ")[0]);

                List<List<Vertice>> buracos = new ArrayList<>();
                for (int j = 0; j < b; j++) {
                    List<Vertice> buraco = new ArrayList<>();

                    linha = br.readLine();
                    dados = linha.split(" ");

                    for(String dado : dados){
                        buraco.add(vertices.get(Integer.parseInt(dado)-1));
                    }

                    buracos.add(buraco);
                }

                faces.add(new Face(faceVertices, buracos, Color.cyan));

            }

            return new Objeto(vertices, faces);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

    }
}
