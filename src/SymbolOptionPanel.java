import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SymbolOptionPanel extends OptionPanel{
    int font;
    JPanel fontPanel;
    JCheckBox boldButton;
    JCheckBox ItalicButton;

    JPanel buttonPanel;

    JButton confirmButton;


    SymbolOptionPanel(SymbolAtom Atom) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        fontPanel = new JPanel();
        fontPanel.setLayout(new BoxLayout(fontPanel,BoxLayout.X_AXIS));
        fontPanel.add(new JLabel("粗/斜體:"));
        boldButton = new JCheckBox("粗體");
        ItalicButton = new JCheckBox("斜體");
        fontPanel.add(boldButton);
        fontPanel.add(ItalicButton);
        add(fontPanel);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        confirmButton = new JButton("確認");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Atom.setBoldFace(boldButton.isSelected());
                Atom.setItalicFace(ItalicButton.isSelected());
            }
        });
        buttonPanel.add(confirmButton);
        add(buttonPanel);


    }
}
