package rmi.models.patient;

import java.io.Serializable;

public class Patient implements Serializable {

    int id;
    String name;
    String address;
    Boolean historyOfSelfHarm;
    String riskIndicator;

    public Patient () {}

    public Patient(String name, String address, Boolean historyOfSelfHarm, String riskIndicator)
    {
        this.name = name;
        this.address = address;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
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
