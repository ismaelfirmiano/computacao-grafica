import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Face 3D - Java2D");
        List<Objeto> objetos = new ArrayList<>();


        // INSTANCIA OBJETOS A PARTIR DE ARQUIVOS
        objetos.add(ObjetoFactory.criarObjetoDeArquivo("/home/matheus/IdeaProjects/computacao-grafica/projeto/src/Objetos/ismael.txt"));
        objetos.add(ObjetoFactory.criarObjetoDeArquivo("/home/matheus/IdeaProjects/computacao-grafica/projeto/src/Objetos/balao.txt"));
        objetos.add(ObjetoFactory.criarObjetoDeArquivo("/home/matheus/IdeaProjects/computacao-grafica/projeto/src/Objetos/piramide.txt"));


        // OBJETO RESPONSÁVEL POR DESENHAR NA TELA
        CenaLuz cena = new CenaLuz(
                objetos,
                new Vetor(1,-1, 1).normalizado(), // LUZ
                new Vetor(0, 0, -1).normalizado() // CÂMERA
        );

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000, 800);
        janela.setContentPane(cena);
        janela.setVisible(true);

        MouseControl rotator = new MouseControl(cena);
        cena.addMouseListener(rotator);
        cena.addMouseMotionListener(rotator);


        // MATRIZ PARA DEIXAR UM HEXAGONO PERFEITO
        objetos.get(1).multiplicaMatriz(
                new double[][]{
                        {1, 0, 0, 0},
                        {0, 1, 0, 0},
                        {0, 0, Math.sqrt(5)/2, 0},
                        {0, 0, 0, 1}
                }
        );


        // APLICA TRANSFORMAÇÕES USANDO MULTIPLICAÇÃO DE MATRIZES
        objetos.get(0).multiplicaMatriz(
                new double[][]{
                        {-30, 0, 0, 0},
                        {0, -30, 0, 0},
                        {0, 0, 30, 0},
                        {0, 0, 0, 1}
                }
        );
        objetos.get(1).multiplicaMatriz(
                new double[][]{
                {20, 0, 0, 0},
                {0, 90, 0, 0},
                {0, 0, 20, 0},
                {0, 0, 0, 1}
        }
        );
        objetos.get(2).multiplicaMatriz(
                new double[][]{
                        {70, 0, 0, -200},
                        {0, 70, 0, 50},
                        {0, 0, 70, -70},
                        {0, 0, 0, 1}
                }
        );


        Timer timer = new Timer(1, e -> {

            double rx;
            double ry;

            rx = rotator.getRotX();
            ry = rotator.getRotY();

            for (Objeto objeto : objetos) {

                objeto.rotacionarX(rx);
                objeto.rotacionarY(ry);

            }


            cena.repaint();
        });

        timer.start();

    }

}