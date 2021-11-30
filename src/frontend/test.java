//The applications first or the main frame
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class test extends JFrame {

    private JButton myFirstButton;
    private JButton mySecondButton;

    // Constructor for a new frame

    public test() {


        myFirstButton = new JButton("First Frame");
        myFirstButton.setFont(new Font("Arial", Font.BOLD, 18));
        myFirstButton.setBackground(Color.red);

        mySecondButton = new JButton("New Frame");
        mySecondButton.setFont(new Font("Arial", Font.BOLD, 18));
        mySecondButton.setBackground(Color.green);

        Container c = getContentPane();
        FlowLayout fl = new FlowLayout(FlowLayout.LEFT);
        c.setLayout(fl);

        c.add(myFirstButton);
        c.add(mySecondButton);

        ButtonHandler handler = new ButtonHandler();    //creation of a new Object
        myFirstButton.addActionListener(handler);          // Attach/register handler to myFirstButton
        mySecondButton.addActionListener(handler);        //Attach/register handler to mySecondButton

        setSize(400, 300);
        show();
    }


    public static void main(String[] args) {

        // Make frame
        test f = new test();

        f.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {

                        // This closes the window and terminates the
                        // Java Virtual Machine in the event that the
                        // Frame is closed by clicking on X.
                        System.out.println("Exit via windowClosing.");
                        System.exit(0);
                    }
                }
        );
    } // end of main

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }


    // inner class for button event handling
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == myFirstButton) {
                new NewFrame1();

            }
            if (e.getSource() == mySecondButton) {
                new NewFrame2();
            }
        }
    } // end of inner class

    //import statements here
    public class NewFrame1 extends JFrame implements ActionListener {
        //initialises the frame and opens it
        public NewFrame1() {
            JButton open = new JButton("New Window");
            open.addActionListener(this);
            add(open);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent event) {
            //code for the new frame
        }
    }

    //import statements here
    public class NewFrame2 extends JFrame implements ActionListener {
        //initialises the frame and opens it
        public NewFrame2() {
            JButton open = new JButton("New Window");
            open.addActionListener(this);
            add(open);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent event) {
            //code for the new frame
        }
    }

} // end of outer class
