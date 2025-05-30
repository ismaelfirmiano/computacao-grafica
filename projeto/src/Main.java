import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame janela = new JFrame("Face 3D - Java2D");
        Renderizacao painel = new Renderizacao();

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);
        janela.setContentPane(painel);
        janela.setVisible(true);

        // Thread de animação (poderia fazer rotação)
        new Thread(() -> {
            double angulo = 0;
            while (true) {
                painel.atualizarFace(angulo);
                painel.repaint();

                angulo += 2;
                if (angulo >= 360) angulo = 0;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }
}