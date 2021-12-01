package frontend;

import rmi.models.consultation.Consultation;
import rmi.models.record.Record;
import rmi.models.treatment.Treatment;
import rmi.models.user.User;
import rmi.services.consultation.ConsultationService;
import rmi.services.record.RecordService;
import rmi.services.treatment.TreatmentService;
import rmi.services.user.UserService;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static frontend.App.name;

public class ClinicalStaff {
    private JPanel ClinicalStaff;
    private JButton viewPatientRecordsButton;
    private JButton updateRecordsButton;
    public JLabel result;
    private JButton EXITButton;
    private JButton createTreatmentButton;
    private JButton createConsultationButton;
    private JButton searchSideEffectsButton;
    public static JFrame frame1 = new JFrame("Clinical Staff");


    public ClinicalStaff() {

        viewPatientRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = "";
                int id = 1;

                //Object id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                String ids = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(ids);

                RecordService service = null;
                List<Record> records = null;
                try {
                    service = (RecordService) Naming.lookup("rmi://localhost:5099/record");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                try {
                    records = service.fetchPatientRecords(idgiven);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println(records);
            }
        });

        updateRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                String cid = JOptionPane.showInputDialog(frame1, "Enter consultation id:");
                int cidgiven = Integer.parseInt(id);

                JLabel label = new JLabel("");
                JTextField field3 = new JTextField("");


                JPanel panel = new JPanel(new GridLayout(10, 10));
                panel.add(new JLabel("Update for patient " + id));


                panel.add(new JLabel("Create Consultation for patient " + id));
                String t[] = {
                        "mental", "physical", "viral", "other"};
                JComboBox cb = new JComboBox(t);
                panel.add(cb);

                panel.add(new JLabel("Comment: "));
                Component com = panel.add(field3);
                String comment = com.toString();

                Date curruntdate = Date.from(Instant.now());
                System.out.println(curruntdate);

                boolean finished = true;

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Patient",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String data = "Selected: " + cb.getItemAt(cb.getSelectedIndex());
                    System.out.println(data);

                    JOptionPane.showMessageDialog(null, "Updated successfully for patient " + id);
                    System.out.println(field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing change for patient " + id);
                }
            }
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        createTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreatmentService service = null;
                Treatment treatment = null;

                try {
                    service = (TreatmentService) Naming.lookup("rmi://localhost:5099/treatment");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                UserService service1 = null;
                User user = null;

                try {
                    service1 = (UserService) Naming.lookup("rmi://localhost:5099/user");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                try {
                    user = service1.loginUser(name);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                int userid = user.getId();


                String id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(id);


                JLabel label = new JLabel("");
                JTextField field3 = new JTextField("");
                JTextField field2 = new JTextField("");
                JTextField field1 = new JTextField("");

                JPanel panel = new JPanel(new GridLayout(10, 10));

                panel.add(new JLabel("Medicine ID: "));
                panel.add(field2);

                panel.add(new JLabel("Quantity: "));
                panel.add(field1);


                panel.add(new JLabel("Description: "));
                Component com = panel.add(field3);
                String descr = com.toString();

                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                System.out.println(date);

                int result = JOptionPane.showConfirmDialog(null, panel, "Update Patient",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {

                    int med = Integer.parseInt(field2.getText());
                    int coun = Integer.parseInt(field1.getText());

                    try {
                        if (service != null) {
                            treatment = service.createTreatment(userid, med, date, "test", "null", coun);
                        }
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(date);
                    JOptionPane.showMessageDialog(null, "Created successfully for patient " + id);
                    System.out.println(" " + field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing created for patient " + id);
                }

            }
        });

        createConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(id);
                //user id

                ConsultationService service = null;
                Consultation consultation = null;

                try {
                    service = (ConsultationService) Naming.lookup("rmi://localhost:5099/consultation");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                UserService service1 = null;
                User user = null;

                try {
                    service1 = (UserService) Naming.lookup("rmi://localhost:5099/user");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                try {
                    user = service1.loginUser(name);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                int userid = user.getId();


                JLabel label = new JLabel("");
                JTextField field1 = new JTextField("");
                JTextField field2 = new JTextField("");
                JTextField field3 = new JTextField("");

                JPanel panel = new JPanel(new GridLayout(10, 10));
                panel.add(new JLabel("Create Consultation for patient " + id));
                String t[] = {
                        "mental", "physical", "viral", "other"};
                JComboBox cb = new JComboBox(t);
                panel.add(cb);

                panel.add(new JLabel("Comment: "));
                Component com = panel.add(field3);
                String comment = com.toString();

                //Date curruntdate = Date.from(Instant.now());

                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                System.out.println(date);


                int result = JOptionPane.showConfirmDialog(null, panel, "Create Consultation",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String data = cb.getItemAt(cb.getSelectedIndex()).toString();

                    try {
                        consultation = service.createConsultation(1, idgiven, 1, userid, data, date, true);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Created successfully for patient " + id);
                    System.out.println(data);
                    System.out.println(" " + field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing created for patient " + id);
                }


            }
        });

        searchSideEffectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = 1;
                String value = "";
                String ids = JOptionPane.showInputDialog(frame1, "Enter medicine id:");
                int idgiven = Integer.parseInt(ids);


                    result.setText(value);

            }
        });
    }

    public static void window() {
        frame1.setContentPane(new ClinicalStaff().ClinicalStaff);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }

    public static void closewin() {
        frame1.dispose();
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
        ClinicalStaff = new JPanel();
        ClinicalStaff.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        viewPatientRecordsButton = new JButton();
        viewPatientRecordsButton.setText("View patient records");
        ClinicalStaff.add(viewPatientRecordsButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        result = new JLabel();
        Font resultFont = this.$$$getFont$$$(null, -1, 16, result.getFont());
        if (resultFont != null) result.setFont(resultFont);
        result.setText("");
        ClinicalStaff.add(result, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(37, 29), null, 0, false));
        createTreatmentButton = new JButton();
        createTreatmentButton.setText("Create treatment");
        ClinicalStaff.add(createTreatmentButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateRecordsButton = new JButton();
        updateRecordsButton.setText("Update consultation ");
        ClinicalStaff.add(updateRecordsButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchSideEffectsButton = new JButton();
        searchSideEffectsButton.setText("Search side effects");
        ClinicalStaff.add(searchSideEffectsButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createConsultationButton = new JButton();
        createConsultationButton.setText("Create consultation");
        ClinicalStaff.add(createConsultationButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EXITButton = new JButton();
        Font EXITButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, EXITButton.getFont());
        if (EXITButtonFont != null) EXITButton.setFont(EXITButtonFont);
        EXITButton.setText("EXIT");
        ClinicalStaff.add(EXITButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return ClinicalStaff;
    }

}


