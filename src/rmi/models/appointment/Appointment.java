package rmi.models.appointment;

import rmi.models.patient.Patient;
import rmi.models.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment implements Serializable {

    int id;
    Date date;
    Patient patient;
    User doctor;

    public Appointment () {}

    public Appointment(Patient patient, User doctor, Date date)
    {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public User getDoctor()
    {
        return doctor;
    }

    public void setDoctor(User doctor)
    {
        this.doctor = doctor;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}
