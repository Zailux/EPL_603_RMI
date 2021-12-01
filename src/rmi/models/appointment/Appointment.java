package rmi.models.appointment;

import rmi.models.patient.Patient;
import rmi.models.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment implements Serializable {

    int id;
    int p_id;
    int u_id;
    Date date;
    Date created;
    boolean attended;

    public Appointment () {}

    public Appointment(int p_id, int u_id, Date date, Date created, boolean attended)
    {
        this.id = id;
        this.p_id = p_id;
        this.u_id = u_id;
        this.date = date;
        this.created = created;
        this.attended=attended;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
