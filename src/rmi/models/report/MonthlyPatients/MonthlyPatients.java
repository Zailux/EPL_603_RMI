package rmi.models.report.MonthlyPatients;

import java.io.Serializable;
import java.sql.Date;

public class MonthlyPatients implements Serializable {

    Integer monthly_patients;

    public MonthlyPatients() {
    }

    public MonthlyPatients(Integer monthly_patients) {
        this.monthly_patients = monthly_patients;
    }

    public Integer getMonthly_patients() {
        return monthly_patients;
    }

    public void setMonthly_patients(Integer monthly_patients) {
        this.monthly_patients = monthly_patients;
    }
}
