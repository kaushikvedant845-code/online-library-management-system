import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {

    private JTextField userField;
    private JPasswordField passField;
    private JComboBox<String> roleBox;
    private JButton loginBtn, clearBtn;

    public LoginPage() {
        setTitle("Online Library - Login");
        setSize(420, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Library Management System");
        heading.setFont(new Font("Arial", Font.BOLD, 18));
        heading.setBounds(70, 15, 300, 30);
        add(heading);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setBounds(40, 70, 80, 25);
        add(uLabel);
        userField = new JTextField();
        userField.setBounds(130, 70, 220, 25);
        add(userField);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setBounds(40, 110, 80, 25);
        add(pLabel);
        passField = new JPasswordField();
        passField.setBounds(130, 110, 220, 25);
        add(passField);

        JLabel rLabel = new JLabel("Role:");
        rLabel.setBounds(40, 150, 80, 25);
        add(rLabel);
        String[] roles = {"Librarian", "Member"};
        roleBox = new JComboBox<>(roles);
        roleBox.setBounds(130, 150, 220, 25);
        add(roleBox);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 200, 90, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(220, 200, 90, 30);
        clearBtn.addActionListener(this);
        add(clearBtn);

        getContentPane().setBackground(new Color(245, 245, 250));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            userField.setText("");
            passField.setText("");
            return;
        }

        String username = userField.getText().trim();
        String password = new String(passField.getPassword()).trim();
        String role = (String) roleBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill both username and password.");
            return;
        }

        LibraryData data = LibraryData.getInstance();

        if ("Librarian".equals(role)) {
            if (data.authenticateLibrarian(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful. Welcome Librarian!");
                dispose();
                // LibrarianDashboard should have a constructor that accepts username (we provided earlier)
                new LibrarianDashboard(username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid librarian credentials.");
            }
        } else { // Member
            Member m = data.authenticateMember(username, password);
            if (m != null) {
                JOptionPane.showMessageDialog(this, "Login successful. Welcome " + m.getName() + "!");
                dispose();
                new MemberDashboard(m.getName()); // MemberDashboard(String name) expected
            } else {
                JOptionPane.showMessageDialog(this, "Invalid member username or password.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
