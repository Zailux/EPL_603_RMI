package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class App {
    private JButton buttonMsg;
    private JPanel panelMain;

    public App() {
        buttonMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rmi.HelloService service = null;
                try {
                    service = (rmi.HelloService) Naming.lookup("rmi://localhost:5099/hello");
                } catch (NotBoundException ex) {
                    ex.printStackTrace();
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
                try {
                    JOptionPane.showMessageDialog(null, service.echo("Hey server"));
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
