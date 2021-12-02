package rmi.models.report.MonthlyDrugs;

import java.io.Serializable;

public class MonthlyDrugs implements Serializable {
    Integer monthly_drugs;

    public MonthlyDrugs() {
    }

    public MonthlyDrugs(Integer monthly_drugs) {
        this.monthly_drugs = monthly_drugs;
    }

    public Integer getMonthly_drugs() {
        return monthly_drugs;
    }

    public void setMonthly_drugs(Integer monthly_drugs) {
        this.monthly_drugs = monthly_drugs;
    }
}
