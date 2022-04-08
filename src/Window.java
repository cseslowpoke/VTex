import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;

public class Window {
    JFrame frame;
    ArrayList<DraggableComponent> DraggableComponents;
    CollapsiblePanel collapsiblePanel;
    JPanel atomPanel;
    JPanel configPanel;
    JPanel drawPanel;
    JPanel textPanel;
    JPanel allPanel;
    JButton copyButton;


    public void run() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        atomPanel = new JPanel();
        configPanel = new JPanel();
        drawPanel = new JPanel();
        textPanel = new JPanel();
        allPanel = new JPanel();
        initCollapsiblePanel();
        allPanel.add(collapsiblePanel);
        allPanel.add(textPanel);
        allPanel.add(drawPanel);
        frame.setContentPane(allPanel);
        frame.setResizable(false);

//      frame.addComponentListener(new ComponentAdapter() {
//          @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//                frame.setSize(500, 500);
//            }
//        });
        frame.setVisible(true);
        test();
    }

    public void test() {
        ArrayList<SymbolAtom> atoms = new ArrayList<SymbolAtom>(10);
        for(int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                atoms.add(new SymbolAtom(""));
            }
            else {
                atoms.add(new SymbolAtom("SA"));
            }
        }
        for(int i=0;i<9;i++) {
            atoms.get(i).setSubscriptAtom(atoms.get(i + 1));
        }

        TeXFormula formula = new TeXFormula(atoms.get(0).generate());
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
        BufferedImage image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        System.out.println(icon.getIconHeight());
        icon.paintIcon(jl, g2, 0, 0);
        Graphics g = allPanel.getGraphics();
        g.drawImage(image,0,0,null);

    }

    private void initCollapsiblePanel() {
        DraggableComponents = new ArrayList<DraggableComponent>();
        collapsiblePanel = new CollapsiblePanel();
        collapsiblePanel.add(atomPanel);
        collapsiblePanel.add(configPanel);
    }

    public static void main(String[] args) {


        Window window = new Window();
        window.run();
    }
}
