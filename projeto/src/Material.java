public class Material {
    private final boolean polido;
    // Coeficientes de reflexão para o modelo de Phong
    public final double kA = 0.1; // Ambiente
    public final double kD = 0.7; // Difuso
    public final double kS = 0.9; // Especular (alto para material polido)
    public final double shininess = 128.0; // Expoente especular (alto para brilhos pequenos e intensos)

    public Material(boolean polido) {
        this.polido = polido;
    }

    public boolean isPolido() {
        return polido;
    }
}