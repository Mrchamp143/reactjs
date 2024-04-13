import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ATMGUI extends JFrame implements ActionListener {
    private JTextField pinField;
    private JTextArea outputTextArea;
    private JButton submitButton; // New submit button

    // Simulated customer data
    private Map<String, Customer> customers = new HashMap<>();
    private final DecimalFormat currencyFormat = new DecimalFormat("#,##0.00");

    public ATMGUI() {
        super("ATM GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JLabel pinLabel = new JLabel("Enter PIN:");
        pinField = new JTextField(10);
        topPanel.add(pinLabel);
        topPanel.add(pinField);

        // Instantiate the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this); // Register action listener
        topPanel.add(submitButton); // Add the submit button to the top panel

        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        // Add some sample customers
        customers.put("1234", new Customer("John Doe", "1111", 1000.0, 2000.0));
        customers.put("5678", new Customer("Jane Smith", "2222", 1500.0, 2500.0));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String pin = pinField.getText();
            Customer customer = customers.get(pin);
            String outputText;
            if (customer != null) {
                outputText = "Customer Name: " + customer.getName() + "\n" +
                        "PIN: " + customer.getPin() + "\n" +
                        "Checking Balance: $" + currencyFormat.format(customer.getCheckingBalance()) + "\n" +
                        "Savings Balance: $" + currencyFormat.format(customer.getSavingsBalance());
            } else {
                outputText = "Invalid PIN. Please try again.";
            }
            outputTextArea.setText(outputText);
            // Display in command line
            System.out.println(outputText);
        }
    }

    public static void main(String[] args) {
        new ATMGUI();
    }
}

class Customer {
    private String name;
    private String pin;
    private double checkingBalance;
    private double savingsBalance;

    public Customer(String name, String pin, double checkingBalance, double savingsBalance) {
        this.name = name;
        this.pin = pin;
        this.checkingBalance = checkingBalance;
        this.savingsBalance = savingsBalance;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }
}
