package rmi.models.patient;

import java.io.Serializable;

public class Patient implements Serializable {

    int id;
    String name;
    String email;
    Boolean historyOfSelfHarm;
    String riskIndicator;
    boolean alive;


    public Patient () {}

    public Patient(String name, String email, Boolean historyOfSelfHarm, String riskIndicator)
    {
        this.name = name;
        this.email = email;
        this.historyOfSelfHarm = historyOfSelfHarm;
        this.riskIndicator = riskIndicator;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Boolean getHistoryOfSelfHarm()
    {
        return historyOfSelfHarm;
    }

    public void setHistoryOfSelfHarm(Boolean historyOfSelfHarm)
    {
        this.historyOfSelfHarm = historyOfSelfHarm;
    }

    public String getRiskIndicator()
    {
        return riskIndicator;
    }

    public void setRiskIndicator(String riskIndicator)
    {
        this.riskIndicator = riskIndicator;
    }


}
