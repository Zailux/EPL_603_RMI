package frontend;

import rmi.models.appointment.Appointment;
import rmi.models.report.MonthlyDrugs.MonthlyDrugs;
import rmi.models.report.MonthlyPatients.MonthlyPatients;
import rmi.models.report.WeeklyAmountsPerDrug.WeeklyAmountsPerDrug;
import rmi.models.report.WeeklyPatients.WeeklyPatients;
import rmi.models.report.WeeklyPatientsPerType.WeeklyPatientsPerType;
import rmi.services.appointment.AppointmentService;
import rmi.services.report.ReportService;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HealthServiceManagement {
    private JPanel HealthServiceManagement;
    private JButton EXITButton;
    private JButton generateReportsButton;
    public JLabel report;
    public static JFrame frame1 = new JFrame("Health Service Management");

    public HealthServiceManagement() {
        $$$setupUI$$$();
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        generateReportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReportService service = null;

                try {
                    service = (ReportService) Naming.lookup("rmi://localhost:5099/report");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                JLabel label = new JLabel("");
                JTextField field1 = new JTextField("");
                JTextField field2 = new JTextField("");

                JPanel panel2 = new JPanel(new GridLayout(10, 10));
                panel2.add(new JLabel("Generated Reports"));

                panel2.add(new JLabel("Month: "));
                panel2.add(field1);

                panel2.add(new JLabel("Year: "));
                panel2.add(field2);

                int result = JOptionPane.showConfirmDialog(null, panel2, "Prescription Created",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


                MonthlyDrugs monthlydr = null;
                MonthlyPatients monthlypat = null;
                WeeklyPatients weekpat = null;

                if (result == JOptionPane.OK_OPTION) {
                    String month = field1.getText();
                    String year = field2.getText();

                    //List arr =[];
                    List<WeeklyPatientsPerType> weeklypatienttype = new ArrayList<WeeklyPatientsPerType>();
                    List<WeeklyAmountsPerDrug> weeklydrugs = new ArrayList<WeeklyAmountsPerDrug>();

                    try {
                        monthlypat = service.fetchMonthlyPatients(month, year);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        monthlydr = service.fetchMonthlyDrugs(month, year);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        weekpat = service.fetchWeeklyPatients();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        weeklypatienttype = service.fetchWeeklyPatientsPerType();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        weeklydrugs = service.fetchWeeklyAmountsPerDrug();
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                    String monthlypatient = monthlypat.getMonthly_patients().toString();
                    String monthlydrugs = monthlydr.getMonthly_drugs().toString();
                    String weeklypatients = weekpat.getWeekly_patients().toString();


                    DefaultListModel<String> l1 = new DefaultListModel<>();
                    l1.addElement("Reports: ");
                    l1.addElement("\n\n\n");

                    l1.addElement("Total patient attendant: ");
                    l1.addElement(monthlypatient);
                    l1.addElement("\n\n\n");

                    l1.addElement("Total drugs attendant: ");
                    l1.addElement(monthlydrugs);
                    l1.addElement("\n\n\n");

                    l1.addElement("Weekly patient attendant: ");
                    l1.addElement(weeklypatients);
                    l1.addElement("\n\n\n");

                    l1.addElement("Weekly Patients per Type : ");
                    WeeklyPatientsPerType wpp;
                    String temp;
                    for (int i = 0; i < weeklypatienttype.size(); i++) {
                        wpp = weeklypatienttype.get(i);
                        temp = "Type:  " + wpp.getType() + "    Weekly patients:  " + wpp.getWeekly_patients();
                        l1.addElement("\n\n\n");
                        l1.addElement(temp);
                    }

                    l1.addElement("\n\n");
                    l1.addElement("Weekly amount of drugs : ");
                    WeeklyAmountsPerDrug apd;
                    String temp1;
                    for (int i = 0; i < weeklydrugs.size(); i++) {
                        apd = weeklydrugs.get(i);
                        temp1 = "Drug ID:  " + apd.getId() + "    Drug Brand:  " + apd.getBrand() + "Drug Description:  " + apd.getDescription()
                                + "    Quantity:  " + apd.getMonthly_drugs();
                        l1.addElement("\n\n");
                        l1.addElement("Drug ID:  " + apd.getId() + "    Drug Brand:  " + apd.getBrand());
                        l1.addElement("Drug Description:  " + apd.getDescription() + "    Quantity:  " + apd.getMonthly_drugs());
                    }

                    JFrame f;
                    f = new JFrame();
                    JList<String> list = new JList<>(l1);
                    list.setBounds(0, 0, 1000, 1000);
                    f.add(list);
                    list.setFont(new Font(null, Font.BOLD, 16));
                    f.setSize(1000, 1000);
                    f.setLayout(null);
                    f.setVisible(true);

                }
            }
        });
    }

    public static void window() {
        frame1.setContentPane(new HealthServiceManagement().HealthServiceManagement);
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.pack();
        frame1.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        HealthServiceManagement = new JPanel();
        HealthServiceManagement.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        EXITButton = new JButton();
        Font EXITButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, EXITButton.getFont());
        if (EXITButtonFont != null) EXITButton.setFont(EXITButtonFont);
        EXITButton.setText("EXIT");
        HealthServiceManagement.add(EXITButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 3, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        HealthServiceManagement.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        report = new JLabel();
        Font reportFont = this.$$$getFont$$$(null, -1, 14, report.getFont());
        if (reportFont != null) report.setFont(reportFont);
        report.setText("");
        HealthServiceManagement.add(report, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generateReportsButton = new JButton();
        generateReportsButton.setText("Generate Reports");
        HealthServiceManagement.add(generateReportsButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return HealthServiceManagement;
    }


}


