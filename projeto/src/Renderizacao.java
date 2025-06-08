import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Arrays;


class Renderizacao extends JPanel {
    List<Ponto> face1;
    Objeto balaoHex;
    public Renderizacao() {

        FileReader file = new FileReader();
        balaoHex = file.createObject();



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
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(0).getVertices() , Color.BLUE);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(1).getVertices() , Color.RED);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(2).getVertices() , Color.BLACK);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(3).getVertices() , Color.GREEN);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(4).getVertices() , Color.PINK);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(5).getVertices() , Color.ORANGE);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(6).getVertices() , Color.GRAY);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(7).getVertices() , Color.CYAN);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(8).getVertices() , Color.MAGENTA);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(9).getVertices() , Color.YELLOW);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(10).getVertices() , Color.BLUE);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(11).getVertices() , Color.GREEN);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(12).getVertices() , Color.PINK);
        desenharFace((Graphics2D) g, balaoHex.getFaces().get(13).getVertices() , Color.BLACK);


    }

    private void desenharFace(Graphics2D g2, List<Vertice> face, Color cor) {
        int n = face.size();
        int[] xPoints = new int[n];
        int[] yPoints = new int[n];

        for (int i = 0; i < n; i++) {
            // Projeção simples: ignora z
            xPoints[i] = (int) face.get(i).getX() + getWidth() / 2;
            yPoints[i] = (int) -face.get(i).getY() + getHeight() / 2; // Inverte y
        }

        g2.setColor(cor);
        g2.fillPolygon(xPoints, yPoints, n);
    }

    private void desenharVertices(Graphics2D g2, List<Vertice> face, Color cor) {

        g2.setColor(cor);

        double angle = Math.toRadians(30);
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);

        int offsetX = getWidth() / 2;
        int offsetY = getHeight() / 2;

        for (Vertice v : face) {
            double x = v.getX();
            double y = v.getY();
            double z = v.getZ();

            //Projeção isométrica

            int screenX = (int) ((x - z) * cos) + offsetX;
            int screenY = (int) (y + (x + z) * sin) + offsetY;

            g2.fillOval(screenX - 3, screenY - 3, 6, 6);

        }
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
