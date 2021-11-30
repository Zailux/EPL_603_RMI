package rmi.database;

import java.sql.*;

public class Postgres {

    private static Connection db = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://surus.db.elephantsql.com:5432/otopyalz";
        String username = "otopyalz";
        String password = "Q5efw30ucCO4E-3KI4IaRh3ohldQ27d9";

        try {
            db = DriverManager.getConnection(url, username, password);
//            Statement st = db.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM people");
//            while (rs.next()) {
//                System.out.print("Column 1 returned ");
//                System.out.println(rs.getString(2));
//                System.out.print("Column 2 returned ");
//                System.out.println(rs.getString(3));
//            }
//            rs.close();
//            st.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return db;
    }

}