package rmi.models.report.WeeklyPatientsPerType;

import java.io.Serializable;

public class WeeklyPatientsPerType implements Serializable {

    Integer weekly_patients;
    String type;

    public WeeklyPatientsPerType() {
    }

    public WeeklyPatientsPerType(Integer weekly_patients, String type) {
        this.weekly_patients = weekly_patients;
        this.type = type;
    }

    public Integer getWeekly_patients() {
        return weekly_patients;
    }

    public void setWeekly_patients(Integer weekly_patients) {
        this.weekly_patients = weekly_patients;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
