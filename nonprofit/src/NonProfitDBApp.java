import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class NonProfitDBApp extends JFrame {

    String datePattern = "YYYY-MM-DD";
    DateFormat df = new SimpleDateFormat(datePattern);

    private Connection conn;

    public NonProfitDBApp() {
        setTitle("NonProfitDB Management");
        setSize(400, 400);
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
        //create buttons for each set of information
        JButton showDonorTableBtn = new JButton("Show Donors");
        JButton showDonationTableBtn = new JButton("Show Donations");	
        JButton showBeneficiaryTableBtn = new JButton("Show Beneficiaries");
        JButton showEventTableBtn = new JButton("Show Events");
        JButton showExpenseTableBtn = new JButton("Show Expenses");
        JButton showVolunteerTableBtn = new JButton("Show Volunteers");
        JButton exitTables = new JButton("Exit");
        //adding buttons as panels
        panel.add(showDonorTableBtn);
        panel.add(showDonationTableBtn);
        panel.add(showBeneficiaryTableBtn);
        panel.add(showEventTableBtn);
        panel.add(showExpenseTableBtn);
        panel.add(showVolunteerTableBtn);
        panel.add(exitTables);
        //setting visible
        frame.add(panel);
        frame.setVisible(true);
        //add button functionality
        showDonorTableBtn.addActionListener(e -> {printDonors();});
        showDonationTableBtn.addActionListener(e -> {printDonations();});
        showBeneficiaryTableBtn.addActionListener(e -> {printBenes();});
        showEventTableBtn.addActionListener(e -> {printEvents();});
        showExpenseTableBtn.addActionListener(e -> {printExpenses();});
        showVolunteerTableBtn.addActionListener(e -> {printVolunteers();});
        exitTables.addActionListener(e -> frame.dispose());
    }

    private void printDonors(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donor");

            JFrame frame = new JFrame("Donors List");
            frame.setSize(400, 400);
            JPanel panel = new JPanel(new GridLayout(0,3));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("Name:"));
            panel.add(new JLabel("Phone #:"));
            panel.add(new JLabel("Donor Type:"));
            
            int dcount = 0;     //for total number of donors
            while (rs.next()) {
                String donName = rs.getString("Name");
                String donPhone = rs.getString("Phone");
                String donType = rs.getString("Type");
                panel.add(new JLabel(donName));
                panel.add(new JLabel(donPhone));
                panel.add(new JLabel(donType));
                dcount++;
                //System.out.println(donName+", "+donPhone+", "+donType);
            }
            panel.add(new JLabel("Total Donor Count: "));
            panel.add(new JLabel(Integer.toString(dcount)));
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Table.");
        }
    }
    
    private void printDonations() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donation");
            JFrame frame = new JFrame("Donations List");
            frame.setSize(500, 400);
            JPanel panel = new JPanel(new GridLayout(0,5));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("Name"));
            panel.add(new JLabel("Date"));
            panel.add(new JLabel("Amount"));
            panel.add(new JLabel("First Donation?"));
            panel.add(new JLabel("Recurring?"));
            double dsum = 0;
            while (rs.next()) {
                String donName = rs.getString("Name");
                String donDate = df.format(rs.getDate("Date"));
                double donAmount = rs.getDouble("Amount");
                String isFirst = rs.getString("IsFirst");
                String isRecurring = rs.getString("IsRecurring");
                panel.add(new JLabel(donName));
                panel.add(new JLabel(donDate));
                panel.add(new JLabel(Double.toString(donAmount)));
                panel.add(new JLabel(isFirst));
                panel.add(new JLabel(isRecurring));
                dsum += donAmount;
                //System.out.println(donName + ", " + donDate + ", " + donAmount + ", " + isFirst + ", " + isRecurring);
            }
            panel.add(new JLabel("Total Donations: "));
            panel.add(new JLabel("$" + Double.toString(dsum)));
            panel.add(new JLabel());panel.add(new JLabel()); //empty fields to keep spacing
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Donations Table.");
        }
    }
    
    private void printBenes() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Beneficiary");
            JFrame frame = new JFrame("Beneficiary List");
            frame.setSize(500, 400);
            JPanel panel = new JPanel(new GridLayout(0,4));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("ID"));
            panel.add(new JLabel("Name"));
            panel.add(new JLabel("Phone"));
            panel.add(new JLabel("Support Type"));
            int bcount=0;
            while (rs.next()) {
                String benID = rs.getString("ID");
                String benName = rs.getString("Name");
                String benPhone = rs.getString("Phone");
                String benType = rs.getString("SupportType");
                panel.add(new JLabel(benID));
                panel.add(new JLabel(benName));
                panel.add(new JLabel(benPhone));
                panel.add(new JLabel(benType));
                bcount++;
                //System.out.println(benID + ", " + benName + ", " + benPhone + ", " + benType);
            }
            panel.add(new JLabel("Total Beneficiaries:"));
            panel.add(new JLabel(Integer.toString(bcount)));
            panel.add(new JLabel());
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Beneficiaries Table.");
        }
    }
    
    private void printEvents() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Event");
            JFrame frame = new JFrame(" List");
            frame.setSize(400, 400);
            JPanel panel = new JPanel(new GridLayout(0,4));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("Name"));
            panel.add(new JLabel("Event Date"));
            panel.add(new JLabel("Location"));
            panel.add(new JLabel("Beneficiary"));
            int ecount=0;
            while (rs.next()) {
                String eventName = rs.getString("Name");
                String eventDate = df.format(rs.getDate("EventDate"));
                String eventLocation = rs.getString("Location");
                String eventBeneficiary = rs.getString("Beneficiary");
                panel.add(new JLabel(eventName));
                panel.add(new JLabel(eventDate));
                panel.add(new JLabel(eventLocation));
                panel.add(new JLabel(eventBeneficiary));
                ecount++;
                //System.out.println(eventName + ", " + eventDate + ", " + eventLocation + ", " + eventBeneficiary);
            }
            panel.add(new JLabel("Number of Events:"));
            panel.add(new JLabel(Integer.toString(ecount)));
            panel.add(new JLabel());
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Events Table.");
        }
    }
    
    private void printExpenses() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Expenses");
            JFrame frame = new JFrame(" List");
            frame.setSize(400, 400);
            JPanel panel = new JPanel(new GridLayout(0,5));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("Beneficiary ID"));
            panel.add(new JLabel("Amount"));
            panel.add(new JLabel("Date"));
            panel.add(new JLabel("Category"));
            panel.add(new JLabel("Description"));
            double sumCost=0;
            while (rs.next()) {
                String benID = rs.getString("BeneficaryID");
                double amount = rs.getDouble("Amount");
                String expDate = df.format(rs.getDate("ExpDate"));
                String category = rs.getString("Category");
                String description = rs.getString("Description");
                panel.add(new JLabel(benID));
                panel.add(new JLabel(expDate));
                panel.add(new JLabel(Double.toString(amount)));
                panel.add(new JLabel(category));
                panel.add(new JLabel(description));
                sumCost+=amount;
                //System.out.println(benID + ", " + amount + ", " + expDate + ", " + category + ", " + description);
            }
            panel.add(new JLabel("Total Costs:"));
            panel.add(new JLabel("$" + Double.toString(sumCost)));
            panel.add(new JLabel());
            panel.add(new JLabel());
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Expenses Table.");
        }
    }
    
    private void printVolunteers() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Volunteer");
            JFrame frame = new JFrame(" List");
            frame.setSize(400, 400);
            JPanel panel = new JPanel(new GridLayout(0,3));
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            panel.add(new JLabel("Name"));
            panel.add(new JLabel("Phone"));
            panel.add(new JLabel("Total Hours"));
            int vcount=0;
            int hcount=0;
            while (rs.next()) {
                String volunteerName = rs.getString("Name");
                String volunteerPhone = rs.getString("Phone");
                int volunteerHours = rs.getInt("Hours");
                //String event = rs.getString("Event");
                panel.add(new JLabel(volunteerName));
                panel.add(new JLabel(volunteerPhone));
                panel.add(new JLabel(Integer.toString(volunteerHours)));
                vcount++;
                hcount+=volunteerHours;
                //System.out.println(volunteerName + ", " + volunteerPhone + ", " + volunteerHours + " hours");
            }
            panel.add(new JLabel("Volunteer Count"));
            panel.add(new JLabel(Integer.toString(vcount)));
            panel.add(new JLabel());
            panel.add(new JLabel("Total Hours:"));
            panel.add(new JLabel(Integer.toString(hcount)));
            panel.add(exit);
            frame.add(panel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Volunteers Table.");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NonProfitDBApp().setVisible(true));
    }
}
