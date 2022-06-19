

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
    }  //defalut用預設圖片

    public ToolButton(String iconSrc) {
        iconSrc="src/icon/unknown.png"; //後面能用this.iconSrc=iconSrc; 然後在白板抓圖時，呼叫iconSrc的路徑

        //可以設定atom然後再新增白板時有不同功能!!!(跟隊友討論一下，我是latex拉基

        ImageIcon originIcon=new ImageIcon(iconSrc);
        Image scaleImage=originIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT); //讓每個Icon看起來都長一樣

        setIcon(new ImageIcon(scaleImage));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "改成在白板新增png"); //改成在白板新增png
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
















