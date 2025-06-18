import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;


class CenaLuz extends JPanel {
    private final List<Objeto> objetos;
    private final Vetor luz;
    private final Vetor camera;

    public CenaLuz(List<Objeto> objetos, Vetor luz, Vetor camera) {
        this.objetos = objetos;
        this.luz = luz;
        this.camera = camera;
    }

    private double mediaZ(Face face) {
        if( face==null ) return 0;

        return face.getContorno().stream().mapToDouble(Vertice::getZ).average().orElse(0);
    }

    private boolean deFrente(Face face) {
        if (face == null)
            return false;

        Vetor normal = face.getNormal();

        if (normal == null) return true;


        double produtoEscalar = normal.getX() * camera.getX()
                + normal.getY() * camera.getY()
                + normal.getZ() * camera.getZ();

        return produtoEscalar < 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        List<Face> faces = new ArrayList<>();
        for(Objeto objeto : objetos){
            faces.addAll(objeto.getFaces());
        }

        faces.sort((f1, f2) -> {
            double z1 = mediaZ(f1);
            double z2 = mediaZ(f2);
            return Double.compare(z2, z1);
        });

        // Se estiver de frente calcular a cor
        for (Face face : faces) {
            if (deFrente(face)) {
                double cos = Math.max(0.5, 0.49+(luz.produtoEscalar(face.getNormal())/2.0));
                g2.setColor(Color.getHSBColor(face.getMatiz()/360f, 1, (float) cos));
                desenharFace(g2, face);
            }
        }

    }

    private void desenharFace(Graphics2D g2, Face face) {
        List<Vertice> contorno = face.getContorno();

        if (contorno == null || contorno.isEmpty()) return;

        Path2D area = new Path2D.Double();
        Vertice primeiro = contorno.getFirst();
        area.moveTo(primeiro.getX() + getWidth() / 2.0, -primeiro.getY() + getHeight() / 2.0);

        for (int i = 1; i < contorno.size(); i++) {
            Vertice v = contorno.get(i);
            double x = v.getX() + getWidth() / 2.0;
            double y = -v.getY() + getHeight() / 2.0;
            area.lineTo(x, y);
        }

        area.closePath();

        g2.fill(area);
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

}
