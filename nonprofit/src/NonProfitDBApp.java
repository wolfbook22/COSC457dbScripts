import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
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
            stmt.setInt(2, Integer.valueOf(phone));
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

    private void showBeneficiaryForm() {
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



    // ---------- Add similar methods for Beneficiary, Volunteer, Donation, Expense ----------


    // ---------- Donation ----------

private void showDonationForm() {
    JFrame frame = new JFrame("Add Donation");
    frame.setSize(300, 200);
    JPanel panel = new JPanel(new GridLayout(6, 2));
    JTextField nameField = new JTextField();
    JTextField dateField = new JTextField();
    JTextField amountField = new JTextField();
    JTextField isFirstField = new JTextField();
    JTextField isRecurringField = new JTextField();
    JButton submitBtn = new JButton("Submit");

    panel.add(new JLabel("Donor Name:")); panel.add(nameField);
    panel.add(new JLabel("Date (YYYY-MM-DD):")); panel.add(dateField);
    panel.add(new JLabel("Amount:")); panel.add(amountField);
    panel.add(new JLabel("First Donation (Y/N):")); panel.add(isFirstField);
    panel.add(new JLabel("Recurring Donation (Y/N):")); panel.add(isRecurringField);
    panel.add(submitBtn);

    frame.add(panel);
    frame.setVisible(true);

    submitBtn.addActionListener(e -> {
        if (!donorExists(nameField.getText())) {
            JOptionPane.showMessageDialog(frame, "Donor does not exist. Please enter a valid donor name.");
            return; 
        }
        insertDonation(nameField.getText(), dateField.getText(), amountField.getText(), isFirstField.getText(), isRecurringField.getText());
        frame.dispose();
    });    
}

private boolean donorExists(String donorName) {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jakinw1db.Donor WHERE Name = ?")) {
        stmt.setString(1, donorName);
        ResultSet rs = stmt.executeQuery();
        return rs.next();  // If a row is found, the donor exists.
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;  // Return false in case of an error.
    }
}


private void insertDonation(String donorName, String date, String amount, String isFirst, String isRecurring) {
    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Donation (Name, Date, Amount, IsFirst, IsRecurring) VALUES (?, ?, ?, ?, ?)")) {
        stmt.setString(1, donorName);
        stmt.setDate(2, Date.valueOf(date));
        stmt.setDouble(3, Double.parseDouble(amount));
        stmt.setString(4, isFirst);
        stmt.setString(5, isRecurring);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Donation added!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding donation.");
    }
}

    // ---------- Expense ----------

private void showExpenseForm() {
    JFrame frame = new JFrame("Add Expense");
    frame.setSize(300, 200);
    JPanel panel = new JPanel(new GridLayout(6, 2));
    JTextField benIDField = new JTextField();
    JTextField amountField = new JTextField();
    JTextField dateField = new JTextField();
    JTextField categoryField = new JTextField();
    JTextField descriptionField = new JTextField();
    JButton submitBtn = new JButton("Submit");

    panel.add(new JLabel("Beneficiary ID:")); panel.add(benIDField);
    panel.add(new JLabel("Amount:")); panel.add(amountField);
    panel.add(new JLabel("Expense Date (YYYY-MM-DD):")); panel.add(dateField);
    panel.add(new JLabel("Category:")); panel.add(categoryField);
    panel.add(new JLabel("Description:")); panel.add(descriptionField);
    panel.add(submitBtn);

    frame.add(panel);
    frame.setVisible(true);

    submitBtn.addActionListener(e -> {
        if (!beneficiaryExists(benIDField.getText())) {
            JOptionPane.showMessageDialog(frame, "Beneficiary does not exist. Please enter a valid beneficiary ID.");
            return; 
        }
        if (!eventExists(dateField.getText())) {
            JOptionPane.showMessageDialog(frame, "Event does not exist. Please enter a valid event date.");
            return;
        }
        insertExpense(benIDField.getText(), amountField.getText(), dateField.getText(), categoryField.getText(), descriptionField.getText());
        frame.dispose();
    });
}
    

