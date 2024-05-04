import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Wordcounter extends JFrame implements ActionListener {
    JTextArea ta;
    JButton b1, b2, pad;

    Wordcounter() {
        super("Word Character Count Tool - JTP");

        // Components for Word Character Counter
        ta = new JTextArea();
        ta.setBounds(50, 50, 300, 200);

        b1 = new JButton("Word");
        b1.setBounds(50, 300, 100, 30);

        b2 = new JButton("Character");
        b2.setBounds(180, 300, 100, 30);

        pad = new JButton("Pad Color");
        pad.setBounds(310, 300, 100, 30); // Adjusted position
        pad.addActionListener(this);

        b1.addActionListener(this);
        b2.addActionListener(this);

        // Adding components to the frame
        add(ta);
        add(b1);
        add(b2);
        add(pad);

        setSize(420, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String text = ta.getText();
        if (e.getSource() == b1) {
            String words[] = text.split("\\s");
            JOptionPane.showMessageDialog(this, "Total words: " + words.length);
        } else if (e.getSource() == b2) {
            JOptionPane.showMessageDialog(this, "Total Characters with space: " + text.length());
        } else if (e.getSource() == pad) {
            Color c = JColorChooser.showDialog(this, "Choose Color", Color.BLACK);
            ta.setBackground(c);
        }
    }

    public static void main(String[] args) {
        new Wordcounter();
    }
}
