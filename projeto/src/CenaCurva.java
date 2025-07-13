import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;


class CenaCurva extends JPanel {
    private final List<Objeto> objetos;
    private final Vetor camera;
    private final Luz luz;

    public CenaCurva(List<Objeto> objetos, Luz luz, Vetor camera) {
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
                Color corFinal;
                if (face.getMaterial() != null && face.getMaterial().isPolido()) {
                    // Use a normal do primeiro vértice como uma aproximação para a face inteira
                    // Uma implementação mais avançada faria interpolação de Gouraud ou Phong.
                    Vetor normalInterpolada = face.getContorno().getFirst().getNormal();
                    corFinal = calcularCorPhong(face, normalInterpolada);
                } else {
                    // Lógica de sombreamento antiga para objetos planos
                    double cos = Math.max(0.1, luz.direcao.produtoEscalar(face.getNormal())); // Use a luz ambiente como mínimo
                    float[] hsb = Color.RGBtoHSB(luz.corDifusa.getRed(), luz.corDifusa.getGreen(), luz.corDifusa.getBlue(), null);
                    corFinal = Color.getHSBColor(face.getMatiz()/360f, hsb[1], (float) (hsb[2] * cos));
                }
                g2.setColor(corFinal);
                desenharFace(g2, face);
            }
        }

    }

    private Color calcularCorPhong(Face face, Vetor normal) {
        Material mat = face.getMaterial();
        Color corBase = Color.getHSBColor(face.getMatiz()/360f, 1, 1);

        // Vetores normalizados
        Vetor N = normal.normalizado();
        Vetor L = luz.direcao; // Já normalizado no construtor da Luz
        Vetor V = camera;      // Já normalizado no construtor da CenaLuz

        // 1. Componente Ambiente
        double r_a = luz.corAmbiente.getRed()   / 255.0 * mat.kA;
        double g_a = luz.corAmbiente.getGreen() / 255.0 * mat.kA;
        double b_a = luz.corAmbiente.getBlue()  / 255.0 * mat.kA;

        // 2. Componente Difusa
        double dot_ln = Math.max(0, L.produtoEscalar(N));
        double r_d = (luz.corDifusa.getRed()   / 255.0 * corBase.getRed()   / 255.0) * mat.kD * dot_ln;
        double g_d = (luz.corDifusa.getGreen() / 255.0 * corBase.getGreen() / 255.0) * mat.kD * dot_ln;
        double b_d = (luz.corDifusa.getBlue()  / 255.0 * corBase.getBlue()  / 255.0) * mat.kD * dot_ln;

        // 3. Componente Especular
        double r_s = 0, g_s = 0, b_s = 0;
        if (dot_ln > 0) { // Só calcula brilho se a face estiver iluminada
            Vetor R = N.multiplica(2 * dot_ln).subtrai(L).normalizado();
            double dot_rv = Math.max(0, R.produtoEscalar(V));
            double specFactor = Math.pow(dot_rv, mat.shininess);

            r_s = (luz.corEspecular.getRed()   / 255.0) * mat.kS * specFactor;
            g_s = (luz.corEspecular.getGreen() / 255.0) * mat.kS * specFactor;
            b_s = (luz.corEspecular.getBlue()  / 255.0) * mat.kS * specFactor;
        }

        // Combina e trava os valores entre 0 e 1
        float r = (float) Math.min(1.0, r_a + r_d + r_s);
        float g = (float) Math.min(1.0, g_a + g_d + g_s);
        float b = (float) Math.min(1.0, b_a + b_d + b_s);

        return new Color(r, g, b);
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
