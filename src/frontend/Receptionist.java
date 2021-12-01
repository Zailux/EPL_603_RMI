package frontend;

import rmi.models.appointment.Appointment;
import rmi.models.consultation.Consultation;
import rmi.models.patient.Patient;
import rmi.models.treatment.Treatment;
import rmi.services.appointment.AppointmentService;
import rmi.services.consultation.ConsultationService;
import rmi.services.patient.PatientService;
import rmi.services.treatment.TreatmentService;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

public class Receptionist {
    private JButton EXITButton;
    public JPanel receptionist;
    private JButton scheduleAppointmentButton;
    private JButton patientKeptsAppointmentButton;
    private JButton patientSearchButton;
    private JButton repeaterPrescriptionsButton;
    public JLabel result;
    private JButton showAppointmentsButton;
    private JButton createPatientButton;
    public static JFrame frame2 = new JFrame("Receptionist");

    public Receptionist() {
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        patientSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientService service = null;
                Patient patient = null;

                try {
                    service = (PatientService) Naming.lookup("rmi://localhost:5099/patient");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                //Object id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                try {
                    patient = service.fetchPatient(idgiven);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                String value = "Patient ID: " + patient.getId().toString() + " Patient Name: " + patient.getName() + " Patient Email: " + patient.getEmail();


                result.setText(value);

            }
        });

        createPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PatientService service = null;
                Patient patient = null;

                try {
                    service = (PatientService) Naming.lookup("rmi://localhost:5099/patient");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                JLabel label = new JLabel("");
                JTextField field3 = new JTextField("");
                JTextField field2 = new JTextField("");
                JTextField field1 = new JTextField("");

                JPanel panel = new JPanel(new GridLayout(10, 10));


                panel.add(new JLabel("Name: "));
                panel.add(field2);

                panel.add(new JLabel("Email address: "));
                panel.add(field1);

                panel.add(new JLabel("Create patient "));

                panel.add(new JLabel("Risk Indicator "));
                String t[] = {
                        "low", "medium", "high"};
                JComboBox cb = new JComboBox(t);
                panel.add(cb);

                panel.add(new JLabel("History Of SelfHarm "));
                String t1[] = {
                        "True", "False"};
                JComboBox cb1 = new JComboBox(t1);
                panel.add(cb1);

                int result = JOptionPane.showConfirmDialog(null, panel, "Patient Created",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String risk = cb.getItemAt(cb.getSelectedIndex()).toString();
                    String history = cb1.getItemAt(cb1.getSelectedIndex()).toString();
                    boolean historyflag;
                    if (history.equals("True"))
                        historyflag = true;
                    else
                        historyflag = false;

                    String name = (field2.getText());
                    String mail = (field1.getText());


                    try {
                        patient = service.createPatient(name, mail, historyflag, risk);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Created patient successfully");
                    System.out.println(field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Patient not created");
                }
            }
        });

        scheduleAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog(frame2, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                AppointmentService service = null;
                try {
                    service = (AppointmentService) Naming.lookup("rmi://localhost:5099/appointment");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                JLabel label = new JLabel("");
                JTextField field1 = new JTextField("");
                JTextField field3 = new JTextField("");
                JTextField field2 = new JTextField("");

                JPanel panel1 = new JPanel(new GridLayout(10, 10));
                panel1.add(new JLabel("Schedule Appointment for patient " + id));

                panel1.add(new JLabel("Appointment date: "));
                Component nc = panel1.add(field1);

                panel1.add(new JLabel("Doctor id: "));
                Component di = panel1.add(field2);

                panel1.add(new JLabel("Comment: "));
                Component com = panel1.add(field3);
                String comment = com.toString();


                int result = JOptionPane.showConfirmDialog(null, panel1, "Schedule Appointment",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    int doctorID = Integer.parseInt(field2.getText());
                    String dategiven = field1.getText();

                    long millis = System.currentTimeMillis();
                    Date date = new Date(millis);
                    System.out.println(date);

                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
                    java.util.Date date1 = null;
                    try {
                        date1 = sdf1.parse(dategiven);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    Date sqlStartDate = new Date(date1.getTime());

                    try {
                        service.createAppointment(idgiven, doctorID, sqlStartDate, date, true);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Appointment for patient " + id + " scheduled");
                    System.out.println(" " + field1.getText()
                            + " " + field2.getText() + " " + field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Cancel procedure");
                }
            }
        });

        patientKeptsAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int patientid = 1;
                int apointid = 2;

                AppointmentService service = null;
                Appointment appointment = null;

                try {
                    service = (AppointmentService) Naming.lookup("rmi://localhost:5099/appointment");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }


                AppointmentService service1 = null;
                Appointment appointment1 = null;

                try {
                    service1 = (AppointmentService) Naming.lookup("rmi://localhost:5099/appointment");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                String id = JOptionPane.showInputDialog(frame2, "Enter appointment id:");
                int appointmentgiven = Integer.parseInt(id);

                try {
                    appointment = service.fetchAppointment(appointmentgiven);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                int pid = appointment.getP_id();
                int uid = appointment.getU_id();
                Date date = appointment.getDate();
                Date created = appointment.getCreated();


                try {
                    appointment = service.updateAppointment(appointmentgiven, pid, uid, appointment.getDate(), appointment.getCreated(), false);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if ((appointmentgiven == appointment.getId())) {
                    JOptionPane.showMessageDialog(null, "Appointment " + appointmentgiven + " of patient " + appointmentgiven + " cancel");
                } else {
                    JOptionPane.showMessageDialog(null, "Appointment or patient not found ");

                }
            }
        });

        showAppointmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AppointmentService service = null;
                Appointment appointment = null;

                try {
                    service = (AppointmentService) Naming.lookup("rmi://localhost:5099/appointment");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                String id = JOptionPane.showInputDialog(frame2, "Enter doctor id:");
                int idgiven = Integer.parseInt(id);
                //List arr =[];
                List<Appointment> arr = new ArrayList<Appointment>();

                try {
                    arr = service.fetchDoctorAppointments(idgiven);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                String value = "";
                Appointment ap;
                Vector res = new Vector();
                String temp = "";
                DefaultListModel<String> l1 = new DefaultListModel<>();
                l1.addElement("Appointments for doctor " + id);
                l1.addElement("\n\n\n");

                for (int i = 0; i < arr.size(); i++) {
                    ap = arr.get(i);
                    temp = "Appointment ID:  " + ap.getId().toString() + "    Patient ID:  " + ap.getP_id() + "    Appointment Date:  " + ap.getDate();
                    l1.addElement(temp);
                }
                JFrame f;
                f = new JFrame();
                JList<String> list = new JList<>(l1);
                list.setBounds(0, 0, 750, 750);
                f.add(list);
                list.setFont(new Font(null, Font.BOLD, 16));
                f.setSize(750, 750);
                f.setLayout(null);
                f.setVisible(true);

            }
        });

        repeaterPrescriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JLabel label = new JLabel("");
                JTextField field1 = new JTextField("");
                JTextField field2 = new JTextField("");

                JPanel panel2 = new JPanel(new GridLayout(10, 10));
                panel2.add(new JLabel("Prescriptions repeat"));

                panel2.add(new JLabel("Patient id:: "));
                panel2.add(field1);

                panel2.add(new JLabel("Doctor id: "));
                panel2.add(field2);

                int result = JOptionPane.showConfirmDialog(null, panel2, "Prescription Created",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    int patientid = Integer.parseInt(field1.getText());
                    int doctorid = Integer.parseInt(field2.getText());




                    


                    JOptionPane.showMessageDialog(null, "Prescription for patient " + patientid + " created from doctor " + doctorid);
                } else {
                    JOptionPane.showMessageDialog(null, "Prescription not created");
                }


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
        receptionist.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        EXITButton = new JButton();
        Font EXITButtonFont = this.$$$getFont$$$(null, Font.BOLD, 20, EXITButton.getFont());
        if (EXITButtonFont != null) EXITButton.setFont(EXITButtonFont);
        EXITButton.setText("EXIT");
        receptionist.add(EXITButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHEAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientSearchButton = new JButton();
        patientSearchButton.setText("Patient Search");
        receptionist.add(patientSearchButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        result = new JLabel();
        Font resultFont = this.$$$getFont$$$(null, -1, 16, result.getFont());
        if (resultFont != null) result.setFont(resultFont);
        result.setText("");
        receptionist.add(result, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAppointmentsButton = new JButton();
        showAppointmentsButton.setText("Show appointments");
        receptionist.add(showAppointmentsButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeaterPrescriptionsButton = new JButton();
        repeaterPrescriptionsButton.setText("Repeater prescriptions");
        receptionist.add(repeaterPrescriptionsButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createPatientButton = new JButton();
        createPatientButton.setText("Create Patient");
        receptionist.add(createPatientButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scheduleAppointmentButton = new JButton();
        scheduleAppointmentButton.setText("Schedule appointment");
        receptionist.add(scheduleAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patientKeptsAppointmentButton = new JButton();
        patientKeptsAppointmentButton.setText("Patient kepts appointment");
        receptionist.add(patientKeptsAppointmentButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
