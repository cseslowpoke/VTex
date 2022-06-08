import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class CollapsiblePanel extends JSplitPane {
    JPanel itemToolPanel;
    JPanel itemConfigPanel;
    CollapsiblePanel(){
        super(JSplitPane.VERTICAL_SPLIT);
        itemToolPanel = new JPanel(new FlowLayout());
        itemConfigPanel = new JPanel(null);
        setLeftComponent(itemToolPanel);
        setRightComponent(itemConfigPanel);
        DraggableLabel label = new DraggableLabel("test");
        itemToolPanel.add(label);
//        itemToolPanel.add(new JLabel("test"));
        setDividerLocation(250);

    }

}
