import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Face 3D - Java2D");
        List<Objeto> objetos = new ArrayList<>();

        objetos.add(ObjetoFactory.criarObjetoDeArquivo("src/Objetos/ismael.txt"));
        objetos.add(ObjetoFactory.criarObjetoDeArquivo("src/Objetos/matheus.txt"));

        CenaLuz cena = new CenaLuz(
                objetos,
                new Vetor(1,-1, 1).normalizado(),
                new Vetor(0, 0, -1).normalizado()
        );

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000, 800);
        janela.setContentPane(cena);
        janela.setVisible(true);


        objetos.get(0).multiplicaMatriz(
                new double[][]{
                        {-25, 0, 0, 0},
                        {0, -25, 0, 0},
                        {0, 0, 25, 0},
                        {0, 0, 0, 1}
                }
        );

        objetos.get(1).multiplicaMatriz(
                new double[][]{
                        {2, 0, 0, 0},
                        {0, 2, 0, 0},
                        {0, 0, 2, 0},
                        {0, 0, 0, 1}
                }
        );

        for (Face face : objetos.get(0).getFaces()) {
            face.setMatiz(0);
        }

        for (Face face : objetos.get(1).getFaces()) {
            face.setMatiz(180);
        }

        Timer timer = new Timer(1, e -> {

            objetos.get(0).rotacionarX(0.01);
            objetos.get(0).rotacionarY(0.01);
            objetos.get(1).rotacionarX(0.02);
            objetos.get(1).rotacionarY(0.02);

            cena.repaint();
        });

        timer.start();

    }

}