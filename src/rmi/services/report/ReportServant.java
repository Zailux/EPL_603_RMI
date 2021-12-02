package rmi.services.report;

import rmi.database.Postgres;
import rmi.models.appointment.Appointment;
import rmi.models.report.MonthlyDrugs.MonthlyDrugs;
import rmi.models.report.MonthlyPatients.MonthlyPatients;
import rmi.models.report.WeeklyAmountsPerDrug.WeeklyAmountsPerDrug;
import rmi.models.report.WeeklyPatients.WeeklyPatients;
import rmi.models.report.WeeklyPatientsPerType.WeeklyPatientsPerType;
import rmi.models.treatment.Treatment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportServant extends UnicastRemoteObject implements  ReportService{
    static Connection con = Postgres.getConnection();

    public ReportServant() throws RemoteException {
        super();
    }

    @Override
    public MonthlyPatients fetchMonthlyPatients(String month, String year) throws RemoteException, SQLException {
        String query = "select COUNT(DISTINCT p_id) AS monthly_patients from \"Appointment\" where attended=true AND EXTRACT(MONTH FROM date)=? AND EXTRACT (YEAR FROM DATE)=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, Integer.parseInt(month));
        ps.setInt(2, Integer.parseInt(year));
        MonthlyPatients patients = new MonthlyPatients();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            patients.setMonthly_patients(rs.getInt("monthly_patients"));
        }

        if (check == true) {
            return patients;
        } else
            return null;
    }

    @Override
    public MonthlyDrugs fetchMonthlyDrugs(String month, String year) throws RemoteException, SQLException {

        String query = "select COUNT(quantity) AS monthly_drugs from \"Treatment\" where EXTRACT(MONTH FROM date)=? AND EXTRACT (YEAR FROM DATE)=?";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, Integer.parseInt(month));
        ps.setInt(2, Integer.parseInt(year));
        MonthlyDrugs drugs = new MonthlyDrugs();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            drugs.setMonthly_drugs(rs.getInt("monthly_drugs"));
        }

        if (check == true) {
            return drugs;
        } else
            return null;
    }

    @Override
    public WeeklyPatients fetchWeeklyPatients() throws RemoteException, SQLException {
        String query = "select date, count(DISTINCT p_id ) as weekly_patients from \"Appointment\" where date > current_date - interval '7 days' GROUP BY date";
        PreparedStatement ps = con.prepareStatement(query);

        WeeklyPatients patients = new WeeklyPatients();
        ResultSet rs = ps.executeQuery();
        boolean check = false;

        while (rs.next()) {
            check = true;
            patients.setWeekly_patients(rs.getInt("weekly_patients"));
        }

        if (check == true) {
            return patients;
        } else
            return null;
    }

    @Override
    public List<WeeklyPatientsPerType> fetchWeeklyPatientsPerType() throws RemoteException, SQLException {
        String query = "select type, count(  distinct p_id ) as weekly_patients from \"Consultation\" where date > current_date - interval '7 days' GROUP BY type";
        PreparedStatement ps= con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<WeeklyPatientsPerType> ls = new ArrayList();

        while (rs.next()) {
            WeeklyPatientsPerType patientsPerType = new WeeklyPatientsPerType();
            patientsPerType.setType(rs.getString("type"));
            patientsPerType.setWeekly_patients(rs.getInt("weekly_patients"));
            ls.add(patientsPerType);
        }
        return ls;
    }

    @Override
    public List<WeeklyAmountsPerDrug> fetchWeeklyAmountsPerDrug() throws RemoteException, SQLException {
        String query = "select M.*, COUNT(quantity) AS weekly_drugs from \"Treatment\" T INNER JOIN \"Medicine\" M on T.m_id=M.id where T.date > current_date - interval '7 days' GROUP BY M.id, M.brand, M.description ";
        PreparedStatement ps= con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<WeeklyAmountsPerDrug> ls = new ArrayList();

        while (rs.next()) {
            WeeklyAmountsPerDrug drug = new WeeklyAmountsPerDrug();
            drug.setId(rs.getInt("id"));
            drug.setDescription(rs.getString("description"));
            drug.setBrand(rs.getString("brand"));
            drug.setMonthly_drugs(rs.getInt("weekly_drugs"));
            ls.add(drug);
        }
        return ls;
    }
}
