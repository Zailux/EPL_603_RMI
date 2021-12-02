package rmi.models.report.WeeklyPatients;

import java.io.Serializable;

public class WeeklyPatients implements Serializable {
    Integer weekly_patients;

    public WeeklyPatients() {
    }

    public WeeklyPatients(Integer weekly_patients) {
        this.weekly_patients = weekly_patients;
    }

    public Integer getWeekly_patients() {
        return weekly_patients;
    }

    public void setWeekly_patients(Integer weekly_patients) {
        this.weekly_patients = weekly_patients;
    }
}
