import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollapsiblePanel extends JSplitPane {
    JPanel itemToolPanel;
    JPanel itemConfigPanel;
    ArrayList<String> iconDB=new ArrayList<String>();
    CollapsiblePanel(){
        super(JSplitPane.VERTICAL_SPLIT);
        itemToolPanel = new JPanel(new FlowLayout());
        itemConfigPanel = new JPanel(null);
        setLeftComponent(itemToolPanel);
        setRightComponent(itemConfigPanel);

        for (int iconSrc=0;iconSrc<5;iconSrc++){
            iconDB.add("iconSrc");
            addToolbutton(String.valueOf(iconDB.get(iconSrc)));
        }
//        itemToolPanel.add(new JLabel("test"));
        setDividerLocation(250);

    }
    void addToolbutton(String iconSrc){
        ToolButton label = new ToolButton(iconSrc);
        itemToolPanel.add(label);
    }
}
