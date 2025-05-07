import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class NonProfitDBApp extends JFrame {
    private Connection conn;

    public NonProfitDBApp() {
        setTitle("NonProfitDB Management");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        // Database Connection for SQL
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e);
        }
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://triton2.towson.edu:3360/?serverTimezone=EST#/jakinw1db",
                "jakinw1", "COSC*qgxvl"
            );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "failed to connect to Database type shi", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Buttons
        JButton donorBtn = new JButton("Add Donor");
        JButton eventBtn = new JButton("Add Event");
        JButton beneficiaryBtn = new JButton("Add Beneficiary");
        JButton volunteerBtn = new JButton("Add Volunteer");
        JButton donationBtn = new JButton("Add Donation");
        JButton expenseBtn = new JButton("Add Expense");
        JButton tablesBtn = new JButton("Show Information");

        add(donorBtn);
        add(eventBtn);
        add(beneficiaryBtn);
        add(volunteerBtn);
        add(donationBtn);
        add(expenseBtn);
        add(tablesBtn);

        donorBtn.addActionListener(e -> showDonorForm());
        eventBtn.addActionListener(e -> showEventForm());
        beneficiaryBtn.addActionListener(e -> showBeneficiaryForm());
        volunteerBtn.addActionListener(e -> showVolunteerForm());
        donationBtn.addActionListener(e -> showDonationForm());
        expenseBtn.addActionListener(e -> showExpenseForm());
        tablesBtn.addActionListener(e -> showTables());
    }

    // ---------- Donor ----------
    private void showDonorForm() {
        JFrame frame = new JFrame("Add Donor");
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField typeField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Type:")); panel.add(typeField);
        panel.add(submitBtn);

        frame.add(panel);
        frame.setVisible(true);

        submitBtn.addActionListener(e -> {
            insertDonor(nameField.getText(), phoneField.getText(), typeField.getText());
            frame.dispose();
        });
    }

    private void insertDonor(String name, String phone, String type) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Donor VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, Integer.parseInt(phone));
            stmt.setString(3, type);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Donor added!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding donor.");
        }
    }

    // ---------- Event ----------
    private void showEventForm() {
        JFrame frame = new JFrame("Add Event");
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField locField = new JTextField();
        JTextField benField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Date (YYYY-MM-DD):")); panel.add(dateField);
        panel.add(new JLabel("Location:")); panel.add(locField);
        panel.add(new JLabel("Beneficiary:")); panel.add(benField);
        panel.add(submitBtn);

        frame.add(panel);
        frame.setVisible(true);

        submitBtn.addActionListener(e -> {
            insertEvent(nameField.getText(), dateField.getText(), locField.getText(), benField.getText());
            frame.dispose();
        });
    }

    private void insertEvent(String name, String date, String location, String beneficiary) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Event VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, location);
            stmt.setString(4, beneficiary);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Event added!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding event.");
        }
    }

    // ---------- Add similar methods for Beneficiary, Volunteer, Donation, Expense ----------
    private void showBeneficiaryForm() { /* similar pattern */
        JFrame frame = new JFrame("Add Beneficiary");
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField IDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField benTypeField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        panel.add(new JLabel("ID:")); panel.add(IDField);
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Type:")); panel.add(benTypeField);
        panel.add(submitBtn);

        frame.add(panel);
        frame.setVisible(true);
        submitBtn.addActionListener(e -> {
            insertBeneficiary(IDField.getText(), nameField.getText(), phoneField.getText(), benTypeField.getText());
            frame.dispose();
        });
    }

    private void insertBeneficiary(String ID, String name, String phone, String type) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Donor VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, ID);
            stmt.setString(2, name);
            stmt.setInt(3, Integer.parseInt(phone));
            stmt.setString(4, type);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Beneficiary added!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding Beneficiary.");
        }
    }
    //User Enter Fields: ID, Name, Phone, SupportType

    
    
    private void showVolunteerForm() { 
        /* similar pattern */ 
        //doing what i can without being able to run so if wrong just delete
        JFrame frame = new JFrame("Add Volunteer");
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField hourField = new JTextField();
        JTextField eventField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Hour:")); panel.add(hourField);
        panel.add(new JLabel("Event: ")); panel.add(eventField);
        panel.add(submitBtn);

        frame.add(panel);
        frame.setVisible(true);
        submitBtn.addActionListener(e -> {
            if (!eventExists(eventField.getText())){
                JOptionPane.showMessageDialog(frame, "Event does not exist. Please enter a valid event name.");
                return; 
            }
            insertVolunteer(nameField.getText(), phoneField.getText(), hourField.getText());
            frame.dispose();
        });
    }
    
    private void insertVolunteer(String name, String phone, String Hours) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Donor VALUES (?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setInt(2, Integer.parseInt(phone));
            stmt.setInt(3, Integer.parseInt(Hours));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Volunteer added!");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding Volunteer.");
        }
    }

    public boolean eventExists(String eventName){
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jakinw1db.Event WHERE Name = ?")) {
            stmt.setString(1, eventName);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    //User Enter Fields: Name, Phone, Hours
    //Then request Event Name
        //ensure Event Name exsists in Event
    //This should then update Volunteer_At with Volunteer.Name, Event.name

    private void showDonationForm() { /* similar pattern */ }
    //User Enter Fields: Name, Date, Amount, IsFirst, IsRecurring
    //Connected: The Separate Donor Entry MUST come First
        //Ensure Donor Name exsists in Donor.Name
    
    private void showExpenseForm() { /* similar pattern */ }
    //User Enter Fields: BeneficaryID, Amount, ExpDate, Category, Description
    //Connected: Event AND Beneficiary entries MUST come first
        //Ensure BeneficaryID is in Beneficiary.ID
        //Ensure ExpDate is in Event.Date

    /* -------- Show Tables -------- */
    private void showTables(){
        JFrame frame = new JFrame("List Tables");
        frame.setSize(400, 400);
        JPanel panel = new JPanel(new GridLayout(7,1));
    
        JButton showDonorTableBtn = new JButton("Show Donors");
        JButton showDonationTableBtn = new JButton("Show Donations");	
        JButton showBeneficiaryTableBtn = new JButton("Show Beneficiaries");
       JButton showEventTableBtn = new JButton("Show Events");
        JButton showExpenseTableBtn = new JButton("Show Expenses");
        JButton showVolunteerTableBtn = new JButton("Show Volunteers");

        panel.add(showDonorTableBtn);
        panel.add(showDonationTableBtn);
        panel.add(showBeneficiaryTableBtn);
        panel.add(showEventTableBtn);
        panel.add(showExpenseTableBtn);
        panel.add(showVolunteerTableBtn);
    
        frame.add(panel);
        frame.setVisible(true);
    
        showDonorTableBtn.addActionListener(e -> {printDonors();});
        showDonationTableBtn.addActionListener(e -> {printDonations();});
        showBeneficiaryTableBtn.addActionListener(e -> {printBenes();});
        showEventTableBtn.addActionListener(e -> {printEvents();});
        showExpenseTableBtn.addActionListener(e -> {printExpenses();});
        showVolunteerTableBtn.addActionListener(e -> {printVolunteers();});
    }

    private void printDonors(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donor");
            System.out.println();
            while (rs.next()) {
                String donName = rs.getString("Name");
                String donPhone = rs.getString("Phone");
                String donType = rs.getString("Type");
                System.out.println(donName+", "+donPhone+", "+donType);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Table.");
        }
    }
    
    private void printDonations() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donation");
            System.out.println();
            while (rs.next()) {
                String donName = rs.getString("Name");
                Date donDate = rs.getDate("Date");
                double donAmount = rs.getDouble("Amount");
                String isFirst = rs.getString("IsFirst");
                String isRecurring = rs.getString("IsRecurring");
                System.out.println(donName + ", " + donDate + ", " + donAmount + ", " + isFirst + ", " + isRecurring);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Donations Table.");
        }
    }
    
    private void printBenes() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Beneficiary");
            System.out.println();
            while (rs.next()) {
                String benID = rs.getString("ID");
                String benName = rs.getString("Name");
                String benPhone = rs.getString("Phone");
                String benType = rs.getString("Type");
                System.out.println(benID + ", " + benName + ", " + benPhone + ", " + benType);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Beneficiaries Table.");
        }
    }
    
    private void printEvents() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Event");
            System.out.println();
            while (rs.next()) {
                String eventName = rs.getString("Name");
                Date eventDate = rs.getDate("Date");
                String eventLocation = rs.getString("Location");
                String eventBeneficiary = rs.getString("Beneficiary");
                System.out.println(eventName + ", " + eventDate + ", " + eventLocation + ", " + eventBeneficiary);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Events Table.");
        }
    }
    
    private void printExpenses() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Expense");
            System.out.println();
            while (rs.next()) {
                String benID = rs.getString("BeneficiaryID");
                double amount = rs.getDouble("Amount");
                Date expDate = rs.getDate("ExpDate");
                String category = rs.getString("Category");
                String description = rs.getString("Description");
                System.out.println(benID + ", " + amount + ", " + expDate + ", " + category + ", " + description);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Expenses Table.");
        }
    }
    
    private void printVolunteers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Volunteer");
            System.out.println();
            while (rs.next()) {
                String volunteerName = rs.getString("Name");
                String volunteerPhone = rs.getString("Phone");
                int volunteerHours = rs.getInt("Hours");
                String event = rs.getString("Event");
                System.out.println(volunteerName + ", " + volunteerPhone + ", " + volunteerHours + " hours, " + event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Volunteers Table.");
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NonProfitDBApp().setVisible(true));
    }
}
