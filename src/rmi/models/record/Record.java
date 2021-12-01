package rmi.models.record;

import java.io.Serializable;
import java.sql.Date;

public class Record implements Serializable {

    Integer consultation_id;
    String consultation_type;
    Date consultation_date;
    Boolean finished;
    Date treatment_date;
    String treatment_description;
    Integer treatment_quantity;

    public Record() {
    }

    public Record(Integer consultation_id,
                  String consultation_type,
                  Date consultation_date,
                  Boolean finished,
                  Date treatment_date,
                  String treatment_description,
                  Integer treatment_quantity) {
        this.consultation_id = consultation_id;
        this.consultation_type =consultation_type;
        this.consultation_date = consultation_date;
        this.finished = finished;
        this.treatment_date = treatment_date;
        this.treatment_description = treatment_description;
        this.treatment_quantity = treatment_quantity;
    }

    public Integer getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(Integer consultation_id) {
        this.consultation_id = consultation_id;
    }

    public String getConsultation_type() {
        return consultation_type;
    }

    public void setConsultation_type(String consultation_type) {
        this.consultation_type = consultation_type;
    }

    public Date getConsultation_date() {
        return consultation_date;
    }

    public void setConsultation_date(Date consultation_date) {
        this.consultation_date = consultation_date;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Date getTreatment_date() {
        return treatment_date;
    }

    public void setTreatment_date(Date treatment_date) {
        this.treatment_date = treatment_date;
    }

    public String getTreatment_description() {
        return treatment_description;
    }

    public void setTreatment_description(String treatment_description) {
        this.treatment_description = treatment_description;
    }

    public Integer getTreatment_quantity() {
        return treatment_quantity;
    }

    public void setTreatment_quantity(Integer treatment_quantity) {
        this.treatment_quantity = treatment_quantity;
    }
}
