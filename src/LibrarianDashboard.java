import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibrarianDashboard extends JFrame implements ActionListener {

    private JButton manageBooksButton, viewMembersButton, logoutButton;
    private JLabel welcomeLabel;
    private String librarianName;

    public LibrarianDashboard(String librarianName) {
        this.librarianName = librarianName;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Librarian Dashboard - " + librarianName);
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Welcome, " + librarianName + " 👋");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(130, 40, 300, 30);
        add(welcomeLabel);

        manageBooksButton = new JButton("📚 Manage Books");
        manageBooksButton.setBounds(150, 100, 200, 40);
        manageBooksButton.addActionListener(this);
        add(manageBooksButton);

        viewMembersButton = new JButton("👥 View Members");
        viewMembersButton.setBounds(150, 160, 200, 40);
        viewMembersButton.addActionListener(this);
        add(viewMembersButton);

        logoutButton = new JButton("🚪 Logout");
        logoutButton.setBounds(150, 220, 200, 40);
        logoutButton.addActionListener(this);
        add(logoutButton);

        getContentPane().setBackground(new Color(240, 255, 245));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageBooksButton) {
            dispose();
            new BookManagement(); // Book management window
        } else if (e.getSource() == viewMembersButton) {
            showMembers();
        } else if (e.getSource() == logoutButton) {
            dispose();
            new LoginPage();
        }
    }

    private void showMembers() {
        StringBuilder sb = new StringBuilder("Registered Members:\n\n");
        for (Member m : LibraryData.getInstance().members) {
            sb.append(m.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
