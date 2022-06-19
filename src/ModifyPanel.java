import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JSplitPane {
    OptionPanel mainPanel;
    JPanel buttonBlock,textBlock;
    JTextField iconAsk,iconname,latexAsk,latexCode;
    JButton newBtn=new JButton("修改屬性") ,delBtn=new JButton("刪除白板中被選擇的字體");
    ModifyPanel(){
        super(JSplitPane.VERTICAL_SPLIT);
//        mainPanel = new SymbolOptionPanel(new SymbolAtom("a"));
//        add(mainPanel);

        mainPanel = new OptionPanel();
        add(mainPanel);
    }

    public void setMainPanel(Atom atom) {
        remove(mainPanel);
        if(atom instanceof SymbolAtom) {
            mainPanel = new SymbolOptionPanel((SymbolAtom) atom);
            add(mainPanel);
        }
    }
}
