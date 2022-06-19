

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton {
    private static final long serialVersionUID = 1L;
    private Point location;
    private Point pressed;
    private boolean dragging;
    private String iconSrc;

    public ToolButton(){
        this("src/icon/unknown.png");
    }

    public ToolButton(String iconSrc) {
        iconSrc="src/icon/unknown.png";
        setIcon(new ImageIcon(iconSrc));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "123");//改成在白板新增png
            }
        });

        //DragListener listener = new DragListener();
        //addMouseListener(listener);
        //addMouseMotionListener(listener);
    }
    /*
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
            System.out.println("mouseReleased");
            System.out.println("Release in " + mousePosition);
        }

    }*/
}
















