import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JLabel {
    private String iconSrc;

    public ToolButton(){
        this("src/icon/unknown.png");
    }  //defalut用預設圖片

    public ToolButton(String iconSrc) {
        iconSrc="src/icon/unknown.png"; //後面能用this.iconSrc=iconSrc; 然後在白板抓圖時，呼叫iconSrc的路徑

        //可以設定atom然後再新增白板時有不同功能!!!(跟隊友討論一下，我是latex拉基

        ImageIcon originIcon=new ImageIcon(iconSrc);
        Image scaleImage=originIcon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT); //讓每個Icon看起來都長一樣

        setIcon(new ImageIcon(scaleImage));
    }
}
















