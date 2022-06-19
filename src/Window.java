import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;
import org.scilab.forge.jlatexmath.Box;

public class Window {
    JFrame frame;
    ArrayList<DraggableComponent> DraggableComponents;
    CollapsiblePanel collapsiblePanel;
    JPanel showPanel;
    JSplitPane allPanel;
    JSplitPane rightPanel;
    JPanel latexOutput;
    JTextField latexOutputText;
    JMenuBar menuBar;
    JButton latexCopyButton;
    Latex latex;

    public Window() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        initShowPanel();
        initCollapsiblePanel();
        initLatexOutputPanel();
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

    private void initLatexOutputPanel(){
        latexOutput = new JPanel(new BorderLayout());
        latexOutputText = new JTextField("Latex output here:test test test test test test test test test test test test test test");
        latexOutputText.setColumns(20);
        latexCopyButton = new JButton("Copy");
        FracAtom frac = new FracAtom("x","xyzw");
        latex.root = frac;
        latexCopyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(latexOutputText.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                //tem


                //FracAtom topF = new FracAtom("12","34");
                //FracAtom botF = new FracAtom("56","78");

                //frac.setNumerator(topF);
                //frac.setDenominator(botF);

                repaintShowpanel();
            }
        });
        latexOutputText.setEditable(false);
        latexOutput.add(latexOutputText, BorderLayout.CENTER);
        latexOutput.add(latexCopyButton, BorderLayout.EAST);
    }

    private void initShowPanel() {
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(new JLabel("showPanel"));
        showPanel.setBackground(Color.white);
        latex = new Latex();
        showPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                latex.mouseClick(e.getX(),e.getY());
            }
        });
    }

    private void initCollapsiblePanel() {
        DraggableComponents = new ArrayList<DraggableComponent>();
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

    public void repaintShowpanel(){
        latexOutputText.setText(latex.draw(showPanel.getGraphics()));
    }


}
