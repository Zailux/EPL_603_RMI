package rmi;

import rmi.services.appointment.AppointmentServant;
import rmi.services.consultation.ConsultationServant;
import rmi.services.medicine.MedicineServant;
import rmi.services.patient.PatientServant;
import rmi.services.treatment.TreatmentServant;
import rmi.services.user.UserServant;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public  class ApplicationServer {
    public static void main(String[] args) throws RemoteException, SQLException {

        Registry registry = LocateRegistry.createRegistry(5099);
        registry.rebind("patient", new PatientServant());
        registry.rebind("user", new UserServant());
        registry.rebind("consultation", new ConsultationServant());
        registry.rebind("treatment", new TreatmentServant());
        registry.rebind("medicine", new MedicineServant());
        registry.rebind("appointment", new AppointmentServant());
    }
}