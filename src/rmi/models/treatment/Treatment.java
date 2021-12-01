package rmi.models.treatment;

import java.sql.Date;

public class Treatment {

    int id;
    int u_id;
    int m_id;
    Date date;
    String description;
    int previousT;
    int quantity;

    public Treatment () {}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPreviousT() {
        return previousT;
    }

    public void setPreviousT(int previousT) {
        this.previousT = previousT;
    }

    public Treatment(int u_id, int m_id, Date date, String description, int previousT, int quantity)
    {
        this.u_id = u_id;
        this.m_id = m_id;
        this.description = description;
        this.date = date;
        this.quantity = quantity;
        this.previousT =  previousT;
    }
}
