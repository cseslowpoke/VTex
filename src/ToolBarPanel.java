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

    JPanel numberPanel;
    JPanel upperCasePanel;
    JPanel lowerCasePanel;
    JPanel upperGreekPanel;
    JPanel lowerGreekPanel;
    JPanel functionPanel;
    JPanel delimiterPanel;

    ToolBarPanel(){
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        numberPanel = new JPanel();
        upperCasePanel = new JPanel();
        lowerCasePanel = new JPanel();
        upperGreekPanel = new JPanel();
        lowerGreekPanel = new JPanel();
        functionPanel = new JPanel();
        delimiterPanel = new JPanel();

        numberPanel.setLayout(new WrapLayout());
        upperCasePanel.setLayout(new WrapLayout());
        lowerCasePanel.setLayout(new WrapLayout());
        upperGreekPanel.setLayout(new WrapLayout());
        lowerGreekPanel.setLayout(new WrapLayout());
        functionPanel.setLayout(new WrapLayout());
        delimiterPanel.setLayout(new WrapLayout());

        add(numberPanel);
        add(upperCasePanel);
        add(lowerCasePanel);
        add(upperGreekPanel);
        add(lowerGreekPanel);
        add(functionPanel);
        add(delimiterPanel);

        String[] number = {"1","2","3","4","5","6","7","8","9","0"};
        String[] upperCase = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] lowerCase = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String[] upperGreek = {"\\Alpha","\\Beta","\\Gamma","\\Delta","\\Epsilon","\\Zeta","\\Eta","\\Theta","\\Iota","\\Kappa","\\Lambda","\\Mu","\\Nu","\\Xi","\\O","\\Pi","\\Rho","\\Sigma","\\Tau","\\Upsilon","\\Phi","\\Chi","\\Psi","\\Omega"};
        String[] lowerGreek = {"\\alpha","\\beta","\\gamma","\\delta","\\epsilon","\\zeta", "\\eta","\\theta","\\iota" ,"\\kappa","\\lambda","\\mu", "\\nu" ,"\\xi" ,"\\o","\\pi", "\\rho","\\sigma" ,"\\tau" ,"\\upsilon","\\phi" ,"\\chi" ,"\\psi" ,"\\omega"};
        String[] operators = {",","+","-","\\times ","\\div ","=","\\equiv ","\\neq ","\\approx ","\\propto ","\\leq ","\\geq ","\\rightarrow ","\\subset ","\\supset ","\\subseteq ","\\supseteq ","\\in ","\\ni ","\\notin ","\\fallingdotseq "};
        String[] delimiters = {"|","\\|","(",")","[","]","\\{","\\}","\\langle ","\\rangle ","\\lfloor ","\\rfloor ","\\lceil ","\\rceil ","/","\\backslash ","\\int ","\\lim ","\\sum "};

        int iconCount = 0;
        for (int i=0;i<number.length;i++){  //把我們要的數學符號輸入到iconDB之內就能把button自動產生出來
            ToolButton button = new ToolButton(Type.SYMBOL,number[i]);
            final int a = iconCount;
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
            numberPanel.add(button);
            iconCount++;
        }
        for (int i=0;i<upperCase.length;i++){
            ToolButton button = new ToolButton(Type.SYMBOL,upperCase[i]);
            final int a = iconCount;
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
            upperCasePanel.add(button);
            iconCount++;
        }
        for (int i=0;i<lowerCase.length;i++){
            ToolButton button = new ToolButton(Type.SYMBOL,lowerCase[i]);
            final int a = iconCount;
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
            lowerCasePanel.add(button);
            iconCount++;
        }
        for (int i=0;i<upperGreek.length;i++){
            ToolButton button = new ToolButton(Type.SYMBOL,upperGreek[i]);
            final int a = iconCount;
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
            upperGreekPanel.add(button);
            iconCount++;
        }
        for (int i=0;i<lowerGreek.length;i++){
            ToolButton button = new ToolButton(Type.SYMBOL,lowerGreek[i]);
            final int a = iconCount;
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
            lowerGreekPanel.add(button);
            iconCount++;
        }

        final ToolButton sqrtButton = new ToolButton(Type.SQRT,"\\sqrt{x}");
        final int a = iconCount;
        sqrtButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonDB.get(Selected).unSelected();
                Window.builder.setAtom(sqrtButton.createAtom());
                sqrtButton.selected();
                Selected = a;
            }
        });
        buttonDB.add(sqrtButton);
        functionPanel.add(sqrtButton);
        iconCount++;

        final ToolButton fracButton = new ToolButton(Type.FRAC,"\\frac{x}{y}");
        final int b = iconCount;
        fracButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonDB.get(Selected).unSelected();
                Window.builder.setAtom(fracButton.createAtom());
                fracButton.selected();
                Selected = b;
            }
        });
        buttonDB.add(fracButton);
        functionPanel.add(fracButton);
        iconCount++;

        for(int i=0;i<operators.length;i++){
            final ToolButton op = new ToolButton(Type.SYMBOL, operators[i]);
            final int c = iconCount;
            op.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDB.get(Selected).unSelected();
                    Window.builder.setAtom(op.createAtom());
                    op.selected();
                    Selected = c;
                }
            });
            buttonDB.add(op);
            functionPanel.add(op);
            iconCount++;
        }

        for (int i=0;i<delimiters.length;i++){  //把我們要的數學符號輸入到iconDB之內就能把button自動產生出來
            ToolButton button = new ToolButton(Type.SYMBOL,delimiters[i]);
            final int c = iconCount;
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonDB.get(Selected).unSelected();
                    Window.builder.setAtom(button.createAtom());
                    button.selected();
                    Selected = c;
                }
            });
            buttonDB.add(button);
            delimiterPanel.add(button);
            iconCount++;
        }

        setBackground(Color.white);
        setSize(500,500);
    }


    public void clearSelected() {
        buttonDB.get(Selected).unSelected();
    }

    int width = 0;
    public void setWidth(int w){
        width = w;
    }
}
