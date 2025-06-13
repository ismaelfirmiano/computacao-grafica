import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;


class Renderizacao extends JPanel {
    List<Objeto> objetos;
    Vetor luz = new Vetor(1,-1, 1).normalizado();
    private double mediaZ(Face face) {
        return face.getContorno().stream().mapToDouble(Vertice::getZ).average().orElse(0);
    }

    private boolean estaDeFrente(Face face) {
        Vetor normal = face.getNormal();
        if (normal == null) return true; // Se não tiver normal, desenha mesmo assim

        // Vetor do observador (sentido da câmera): no caso mais simples, em -Z
        Vetor direcaoCamera = new Vetor(0, 0, -1);

        double produtoEscalar = normal.getX() * direcaoCamera.getX()
                + normal.getY() * direcaoCamera.getY()
                + normal.getZ() * direcaoCamera.getZ();

        return produtoEscalar < 0; // Se menor que 0, está voltada para a câmera
    }

    public Renderizacao(String[] nome) {
        objetos = new ArrayList<>(); // INICIALIZA AQUI
        for (String n : nome) {
            objetos.add(ObjetoFactory.criarObjetoDeArquivo(n));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(Objeto objeto : objetos){
            objeto.getFaces().sort((f1, f2) -> {
                double z1 = mediaZ(f1);
                double z2 = mediaZ(f2);
                return Double.compare(z2, z1); // Desenha primeiro as mais "distantes"
            });


            for (Face face : objeto.getFaces()) {
                if (estaDeFrente(face)) {
                    //List<Vertice> vertices = face.getContorno();
                    g2.setColor(face.getCor() != null ? face.getCor() : Color.GRAY);
                    desenharFace(g2, face);
                }
            }
        }


       // desenharFace((Graphics2D) g, balaoHex.getFaces().get(0).getVertices() , Color.BLUE);


    }

    private void desenharFace(Graphics2D g2, Face face) {
        List<Vertice> contorno = face.getContorno();
        List<List<Vertice>> buracos = face.getBuracos();

        // Projeção dos vértices do contorno
        Polygon contornoPoly = new Polygon();
        for (Vertice v : contorno) {
            int x = (int) v.getX() + getWidth() / 2;
            int y = (int) -v.getY() + getHeight() / 2;
            contornoPoly.addPoint(x, y);
        }

        Area areaFinal = new Area(contornoPoly);

        // Subtrair os buracos da face
        if (buracos != null) {
            for (List<Vertice> buraco : buracos) {
                Polygon buracoPoly = new Polygon();
                for (Vertice v : buraco) {
                    int x = (int) v.getX() + getWidth() / 2;
                    int y = (int) -v.getY() + getHeight() / 2;
                    buracoPoly.addPoint(x, y);
                }
                areaFinal.subtract(new Area(buracoPoly));
            }
        }

        g2.fill(areaFinal);
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

    public void rotacionar(double angulo) {
        for(Objeto obj : objetos){
            obj.rotacionar(angulo);
        }
        for(Objeto obj : objetos){
            obj.iluminar(luz);
        }
    }
}
