package rmi.services.report;

import rmi.database.Postgres;
import rmi.models.report.MonthlyDrugs.MonthlyDrugs;
import rmi.models.report.MonthlyPatients.MonthlyPatients;
import rmi.models.report.WeeklyAmountsPerDrug.WeeklyAmountsPerDrug;
import rmi.models.report.WeeklyPatients.WeeklyPatients;
import rmi.models.report.WeeklyPatientsPerType.WeeklyPatientsPerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReportService extends Remote {

    public MonthlyPatients fetchMonthlyPatients(String month, String year) throws RemoteException, SQLException;

    public MonthlyDrugs fetchMonthlyDrugs(String month, String year) throws RemoteException, SQLException;

    public WeeklyPatients fetchWeeklyPatients(Date date) throws RemoteException, SQLException;

    public List<WeeklyPatientsPerType> fetchWeeklyPatientsPerType(Date date) throws RemoteException, SQLException;

    public List<WeeklyAmountsPerDrug> fetchWeeklyAmountsPerDrug(Date date) throws RemoteException, SQLException;
}
