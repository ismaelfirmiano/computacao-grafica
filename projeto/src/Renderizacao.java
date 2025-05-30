import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;


class Renderizacao extends JPanel {
    List<Ponto> face1;

    public Renderizacao() {
        // Define uma face com 4 vértices (quadrado no plano XY)
        face1 = Arrays.asList(
                new Ponto(-50, -50, 0),
                new Ponto(50, -50, 0),
                new Ponto(50, 50, 0),
                new Ponto(-50, 50, 0)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharFace((Graphics2D) g, face1, Color.BLUE);
    }

    private void desenharFace(Graphics2D g2, List<Ponto> face, Color cor) {
        int n = face.size();
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for (int i = 0; i < n; i++) {
            // Projeção simples: ignora z
            xPoints[i] = (int) face.get(i).x + getWidth() / 2;
            yPoints[i] = (int) -face.get(i).y + getHeight() / 2; // Inverte y
        }

        g2.setColor(cor);
        g2.fillPolygon(xPoints, yPoints, n);
    }

    public void atualizarFace(double angulo) {
        double rad = Math.toRadians(angulo);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        for (Ponto p : face1) {
            double x = p.x, y = p.y;
            // Rotação em torno do Z
            p.x = x * cos - y * sin;
            p.y = x * sin + y * cos;
        }
    }
}
