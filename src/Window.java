import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;
import org.scilab.forge.jlatexmath.Box;

public class Window {
    JFrame frame;
    ArrayList<ToolButton> DraggableComponents;
    CollapsiblePanel collapsiblePanel;
    JPanel showPanel;
//    JPanel drawPanel;
//    JPanel textPanel;
    JSplitPane allPanel;
    JSplitPane rightPanel;
    JButton copyButton;
    JPanel latexOutput;
    JTextField latexOutputText;
    JMenuBar menuBar;
    JButton latexCopyButton;
    DragSource dragSource;
    public Window() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        initShowPanel();
        initCollapsiblePanel();
        latexOutput = new JPanel(new BorderLayout());
        latexOutputText = new JTextField("Latex output here:test test test test test test test test test test test test test test");
        latexOutputText.setColumns(20);
        latexCopyButton = new JButton("Copy");
        latexOutputText.setEditable(false);
        latexOutput.add(latexOutputText, BorderLayout.CENTER);
        latexOutput.add(latexCopyButton, BorderLayout.EAST);
        rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, showPanel, latexOutput);
        rightPanel.setDividerLocation(400);
        allPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, collapsiblePanel, rightPanel);
        allPanel.setDividerLocation(150);
        allPanel.setOneTouchExpandable(true);
        frame.add(allPanel);

        initMenuBar();
        frame.setJMenuBar(menuBar);
        frame.setContentPane(allPanel);
    }
    private void initShowPanel() {
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(new JLabel("showPanel"));
    }

    private void initCollapsiblePanel() {
        //DraggableComponents = new ArrayList<DraggableLabel>();
        //DraggableComponents.add(new DraggableLabel("button2"));
        //DraggableComponents.add(new DraggableLabel("button3"));

        collapsiblePanel = new CollapsiblePanel();
    }

    private void initMenuBar(){
        menuBar = new JMenuBar();
        JMenu save = new JMenu("save");
        JMenuItem saveAsPNG = new JMenuItem("Save as PNG");
        save.add(saveAsPNG);
        menuBar.add(save);
        //TODO: implement save function
    }


    public void run() {
        //frame.setResizable(false);
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
