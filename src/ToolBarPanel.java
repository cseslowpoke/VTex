import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ToolBarPanel extends JPanel {
    int Selected;
    ArrayList<String> iconDB=new ArrayList<String>(); //latex要有甚麼數學符號仍需要討論
    ArrayList<ToolButton> buttonDB = new ArrayList<ToolButton>();

    ToolBarPanel(){
        super();
        String[] s = {"1","2","3","4","5","6","7","8","9","0","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","\\Alpha","\\Beta","\\Gamma","\\Delta","\\Epsilon","\\Zeta","\\Eta","\\Theta","\\Iota","\\Kappa","\\Lambda","\\Mu","\\Nu","\\Xi","\\O","\\Pi","\\Rho","\\Sigma","\\Tau","\\Upsilon","\\Phi","\\Chi","\\Psi","\\Omega","\\alpha","\\beta","\\gamma","\\delta","\\epsilon","\\zeta", "\\eta","\\theta","\\iota" ,"\\kappa","\\lambda","\\mu", "\\nu" ,"\\xi" ,"\\o","\\pi", "\\rho","\\sigma" ,"\\tau" ,"\\upsilon","\\phi" ,"\\chi" ,"\\psi" ,"\\omega"};//        add(new JLabel("toolPanel"));
        for(int i=0;i<10;i++){
            addArrayList("src/icon/unknown.png"); //正式版本要刪掉!!!
        }

        for (int iconSrc=0;iconSrc<s.length;iconSrc++){  //把我們要的數學符號輸入到iconDB之內就能把button自動產生出來
            ToolButton button = new ToolButton(Type.SYMBOL,s[iconSrc]);
            final int a = iconSrc;
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDB.get(Selected).unSelected();
                    Window.builder.setAtom(button.createAtom());
                    button.selected();
                    Selected = a;
                }
            });
            buttonDB.add(button);
            add(button);
        }

        setBackground(Color.white);

    }

    void addArrayList(String iconSrc) {
        iconDB.add(iconSrc);
    }

    public void clearSelected() {
        buttonDB.get(Selected).unSelected();
    }
}
