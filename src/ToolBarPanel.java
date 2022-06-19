import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolBarPanel extends JPanel {
    ArrayList<String> iconDB=new ArrayList<String>(); //latex要有甚麼數學符號仍需要討論
    ToolBarPanel(){
        setLayout(new GridLayout(4,10));
        for(int i=0;i<40;i++){
            addArrayList("src/icon/unknown.png"); //正式版本要刪掉!!!
        }

        for (int iconSrc=0;iconSrc<iconDB.size();iconSrc++){  //把我們要的數學符號輸入到iconDB之內就能把button自動產生出來
            addToolbutton(String.valueOf(iconDB.get(iconSrc)));
        }

    }
    void addToolbutton(String iconSrc){
        ToolButton button = new ToolButton(iconSrc);
        add(button);
    }

    void addArrayList(String iconSrc) {
        iconDB.add(iconSrc);
    }
}
