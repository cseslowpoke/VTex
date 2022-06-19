import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.scilab.forge.jlatexmath.*;
import org.scilab.forge.jlatexmath.Box;

public class Window {
    JFrame appFullScreenFrame;
    //ArrayList<ToolButton> DraggableComponents;
    ToolBarPanel toolBarPanel;
    //JPanel toolBarPanel;
    JPanel showPanel;
    ModifyPanel modifyPanel;
//    JPanel drawPanel;
//    JPanel textPanel;
    JSplitPane allPanel;
    JSplitPane rightPanel;
    JSplitPane leftPanel;
    JButton copyButton;
    JPanel outputPanel;
    JTextField latexText;
    JMenuBar menuBar;
    JButton latex_Src_Code_Copy_Button;
    //DragSource dragSource;
    public Window() {

        appFullScreenFrame = new JFrame("Latex小工具");
        appFullScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFullScreenFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        setShowPanel(); //白板修改(右上)
        setToolBarPanel(); //工具列修改 (左上)
        setOutputPanel();//輸出修改(右下)
        setMenuBar();//最上面的選單(頂端)
        setModifyPanel();

        rightPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, showPanel, outputPanel);
        rightPanel.setResizeWeight(0.8);
        leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, toolBarPanel, modifyPanel);
        leftPanel.setResizeWeight(0.8);
        allPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        allPanel.setResizeWeight(0.01);

        rightPanel.setOneTouchExpandable(true);
        leftPanel.setOneTouchExpandable(true);
        allPanel.setOneTouchExpandable(true);

        appFullScreenFrame.add(allPanel);
        appFullScreenFrame.setJMenuBar(menuBar);
        appFullScreenFrame.setContentPane(allPanel);

    }
    private void setShowPanel() {
        showPanel = new JPanel();
        showPanel.setLayout(new BoxLayout(showPanel, BoxLayout.Y_AXIS));
        showPanel.add(new JLabel("showPanel"));
    }

    private void setToolBarPanel() {

        toolBarPanel = new ToolBarPanel();
    }
    private void setModifyPanel(){
        modifyPanel =new ModifyPanel();
    }

    private void setMenuBar(){
        menuBar = new JMenuBar();
        JMenu save = new JMenu("save");
        JMenuItem saveAsPNG = new JMenuItem("Save as PNG");
        save.add(saveAsPNG);
        menuBar.add(save);
        //TODO: implement save function
    }

    public void run() {
        //frame.setResizable(false);
        appFullScreenFrame.setVisible(true);
    }
    public static void main(String[] args) {
        Window window = new Window();
        window.run();
    }
    private  void setOutputPanel(){
        outputPanel = new JPanel(new BorderLayout());
        latexText = new JTextField("Latex output here:test test test test test test test test test test test test test test");
        latexText.setColumns(20);
        latex_Src_Code_Copy_Button = new JButton("get Source latex code");
        latexText.setEditable(false);
        outputPanel.add(latexText, BorderLayout.CENTER);
        outputPanel.add(latex_Src_Code_Copy_Button, BorderLayout.EAST);
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
