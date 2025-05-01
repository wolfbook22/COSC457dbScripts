/*
 * This is a base for the java connector. 
 * Ensure mysql-connector-java-8.0.16.jar is loaded as a library.
 */
package dbconnector;

/**
 *
 * @author S
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.time.Clock;
import java.time.ZoneId;

public class dbconnector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
    }catch (ClassNotFoundException e){
        System.out.println(e);
    }
    
    final String ID = "shong";
    final String PW = "";
    final String SERVER = "jdbc:mysql://triton2.towson.edu:3360/?serverTimezone=EST#/shongdb";
    String querys;
    try {
        Connection con = DriverManager.getConnection(SERVER, ID, PW);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM shongdb.Branch");
        PreparedStatement updateStaff = null;
        
        while (rs.next()){
            String bNo = rs.getString("branchNo");
            String street = rs.getString("street");
            String city = rs.getString("city");
            String postcode = rs.getString("postcode");
            System.out.println(bNo+", "+street+", "+city+", "+ postcode);
            
        }
        querys = "UPDATE shongdb.Branch SET city = 'Baltimore' WHERE branchNo = 'B002';";
        updateStaff = con.prepareStatement(querys);
        updateStaff.executeUpdate();
    }catch (SQLException e){
        System.err.println(e);
    }
    }//Main
    
}