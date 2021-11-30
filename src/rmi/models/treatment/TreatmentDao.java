package rmi.models.treatment;

import java.sql.SQLException;

public interface TreatmentDao {

    int add(Treatment treatment)
            throws SQLException;

    Treatment getTreatment(int id)
            throws SQLException;
}
