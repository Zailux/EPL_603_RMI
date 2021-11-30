package frontend;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Receptionist {
    private JButton EXITButton;
    public JPanel receptionist;
    private JButton scheduleAppointmentButton;
    private JButton patientKeptsAppointmentButton;
    private JButton patientSearchButton;
    private JButton repeaterPrescriptionsButton;
    public JLabel result;
    private JButton showAppointmentsButton;
    public static JFrame frame2 = new JFrame("Receptionist");

    public Receptionist() {
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        String myArray[] = new String[2];
        myArray[0] = "Kostis";
        myArray[1] = "En kalos";

        patientSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "";

                //Object id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);


                if (idgiven == 1) {
                    for (int j = 0; j < myArray.length; j++) {
                        value += myArray[j] + "  ";
                    }
                    result.setText(value);
                }

            }
        });
        patientKeptsAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int patientid = 1;
                int apointid = 2;

                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                String appoin = JOptionPane.showInputDialog(frame2, "Enter treatment id:");
                int appointmentgiven = Integer.parseInt(appoin);

                if ((idgiven == patientid) & (appointmentgiven == apointid)) {
                    JOptionPane.showMessageDialog(null, "Appointment " + appointmentgiven + " of patient " + idgiven + " cancel");
                } else {
                    JOptionPane.showMessageDialog(null, "Appointment or patient not found ");

                }
            }
        });
        scheduleAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                JLabel label = new JLabel("");
                JTextField field1 = new JTextField("");
                JTextField field3 = new JTextField("");
                JTextField field2 = new JTextField("");

                JPanel panel1 = new JPanel(new GridLayout(10, 10));
                panel1.add(new JLabel("Schedule Appointment for patient " + id));

                panel1.add(new JLabel("Appointment date: "));
                Component nc = panel1.add(field1);
                String appointmentdate = nc.toString();

                panel1.add(new JLabel("Doctor id: "));
                Component di = panel1.add(field2);
                String doctorid = di.toString();

                panel1.add(new JLabel("Comment: "));
                Component com = panel1.add(field3);
                String comment = com.toString();

                int result = JOptionPane.showConfirmDialog(null, panel1, "Schedule Appointment",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "Appointment for patient " + id + " scheduled");
                    System.out.println(" " + field1.getText()
                            + " " + field2.getText() + " " + field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Cancel procedure");
                }
            }
        });
        repeaterPrescriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                JOptionPane.showMessageDialog(null, "Prescriptions patient " + id + " created");

            }
        });
        showAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String appointment[] = new String[2];
                appointment[0] = "2021/12/2";
                appointment[1] = "2021/11/30";
                String value = "";

                for (int j = 0; j < appointment.length; j++) {
                    value += (j + 1) + "." + appointment[j] + "      ";
                }
                result.setText(value);

            }
        });
    }

    public static void window() {
        frame2.setContentPane(new Receptionist().receptionist);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
    }

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
        receptionist = new JPanel();
        receptionist.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        EXITButton = new JButton();
        Font EXITButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, EXITButton.getFont());
        if (EXITButtonFont != null) EXITButton.setFont(EXITButtonFont);
        EXITButton.setText("EXIT");
        receptionist.add(EXITButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scheduleAppointmentButton = new JButton();
        scheduleAppointmentButton.setText("Schedule appointment");
        receptionist.add(scheduleAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientKeptsAppointmentButton = new JButton();
        patientKeptsAppointmentButton.setText("Patient kepts appointment");
        receptionist.add(patientKeptsAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientSearchButton = new JButton();
        patientSearchButton.setText("Patient Search");
        receptionist.add(patientSearchButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        result = new JLabel();
        Font resultFont = this.$$$getFont$$$(null, -1, 14, result.getFont());
        if (resultFont != null) result.setFont(resultFont);
        result.setText("");
        receptionist.add(result, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAppointmentsButton = new JButton();
        showAppointmentsButton.setText("Show appointments");
        receptionist.add(showAppointmentsButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeaterPrescriptionsButton = new JButton();
        repeaterPrescriptionsButton.setText("Repeater prescriptions");
        receptionist.add(repeaterPrescriptionsButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return receptionist;
    }


}
