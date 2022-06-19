
import org.scilab.forge.jlatexmath.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolButton extends JLabel {
    public String iconSrc;
    Type TYPE;
    String value;


    public ToolButton(Type TYPE, String value) {

//        super(new ImageIcon());

        this.TYPE = TYPE;
        this.value = value;
        setPreferredSize(new Dimension(50  ,50));

    }
    public Atom createAtom() {
        if(TYPE == Type.SYMBOL) {
            return new SymbolAtom(value);
        }
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        TeXFormula formula = new TeXFormula(value);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 50);
        BufferedImage image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        //System.out.println(icon.getIconHeight());
        icon.paintIcon(jl, g2, 0, 0);
        g.drawImage(image,0,0,null);

    }

    public void selected() {
        Border Border = BorderFactory.createLineBorder(Color.BLUE,5);
        setBorder(Border);
    }
    public void unSelected() {
        setBorder(null);
    }

}
















