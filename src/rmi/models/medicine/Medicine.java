package rmi.models.medicine;

import java.io.Serializable;
import java.sql.Date;

public class Medicine implements Serializable {
    int id;
    String description;
    String brand;

    public Medicine() { }

    public Medicine(String description, String brand) {
        this.description = description;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
