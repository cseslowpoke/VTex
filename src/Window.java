import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Window {
    JFrame frame;
    ArrayList<DraggableComponent> DraggableComponents;
    CollapsiblePanel collapsiblePanel;
    JPanel atomPanel;
    JPanel configPanel;
    JPanel drawPanel;
    JPanel textPanel;
    JButton copyButton;


    public void run() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        atomPanel = new JPanel();
        configPanel = new JPanel();
        drawPanel = new JPanel();
        textPanel = new JPanel();
        initCollapsiblePanel();
        frame.add(collapsiblePanel);
        frame.add(textPanel);
        frame.add(drawPanel);
        frame.setResizable(false);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                frame.setSize(100, 100);
            }
        });
        frame.setVisible(true);
    }


    private void initCollapsiblePanel() {
        DraggableComponents = new ArrayList<DraggableComponent>();
        collapsiblePanel = new CollapsiblePanel();
        collapsiblePanel.add(atomPanel);
        collapsiblePanel.add(configPanel);
    }

    public static void main(String[] args) {
        ArrayList<SymbolAtom> atoms = new ArrayList<SymbolAtom>(10);
        for(int i = 0; i < 10; i++) {
            atoms.add(new SymbolAtom("SA"));
        }
        for(int i=0;i<9;i++) {
            atoms.get(i).setSubscriptAtom(atoms.get(i+1));
        }

        System.out.println(atoms.get(0).generate());
        Window window = new Window();
        window.run();
    }
}
