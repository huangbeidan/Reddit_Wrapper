package testconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class createtable {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "beidan", "hbdydkdy");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Reddit " +
                    "(FULLNAME VARCHAR PRIMARY KEY NOT NULL," +
                    " TYPE     VARCHAR , " +
                    " CONTENT  VARCHAR )";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
