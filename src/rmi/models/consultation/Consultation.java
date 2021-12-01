package rmi.models.consultation;

import rmi.models.patient.Patient;
import rmi.models.user.User;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Consultation implements Serializable {

    int id;
    int t_id;
    int p_id;
    int pc_id;
    int u_id;
    String type;
    Date date;
    boolean finished;

    public Consultation () {}

    public Consultation(int t_id,int p_id, int u_id, String type, Date date, boolean finished)
    {
        this.t_id = t_id;
        this.p_id = p_id;
        this.u_id = u_id;
        this.type = type;
        this.date = date;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getPc_id() {
        return pc_id;
    }

    public void setPc_id(int pc_id) {
        this.pc_id = pc_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
