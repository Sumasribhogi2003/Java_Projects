import javax.swing.*;
import java.io.*;
import java.awt.event.*;

class TextEditor extends JFrame implements ActionListener {
    // Text component
    JTextArea t;

    // Frame
    JFrame f;

    // Constructor
    TextEditor() {
        // Create a frame
        f = new JFrame("Text Editor");

        // Text component
        t = new JTextArea();

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create a menu for File
        JMenu m1 = new JMenu("File");

        // Create menu items for File
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        // Add action listener for File menu items
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        // Add menu items to File menu
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        // Create a menu for Edit
        JMenu m2 = new JMenu("Edit");

        // Create menu items for Edit
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");

        // Add action listener for Edit menu items
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        // Add menu items to Edit menu
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenuItem mc = new JMenuItem("Close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Cut")) {
            t.cut();
        } else if (s.equals("Copy")) {
            t.copy();
        } else if (s.equals("Paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            // Create a file chooser
            JFileChooser j = new JFileChooser("f:");

            // Show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write to file
                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(f, "The user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                // Print the text area content
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Create a file chooser
            JFileChooser j = new JFileChooser("f:");

            // Show the open dialog
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String to store file content
                    String s1 = "", sl = "";

                    // File reader
                    FileReader fr = new FileReader(fi);

                    // Buffered reader
                    BufferedReader br = new BufferedReader(fr);

                    // Initialize sl
                    sl = br.readLine();

                    // Read the file content
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    // Set the text area content
                    t.setText(sl);

                    // Close the BufferedReader
                    br.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(f, "The user cancelled the operation");
        } else if (s.equals("New")) {
            // Clear the text area
            t.setText("");
        } else if (s.equals("Close")) {
            // Close the frame
            f.setVisible(false);
        }
    }

    // Main method
    public static void main(String args[]) {
        // Create an instance of TextEditor
        new TextEditor();
    }
}
