/*
 * This is a base for the java connector. 
 * Ensure mysql-connector-java-8.0.16.jar is loaded as a library.
 */

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
import java.util.Scanner;

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
    
    final String ID = "jakinw1"; //your triton username
    final String PW = "COSC*qgxvl"; //add your triton pw here
    final String SERVER = "jdbc:mysql://triton2.towson.edu:3360/?serverTimezone=EST#/jakinw1db"; //your tronton database name here
    String querys;
    try {
        Connection con = DriverManager.getConnection(SERVER, ID, PW);
        Statement stmt = con.createStatement();


        //This is a simple SELECT request
        ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.spj");
        while (rs.next()){
            String snum = rs.getString("s_num");
            String pnum = rs.getString("p_num");
            String jnum = rs.getString("j_num");
            String quantity = rs.getString("qty");
            System.out.println(snum+", "+pnum+", "+jnum+", "+ quantity);
        }
        

        //Input option
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter new City Name for Blake: ");
        String cityName = scan.next();

        //This is an UPDATE request using the above manual input
        PreparedStatement updateSupplier = null;
        querys = "UPDATE jakinw1db.s SET city = '" + cityName + "' WHERE s_name = 'Blake';";
        updateSupplier = con.prepareStatement(querys);
        updateSupplier.executeUpdate();

        System.out.println("Update Complete");

        //This is an INSERT requets
        PreparedStatement addSuppply = null;
        querys = "INSERT INTO jakinw1db.s (s_num, s_name, status, city) VALUES ('s6', 'James', '10', 'Cork')";
        addSuppply = con.prepareStatement(querys);
        addSuppply.executeUpdate();

        System.out.println("Insert Complete");

        ResultSet rs2 = stmt.executeQuery("SELECT * FROM jakinw1db.s");
        while (rs2.next()){
            String snum = rs2.getString("s_num");
            String sname = rs2.getString("s_name");
            String status = rs2.getString("status");
            String city = rs2.getString("city");
            System.out.println(snum+", "+sname+", "+status+", "+ city);
        }

    }catch (SQLException e){
        System.err.println(e);
    }
    }//Main
    
}