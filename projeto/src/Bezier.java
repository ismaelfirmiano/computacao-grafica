public class Bezier {

    private final Vetor p0, p1, p2, p3;

    public Bezier(Vetor p0, Vetor p1, Vetor p2, Vetor p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Calcula o ponto na curva para um t (0 <= t <= 1)
    public Vetor getPoint(double t) {
        double u = 1 - t;
        double tt = t * t;
        double uu = u * u;
        double uuu = uu * u;
        double ttt = tt * t;

        Vetor p = p0.multiplica(uuu);
        p = p.soma(p1.multiplica(3 * uu * t));
        p = p.soma(p2.multiplica(3 * u * tt));
        p = p.soma(p3.multiplica(ttt));

        return p;
    }

    // Calcula a tangente na curva para um t. A tangente é a derivada da curva.
    public Vetor getTangent(double t) {
        double u = 1 - t;
        double tt = t * t;
        double uu = u * u;

        Vetor p = p1.subtrai(p0).multiplica(3 * uu);
        p = p.soma(p2.subtrai(p1).multiplica(6 * u * t));
        p = p.soma(p3.subtrai(p2).multiplica(3 * tt));

        return p.normalizado();
    }
}