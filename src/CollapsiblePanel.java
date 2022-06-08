import javax.swing.*;

public class CollapsiblePanel extends JSplitPane {
    JPanel itemToolPanel;
    JPanel itemConfigPanel;
    CollapsiblePanel(){
        super(JSplitPane.VERTICAL_SPLIT);
        itemToolPanel = new JPanel();
        itemConfigPanel = new JPanel();
        setLeftComponent(itemToolPanel);
        setRightComponent(itemConfigPanel);


        setDividerLocation(250);

    }
}
