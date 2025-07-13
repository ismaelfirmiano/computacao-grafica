import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseControl extends JPanel implements MouseListener, MouseMotionListener {

    double rotX = 0;
    double rotY = 0;
    int lastX, lastY;

    private final CenaCurva target;

    public MouseControl(CenaCurva target) {
        this.target = target;
    }
    public double getRotX() { return rotX; }
    public double getRotY() { return rotY; }

    @Override
    public void mousePressed(MouseEvent e) {
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;
        rotY += dx * -0.001;
        rotX += dy * 0.001;
        lastX = e.getX();
        lastY = e.getY();
        repaint();

    }

    public void mouseReleased(MouseEvent e) {
        rotX = 0;
        rotY = 0;
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}
