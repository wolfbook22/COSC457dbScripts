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
        setLayout(new GridLayout(6, 1));

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

        add(donorBtn);
        add(eventBtn);
        add(beneficiaryBtn);
        add(volunteerBtn);
        add(donationBtn);
        add(expenseBtn);

        donorBtn.addActionListener(e -> showDonorForm());
        eventBtn.addActionListener(e -> showEventForm());
        beneficiaryBtn.addActionListener(e -> showBeneficiaryForm());
        volunteerBtn.addActionListener(e -> showVolunteerForm());
        donationBtn.addActionListener(e -> showDonationForm());
        expenseBtn.addActionListener(e -> showExpenseForm());
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
        JTextField typeField = new JTextField();
        JButton submitBtn = new JButton("Submit");

        panel.add(new JLabel("ID:")); panel.add(IDField);
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Phone:")); panel.add(phoneField);
        panel.add(new JLabel("Type:")); panel.add(typeField);
        panel.add(submitBtn);

        frame.add(panel);
        frame.setVisible(true);
        submitBtn.addActionListener(e -> {
            insertBeneficiary(IDField.getText(), nameField.getText(), phoneField.getText(), typeField.getText());
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

    
    
    private void showVolunteerForm() { /* similar pattern */ }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NonProfitDBApp().setVisible(true));
    }
}
