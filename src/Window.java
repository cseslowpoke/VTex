import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;
import org.scilab.forge.jlatexmath.Box;

public class Window {
    JFrame frame;
    ToolBarPanel toolBarPanel;
    JPanel showPanel;
    JSplitPane allPanel;
    JSplitPane leftPanel;
    JSplitPane rightPanel;
    JPanel latexOutput;
    JTextField latexOutputText;
    JMenuBar menuBar;
    JButton latexCopyButton;
    JButton latexClearButton;
    Latex latex;

    static public Builder builder = new Builder();

    static ModifyPanel modifyPanel;

    public Window() {
        frame = new JFrame("Latex小工具");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        initShowPanel();
        initLatexOutputPanel();
        setToolBarPanel(); //工具列修改 (左上)
        setMenuBar();//最上面的選單(頂端)
        setModifyPanel();

        rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, showPanel, latexOutput);
        rightPanel.setResizeWeight(0.8);
        leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolBarPanel, modifyPanel);
        leftPanel.setResizeWeight(0.8);
        allPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        allPanel.setResizeWeight(0);

        rightPanel.setOneTouchExpandable(true);
        leftPanel.setOneTouchExpandable(true);
        allPanel.setOneTouchExpandable(true);

        frame.add(allPanel);
        frame.setJMenuBar(menuBar);
        frame.setContentPane(allPanel);
    }
    private void setModifyPanel(){
        modifyPanel =new ModifyPanel();
    }
    private void setToolBarPanel() {
        toolBarPanel = new ToolBarPanel();
    }
    private void setMenuBar(){
        menuBar = new JMenuBar();
        JMenu save = new JMenu("save");
        JMenuItem saveAsPNG = new JMenuItem("Save as PNG");
        save.add(saveAsPNG);
        menuBar.add(save);
        //TODO: implement save function
    }

    private void initLatexOutputPanel(){
        latexOutput = new JPanel(new BorderLayout());
        latexOutputText = new JTextField("");
        latexOutputText.setColumns(20);
        latexCopyButton = new JButton("Copy");
        latex.root = null;
        latexCopyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(latexOutputText.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                repaintShowpanel();
            }
        });
        latexOutputText.setEditable(false);
        latexOutput.add(latexOutputText, BorderLayout.CENTER);


        latexOutput.add(latexCopyButton, BorderLayout.EAST);

        latexClearButton = new JButton("Clear");

        latexClearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                latex.root = null;
                repaintShowpanel();
            }
        });
        latexOutput.add(latexClearButton, BorderLayout.WEST);
    }

    private void initShowPanel() {
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(new JLabel());
        showPanel.setBackground(Color.white);
        latex = new Latex();
        showPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(builder.nowAtom != null){
                    boolean a = latex.addAtom(builder.nowAtom,e.getX(),e.getY());
                    if(!a) {
                        return;
                    }
                    toolBarPanel.clearSelected();
                    builder.nowAtom = null;
                    repaintShowpanel();
                }
            }
        });
        showPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaintShowpanel();
            }
        });
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
        Graphics g = showPanel.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,showPanel.getWidth(),showPanel.getHeight());
        latexOutputText.setText(latex.draw(showPanel.getGraphics()));
    }


}
