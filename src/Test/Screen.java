import javax.swing.*;
import java.awt.*;

public class Screen{
    private JFrame frame;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    public void init() {
        frame = new JFrame();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel1.add(new JButton("AA"));
        panel3.setSize(100, 100);
        panel4.setSize(100, 100);
        panel3.setBackground(Color.cyan);
        panel4.setBackground(Color.blue);
        panel3.add(panel1);
        panel3.add(panel2);
        panel4.setBorder(null);
        panel4.setLocation(100, 100);
        JTextPane textPanel = new JTextPane();
        textPanel.setText("Hello");
        textPanel.setSize(100, 100);
        textPanel.setBackground(Color.red);
        textPanel.setLocation(100, 100);
        textPanel.setEditable(false);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Export");
        JMenu menu2= new JMenu("Copy Image");
        JMenuItem menuItem = new JMenuItem("Open");
        menu.add(menuItem);
        menuBar.add(menu2);
        menuBar.add(menu);
//        panel2.add(new DraggableComponent());


        frame.setJMenuBar(menuBar);
        panel4.add(textPanel);
//        panel4.add(new JLabel("Hello"));
        panel4.add(new JButton("copy"));
        frame.setLayout(null);
        frame.add(panel3);
        frame.add(panel4);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
