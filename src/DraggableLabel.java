

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableLabel extends JLabel {
    private static final long serialVersionUID = 1L;
    private Point location;
    private Point pressed;
    private boolean dragging;

    public DraggableLabel() {
        this("");
    }
    public DraggableLabel(String s) {
        setIcon(new ImageIcon("src/icon/unknown.png"));
        DragListener listener = new DragListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
    public class DragListener extends MouseInputAdapter
    {

        Point location;
        Point mousePosition;
        MouseEvent pressed;

        public void mousePressed(MouseEvent me)
        {
            pressed = me;
        }

        public void mouseDragged(MouseEvent me)
        {
            Component component = me.getComponent();
            location = component.getLocation(location);


            int x = me.getX();
            int y = me.getY();

            mousePosition = new Point(x, y);
        }

        public void mouseReleased(MouseEvent me)
        {
//            System.out.println("mouseReleased");
            System.out.println("Release in " + mousePosition);
        }
    }
}
