private boolean beneficiaryExists(String beneficiaryID) {
    try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jakinw1db.Beneficiary WHERE ID = ?")) {
        stmt.setString(1, beneficiaryID);
        ResultSet rs = stmt.executeQuery();
        return rs.next();  // If a row is found, the beneficiary exists.
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;  // Return false in case of an error.
    }
}


private void insertExpense(String beneficiaryID, String amount, String date, String category, String description) {
    try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO jakinw1db.Expense (BeneficiaryID, Amount, ExpDate, Category, Description) VALUES (?, ?, ?, ?, ?)")) {
        stmt.setInt(1, Integer.parseInt(beneficiaryID));
        stmt.setDouble(2, Double.parseDouble(amount));
        stmt.setDate(3, Date.valueOf(date));
        stmt.setString(4, category);
        stmt.setString(5, description);
        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Expense added!");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding expense.");
    }
}


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

        //setting panel into frame and as visible
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

    //---------- Creating Each Table Display
    private void printDonors(){
        try {
            //sql query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donor");
            //create initial frame and size
            JFrame frame = new JFrame("Donors List");
            frame.setSize(400, 400);
            //create content panel with grid and sizes
            JPanel panel = new JPanel(new GridLayout(0,3));
            panel.setSize(100, 20);
            //create scrollbar, set to as needed
            //set the content panel in the scrollbar panel
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            //frame exit button
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> frame.dispose());
            //header labels
            panel.add(new JLabel("Name:"));
            panel.add(new JLabel("Phone #:"));
            panel.add(new JLabel("Donor Type:"));
            int dcount = 0;     //for total number of donors
            while (rs.next()) {
                //getting the SQL query output and printing to JLabel
                String donName = rs.getString("Name");
                String donPhone = rs.getString("Phone");
                String donType = rs.getString("Type");
                panel.add(new JLabel(donName));
                panel.add(new JLabel(donPhone));
                panel.add(new JLabel(donType));
                dcount++;
                //System.out.println(donName+", "+donPhone+", "+donType);
            }
            //total donor count and exit fame button
            panel.add(new JLabel("Total Donor Count: "));
            panel.add(new JLabel(Integer.toString(dcount)));
            panel.add(exit);
            //adding scroll panel to frame
            frame.add(vScroll);
            //set frame to visible
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Getting Table.");
        }
    }

    //all below follow the same pattern
    private void printDonations() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM jakinw1db.Donation");
            JFrame frame = new JFrame("Donations List");
            frame.setSize(500, 400);
            JPanel panel = new JPanel(new GridLayout(0,5));
            panel.setSize(100, 20);
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
            frame.add(vScroll);
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
            frame.setSize(400, 400);
            JPanel panel = new JPanel(new GridLayout(0,4));
            panel.setSize(100, 20);
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
            panel.add(new JLabel());
            panel.add(new JLabel("Total Beneficiaries:"));
            panel.add(new JLabel(Integer.toString(bcount)));
            
            panel.add(exit);
            frame.add(vScroll);
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
            JFrame frame = new JFrame("Events List");
            frame.setSize(800, 400);
            JPanel panel = new JPanel(new GridLayout(0,4));
            panel.setSize(200, 20);
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
            frame.add(vScroll);
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
            JFrame frame = new JFrame("Expenses");
            frame.setSize(500, 400);
            JPanel panel = new JPanel(new GridLayout(0,5));
            panel.setSize(100, 20);
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
            frame.add(vScroll);
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
            JFrame frame = new JFrame("Volunteers");
            frame.setSize(300, 400);
            JPanel panel = new JPanel(new GridLayout(0,3));
            panel.setSize(100, 20);
            JScrollPane vScroll = new JScrollPane(panel);
            vScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
            frame.add(vScroll);
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
