import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberDashboard extends JFrame implements ActionListener {

    private JButton viewBooksButton, logoutButton;
    private JLabel welcomeLabel;
    private String memberName;

    public MemberDashboard(String memberName) {
        this.memberName = memberName;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Member Dashboard - " + memberName);
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Welcome, " + memberName + " 🙏");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBounds(130, 50, 300, 30);
        add(welcomeLabel);

        viewBooksButton = new JButton("📖 View Available Books");
        viewBooksButton.setBounds(150, 120, 200, 40);
        viewBooksButton.addActionListener(this);
        add(viewBooksButton);

        logoutButton = new JButton("🚪 Logout");
        logoutButton.setBounds(150, 180, 200, 40);
        logoutButton.addActionListener(this);
        add(logoutButton);

        getContentPane().setBackground(new Color(245, 250, 255));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewBooksButton) {
            showBooks();
        } else if (e.getSource() == logoutButton) {
            dispose();
            new LoginPage();
        }
    }

    private void showBooks() {
        StringBuilder sb = new StringBuilder("Available Books:\n\n");
        for (Book b : LibraryData.getInstance().books) {
            sb.append(b.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}
