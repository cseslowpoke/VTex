import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;
import org.scilab.forge.jlatexmath.Box;

public class Window {
    JFrame frame;
    ArrayList<DraggableComponent> DraggableComponents;
    CollapsiblePanel collapsiblePanel;
    JPanel showPanel;
    JPanel atomPanel;
    JPanel configPanel;
//    JPanel drawPanel;
//    JPanel textPanel;
    JPanel allPanel;
    JButton copyButton;

    public Window() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        allPanel = new JPanel(new GridLayout(1, 2));
        initShowPanel();
        initCollapsiblePanel();
        allPanel.add(collapsiblePanel);
        allPanel.add(showPanel);
        frame.add(allPanel);
        frame.setContentPane(allPanel);
    }
    private void initShowPanel() {
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(new JLabel("showPanel"));
        showPanel.add(new JTextField("showPanel"));
    }

    private void initCollapsiblePanel() {
        DraggableComponents = new ArrayList<DraggableComponent>();
        collapsiblePanel = new CollapsiblePanel();
        atomPanel = new JPanel();
        collapsiblePanel.add(atomPanel);
        configPanel = new JPanel();
        collapsiblePanel.add(configPanel);
    }

    public void run() {
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        Window window = new Window();
        window.run();
    }

    public void test() {
        ArrayList<SymbolAtom> atoms = new ArrayList<SymbolAtom>(10);
//        for(int i = 0; i < 10; i++) {
//            if(i % 2 == 0) {
//                atoms.add(new SymbolAtom("\\ "));
//            }
//            else {
//                atoms.add(new SymbolAtom("SA"));
//            }
//        }
        for(int i=0;i<9;i++) {
            atoms.get(i).setSubscriptAtom(atoms.get(i + 1));
        }
        Box.DEBUG = !false;
        TeXFormula formula = new TeXFormula(atoms.get(0).generate());
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 100);
        BufferedImage image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        System.out.println(icon.getIconHeight());
        icon.paintIcon(jl, g2, 0, 0);
        Graphics g = allPanel.getGraphics();
        g.drawImage(image,0,0,null);
    }
}
