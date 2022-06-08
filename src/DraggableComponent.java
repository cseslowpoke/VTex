

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DraggableComponent extends JComponent {
    private static final long serialVersionUID = 1L;
    private Point location;
    private Point pressed;
    private boolean dragging;

    public DraggableComponent() {
        this.location = new Point(0, 0);
        this.pressed = new Point(0, 0);
        this.dragging = false;
    }


    public void mousePressed(MouseEvent e) {
        this.pressed = e.getPoint();
        this.dragging = true;
    }


    public void mouseReleased(MouseEvent e) {
        this.dragging = false;
    }


    public void mouseDragged(MouseEvent e) {
        if (this.dragging) {
            Point current = e.getPoint();
            this.location.translate(current.x - this.pressed.x, current.y - this.pressed.y);
            this.pressed = current;
            this.repaint();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.translate(this.location.x, this.location.y);
        this.paintDraggableComponent(g);
    }

    private void paintDraggableComponent(Graphics g) {



    }
}
