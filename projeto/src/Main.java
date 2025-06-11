import javax.swing.*;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void menu(){
        System.out.println("1 - MATRIZ");
    }

    public static void main(String[] args) {
        JFrame janela = new JFrame("Face 3D - Java2D");
        Scanner scanner = new Scanner(System.in);


        Renderizacao painel = new Renderizacao(new String[]{"/home/matheus/IdeaProjects/computacao-grafica/projeto/src/Objetos/escada.txt",
                "/home/matheus/IdeaProjects/computacao-grafica/projeto/src/Objetos/matheus.txt"});
        //Renderizacao painel = new Renderizacao(new String[]{"src/Objetos/escada.txt"});


        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1000, 800);
        janela.setContentPane(painel);
        janela.setVisible(true);

        //int i = scanner.nextInt();
        double[][] matriz = {{-25, 0, 0, 0}, {0, -25, 0, 0}, {0, 0, 25, 0}, {0, 0, 0, 1}};
        painel.objetos.get(0).aplicarTransformacao(matriz);

        // Thread de animação (poderia fazer rotação)
        new Thread(() -> {
            double angulo = 1;
            while (true) {
                painel.rotacionar(angulo);
                painel.repaint();


                if (angulo >= 360) angulo = 0;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();

        new Thread(() -> {

            int i = 0;
            while (true) {
                for(Objeto obj : painel.objetos) {
                    obj.colorir((float) i /obj.getTam());
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                i++;
            }
        }).start();


        int x;

        //System.out.println("Cor inicial (0-360): ");
        //float cor = scanner.nextFloat();
        //painel.objeto.colorir(cor/360);


    }

}