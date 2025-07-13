import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Face 3D - Java2D");
        List<Objeto> objetos = new ArrayList<>();

        //Objeto esferaPolida = SuperficieDeRevolucaoFactory.criarSuperficie(y -> 1.0, 30, 15, 200); // 200 = matiz azulado
        //objetos.add(esferaPolida);

        Vetor p0 = new Vetor(0.1, -1.5, 0);
        Vetor p1 = new Vetor(1.5, -1.0, 0);
        Vetor p2 = new Vetor(0.8,  1.0, 0);
        Vetor p3 = new Vetor(1.0,  1.5, 0);

        Bezier perfilVaso = new Bezier(p0, p1, p2, p3);

        Objeto vasoPolido = SuperficieDeRevolucaoFactory.criarDeBezier(
                perfilVaso,
                100,
                100,
                25
        );

        objetos.add(vasoPolido);

        // OBJETO RESPONSÁVEL POR DESENHAR NA TELA
        CenaCurva cena = new CenaCurva(
                objetos,
                new Luz(new Vetor(1,-1, 1).normalizado()), // LUZ
                new Vetor(0, 0, -1).normalizado() // CÂMERA
        );

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000, 800);
        janela.setContentPane(cena);
        janela.setVisible(true);

        MouseControl rotator = new MouseControl(cena);
        cena.addMouseListener(rotator);
        cena.addMouseMotionListener(rotator);


        objetos.get(0).multiplicaMatriz(
                new double[][]{
                        {80, 0, 0, -150},
                        {0, 80, 0, 0},
                        {0, 0, 80, 0},
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