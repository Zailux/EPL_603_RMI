package frontend;

import rmi.models.appointment.Appointment;
import rmi.models.consultation.Consultation;
import rmi.models.medicine.Medicine;
import rmi.models.record.Record;
import rmi.models.treatment.Treatment;
import rmi.models.user.User;
import rmi.services.consultation.ConsultationService;
import rmi.services.medicine.MedicineService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

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

                //Object id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                String id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

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

                Record rec;
                DefaultListModel<String> l1 = new DefaultListModel<>();
                l1.addElement("Records for patient " + id);
                l1.addElement("\n\n\n");

                for (int i = 0; i < records.size(); i++) {
                    rec = records.get(i);

                    l1.addElement("Consultation type:  " + rec.getConsultation_type());
                    l1.addElement("\nConsultation date:  " + rec.getConsultation_date());
                    l1.addElement("\nTreatment description:  " + rec.getTreatment_description());
                    l1.addElement("\nTreatment date:  " + rec.getTreatment_date());
                    l1.addElement("\nTreatment quantity:  " + rec.getTreatment_quantity());
                }


                JFrame f;
                f = new JFrame();
                JList<String> list = new JList<>(l1);
                list.setBounds(0, 0, 750, 750);
                f.add(list);
                list.setFont(new Font(null, Font.BOLD, 18));
                f.setSize(750, 750);
                f.setLayout(null);
                f.setVisible(true);


                System.out.println(records);
            }
        });

        updateRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultationService service = null;

                try {
                    service = (ConsultationService) Naming.lookup("rmi://localhost:5099/consultation");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }

                String id = JOptionPane.showInputDialog(frame1, "Enter patient id:");
                int idgiven = Integer.parseInt(id);

                String cid = JOptionPane.showInputDialog(frame1, "Enter consultation id:");
                int cidgiven = Integer.parseInt(cid);

                Consultation consultation = null;
                try {
                    consultation = service.fetchConsultation(cidgiven);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                JLabel label = new JLabel("");
                JTextField field3 = new JTextField("");


                JPanel panel = new JPanel(new GridLayout(10, 10));
                panel.add(new JLabel("Update for patient " + id));


                panel.add(new JLabel("Update Consultation " + cid + " for patient " + id));
                String t[] = {
                        "mental", "physical", "viral", "other"};
                JComboBox cb = new JComboBox(t);
                cb.setSelectedItem(consultation.getType());
                panel.add(cb);

                panel.add(new JLabel("Finished "));
                String t2[] = {
                        "true", "false"};
                JComboBox cb2 = new JComboBox(t2);
                cb2.setSelectedItem(String.valueOf(consultation.isFinished()));
                panel.add(cb2);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                panel.add(new JLabel("Date (dd/MM/yyyy)"));
                JTextField tf = new JTextField(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                tf.setText(LocalDate.parse(dateFormat.format(consultation.getDate()), formatter).format(formatter2));
                panel.add(tf);


                int result = JOptionPane.showConfirmDialog(null, panel, "Update Consultation",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    Date parsed = null;
                    try {
                        parsed = format.parse(tf.getText());
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    try {
                        service.updateConsultation(cidgiven, consultation.getT_id(), consultation.getP_id(), consultation.getU_id(), (String) cb.getItemAt(cb.getSelectedIndex()), sql, Boolean.valueOf((String) cb2.getItemAt(cb2.getSelectedIndex())));
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Updated successfully for consultation " + cid);
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
                String previousT = "null";
                Treatment lastTreatment = null;
                try {
                    lastTreatment = service.fetchPatientLatestTreatment(idgiven);
                    previousT = String.valueOf(lastTreatment.getId());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                JLabel label = new JLabel("");
                JTextField field3 = new JTextField("");
                JTextField field2 = new JTextField("");
                JTextField field1 = new JTextField("");

                JPanel panel = new JPanel(new GridLayout(10, 10));

                panel.add(new JLabel("Medicine ID: "));
                panel.add(field2);
                field2.setText(String.valueOf(lastTreatment.getM_id()));

                panel.add(new JLabel("Quantity: "));
                panel.add(field1);
                field1.setText(String.valueOf(lastTreatment.getQuantity()));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                panel.add(new JLabel("Date (dd/MM/yyyy)"));
                JTextField tf = new JTextField(1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                panel.add(tf);

                tf.setText(LocalDate.parse(dateFormat.format(lastTreatment.getDate()), formatter).format(formatter2));

                panel.add(new JLabel("Description: "));
                panel.add(field3);
                String description = field3.getText();
                field3.setText(lastTreatment.getDescription());
                System.out.println(description);


                int result = JOptionPane.showConfirmDialog(null, panel, "Created Treatment",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {

                    int med = Integer.parseInt(field2.getText());
                    int coun = Integer.parseInt(field1.getText());

                    try {
                        Date parsed = null;
                        try {
                            parsed = format.parse(tf.getText());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        java.sql.Date sql = new java.sql.Date(parsed.getTime());
                        assert service != null;
                        treatment = service.createTreatment(idgiven, med, sql, description, previousT, coun);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Created successfully for patient " + id);
                    System.out.println(" " + field3.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing created for patient " + id);
                }

            }
        });

        createConsultationButton.addActionListener(new

                                                           ActionListener() {
                                                               @Override
                                                               public void actionPerformed(ActionEvent e) {
                                                                   JLabel label = new JLabel("");
                                                                   JTextField field1 = new JTextField("");
                                                                   JTextField field2 = new JTextField("");
                                                                   JTextField field3 = new JTextField("");
                                                                   JTextField field4 = new JTextField("");


                                                                   JPanel panel2 = new JPanel(new GridLayout(10, 10));

                                                                   panel2.add(new JLabel("Patient id:: "));
                                                                   panel2.add(field1);

                                                                   panel2.add(new JLabel("Doctor id: "));
                                                                   panel2.add(field2);

                                                                   panel2.add(new JLabel("Treatment id: "));
                                                                   panel2.add(field3);

                                                                   panel2.add(new JLabel("Date (dd/MM/yyyy)"));
                                                                   panel2.add(field4);

                                                                   panel2.add(new JLabel("Type"));
                                                                   String t[] = {
                                                                           "mental", "physical", "viral", "other"};
                                                                   JComboBox cb = new JComboBox(t);
                                                                   panel2.add(cb);

                                                                   panel2.add(new JLabel("Finished "));
                                                                   String t2[] = {
                                                                           "true", "false"};
                                                                   JComboBox cb1 = new JComboBox(t2);
                                                                   panel2.add(cb1);


                                                                   int result = JOptionPane.showConfirmDialog(null, panel2, "Consultation Created",
                                                                           JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                                                   if (result == JOptionPane.OK_OPTION) {
                                                                       int patientid = Integer.parseInt(field1.getText());
                                                                       int doctorid = Integer.parseInt(field2.getText());
                                                                       int treatmentid = Integer.parseInt(field3.getText());

                                                                       String type = cb.getItemAt(cb.getSelectedIndex()).toString();
                                                                       String finish = cb1.getItemAt(cb1.getSelectedIndex()).toString();
                                                                       boolean finished;
                                                                       if (finish.equals("true"))
                                                                           finished = true;
                                                                       else
                                                                           finished = false;

                                                                       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                                                                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                                       DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                                                       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


                                                                       Date parsed = null;
                                                                       try {
                                                                           parsed = format.parse(field4.getText());
                                                                       } catch (ParseException ex) {
                                                                           ex.printStackTrace();
                                                                       }
                                                                       java.sql.Date sql = new java.sql.Date(parsed.getTime());

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

                                                                       try {
                                                                           consultation = service.createConsultation(treatmentid, patientid, doctorid, type, sql, finished);
                                                                       } catch (RemoteException ex) {
                                                                           ex.printStackTrace();
                                                                       } catch (SQLException ex) {
                                                                           ex.printStackTrace();
                                                                       }
                                                                       JOptionPane.showMessageDialog(null, "Created successfully for patient " + patientid);
                                                                       System.out.println(type);
                                                                       System.out.println(" " + field3.getText());
                                                                   } else {
                                                                       JOptionPane.showMessageDialog(null, "Nothing created for patient ");
                                                                   }


                                                               }
                                                           });

        searchSideEffectsButton.addActionListener(new

                                                          ActionListener() {
                                                              @Override
                                                              public void actionPerformed(ActionEvent e) {

                                                                  int id = 1;
                                                                  String value = "test";
                                                                  String ids = JOptionPane.showInputDialog(frame1, "Enter medicine id:");
                                                                  int idgiven = Integer.parseInt(ids);

                                                                  MedicineService service = null;
                                                                  try {
                                                                      service = (MedicineService) Naming.lookup("rmi://localhost:5099/medicine");
                                                                  } catch (NotBoundException ex) {
                                                                      ex.printStackTrace();
                                                                  } catch (MalformedURLException ex) {
                                                                      ex.printStackTrace();
                                                                  } catch (RemoteException ex) {
                                                                      ex.printStackTrace();
                                                                  }
                                                                  Medicine medicine = null;
                                                                  try {
                                                                      medicine = service.fetchMedicine(idgiven);
                                                                  } catch (RemoteException ex) {
                                                                      ex.printStackTrace();
                                                                  } catch (SQLException ex) {
                                                                      ex.printStackTrace();
                                                                  }
                                                                  result.setText("Description: " + medicine.getDescription());
                                                                  frame1.resize(750, 750);
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


