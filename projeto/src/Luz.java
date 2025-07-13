// Luz.java - Para definir a cor da luz
import java.awt.Color;

public class Luz {
    // Luz "amarelada" como solicitado
    public final Color corAmbiente = new Color(100, 100, 80); // Amarelo escuro
    public final Color corDifusa = new Color(255, 255, 224);   // Amarelo claro
    public final Color corEspecular = new Color(255, 255, 255); // Brilho branco/amarelado

    public final Vetor direcao;

    public Luz(Vetor direcao) {
        this.direcao = direcao.normalizado();
    }
}