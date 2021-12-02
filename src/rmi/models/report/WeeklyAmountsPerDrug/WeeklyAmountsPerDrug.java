package rmi.models.report.WeeklyAmountsPerDrug;

import java.io.Serializable;

public class WeeklyAmountsPerDrug implements Serializable {

    Integer id;
    String description;
    String brand;
    Integer monthly_drugs;

    public WeeklyAmountsPerDrug() {
    }

    public WeeklyAmountsPerDrug(Integer id, String description, String brand, Integer monthly_drugs) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.monthly_drugs = monthly_drugs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getMonthly_drugs() {
        return monthly_drugs;
    }

    public void setMonthly_drugs(Integer monthly_drugs) {
        this.monthly_drugs = monthly_drugs;
    }
}
