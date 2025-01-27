import java.awt.*;
import java.awt.event.*;

public class SimpleATMApp extends Frame implements ActionListener {
    // Components of the ATM
    Label balanceLabel, depositLabel, withdrawLabel;
    TextField depositAmount, withdrawAmount;
    TextArea displayArea;
    Button checkBalanceButton, depositButton, withdrawButton;
    
    // Account balance
    double balance = 1000; // Default balance

    public SimpleATMApp() {
        // Frame setup
        setTitle("Simple ATM Application");
        setSize(400, 300);
        setLayout(null);
        
        // Initialize components
        balanceLabel = new Label("Balance: $" + balance);
        depositLabel = new Label("Deposit Amount:");
        withdrawLabel = new Label("Withdraw Amount:");
        
        depositAmount = new TextField();
        withdrawAmount = new TextField();
        
        checkBalanceButton = new Button("Check Balance");
        depositButton = new Button("Deposit");
        withdrawButton = new Button("Withdraw");
        
        displayArea = new TextArea();
        displayArea.setEditable(false);
        
        // Set component positions and sizes
        balanceLabel.setBounds(20, 50, 200, 30);
        depositLabel.setBounds(20, 100, 100, 30);
        withdrawLabel.setBounds(20, 150, 120, 30);
        
        depositAmount.setBounds(140, 100, 100, 30);
        withdrawAmount.setBounds(140, 150, 100, 30);
        
        checkBalanceButton.setBounds(250, 50, 120, 30);
        depositButton.setBounds(250, 100, 120, 30);
        withdrawButton.setBounds(250, 150, 120, 30);
        
        displayArea.setBounds(20, 200, 350, 60);
        
        // Add components to the frame
        add(balanceLabel);
        add(depositLabel);
        add(withdrawLabel);
        add(depositAmount);
        add(withdrawAmount);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(displayArea);
        
        // Add action listeners
        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        
        // Set frame visibility
        setVisible(true);
    }
    
    // Action listener method
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Check Balance")) {
            displayArea.setText("Current Balance: $" + balance);
        } 
        else if (command.equals("Deposit")) {
            try {
                double deposit = Double.parseDouble(depositAmount.getText());
                if (deposit > 0) {
                    balance += deposit;
                    displayArea.setText("Deposited: $" + deposit + "\nNew Balance: $" + balance);
                } else {
                    displayArea.setText("Invalid deposit amount!");
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Please enter a valid deposit amount.");
            }
        } 
        else if (command.equals("Withdraw")) {
            try {
                double withdraw = Double.parseDouble(withdrawAmount.getText());
                if (withdraw > 0 && withdraw <= balance) {
                    balance -= withdraw;
                    displayArea.setText("Withdrawn: $" + withdraw + "\nNew Balance: $" + balance);
                } else if (withdraw > balance) {
                    displayArea.setText("Insufficient balance!");
                } else {
                    displayArea.setText("Invalid withdrawal amount!");
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Please enter a valid withdrawal amount.");
            }
        }
    }
    
    // Main method to launch the application
    public static void main(String[] args) {
        SimpleATMApp atmApp = new SimpleATMApp();
        
        // Window Closing Event
        atmApp.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
