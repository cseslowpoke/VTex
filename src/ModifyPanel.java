import javax.swing.*;
import java.awt.*;

public class ModifyPanel extends JSplitPane {
    JPanel buttonBlock,textBlock;
    JTextField iconAsk,iconname,latexAsk,latexCode;
    JButton newBtn=new JButton("修改屬性") ,delBtn=new JButton("刪除白板中被選擇的字體");
    ModifyPanel(){
        super(JSplitPane.VERTICAL_SPLIT);

        buttonBlock=new JPanel(new GridLayout(1,2));

        newBtn.setFont(new Font("標楷體", Font.BOLD, 18));
        delBtn.setFont(new Font("標楷體", Font.BOLD, 18));

        buttonBlock.add(newBtn);
        buttonBlock.add(delBtn);

        textBlock = new JPanel(new GridLayout(2,2));

        iconAsk=new JTextField("字體");
        iconAsk.setEditable(false);
        iconname=new JTextField("請輸入字體名稱");
        latexAsk=new JTextField("顏色");
        latexAsk.setEditable(false);
        latexCode=new JTextField("請輸入(r,g,b)");

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
