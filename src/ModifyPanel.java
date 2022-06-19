import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JSplitPane {
    JPanel buttonBlock,textBlock;
    JTextField iconAsk,iconname,latexAsk,latexCode;
    JButton newBtn=new JButton("新增") ,delBtn=new JButton("刪除");
    ModifyPanel(){
        super(JSplitPane.VERTICAL_SPLIT);

        buttonBlock=new JPanel(new GridLayout(1,2));

        newBtn.setFont(new Font("標楷體", Font.BOLD, 18));
        delBtn.setFont(new Font("標楷體", Font.BOLD, 18));

        buttonBlock.add(newBtn);
        buttonBlock.add(delBtn);

        textBlock = new JPanel(new GridLayout(2,3));

        iconAsk=new JTextField("目前ID");
        iconAsk.setEditable(false);
        iconname=new JTextField();
        latexAsk=new JTextField("latex代表字串");
        latexAsk.setEditable(false);
        latexCode=new JTextField();

        iconAsk.setFont(new Font("標楷體", Font.BOLD, 18));
        iconname.setFont(new Font("標楷體", Font.BOLD, 18));
        latexAsk.setFont(new Font("標楷體", Font.BOLD, 18));
        latexCode.setFont(new Font("標楷體", Font.BOLD, 18));

        textBlock.add(iconAsk);
        textBlock.add(iconname);
        textBlock.add(latexAsk);
        textBlock.add(latexCode);

        setLeftComponent(textBlock);
        setRightComponent(buttonBlock);
        setResizeWeight(0.8);;

    }
}
