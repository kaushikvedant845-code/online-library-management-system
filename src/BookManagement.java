import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookManagement extends JFrame implements ActionListener {

    JLabel titleLabel, authorLabel, isbnLabel, genreLabel;
    JTextField titleField, authorField, isbnField, genreField;
    JButton addButton, viewButton, deleteButton, backButton;
    JTextArea displayArea;

    ArrayList<Book> books = LibraryData.getInstance().books;

    public BookManagement() {
        setTitle("📚 Book Management");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Labels and text fields
        titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 30, 100, 25);
        add(titleLabel);
        titleField = new JTextField();
        titleField.setBounds(150, 30, 150, 25);
        add(titleField);

        authorLabel = new JLabel("Author:");
        authorLabel.setBounds(50, 70, 100, 25);
        add(authorLabel);
        authorField = new JTextField();
        authorField.setBounds(150, 70, 150, 25);
        add(authorField);

        isbnLabel = new JLabel("ISBN:");
        isbnLabel.setBounds(50, 110, 100, 25);
        add(isbnLabel);
        isbnField = new JTextField();
        isbnField.setBounds(150, 110, 150, 25);
        add(isbnField);

        genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(50, 150, 100, 25);
        add(genreLabel);
        genreField = new JTextField();
        genreField.setBounds(150, 150, 150, 25);
        add(genreField);

        // Buttons
        addButton = new JButton("➕ Add Book");
        addButton.setBounds(350, 30, 180, 30);
        addButton.addActionListener(this);
        add(addButton);

        viewButton = new JButton("📋 View Books");
        viewButton.setBounds(350, 70, 180, 30);
        viewButton.addActionListener(this);
        add(viewButton);

        deleteButton = new JButton("❌ Delete Book");
        deleteButton.setBounds(350, 110, 180, 30);
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("⬅️ Back");
        backButton.setBounds(350, 150, 180, 30);
        backButton.addActionListener(this);
        add(backButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setBounds(50, 200, 480, 220);
        displayArea.setEditable(false);
        add(displayArea);

        // Background color
        getContentPane().setBackground(new Color(245, 255, 240));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String genre = genreField.getText();

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || genre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            books.add(new Book(title, author, isbn, genre));
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            clearFields();

        } else if (e.getSource() == viewButton) {
            displayArea.setText("");
            if (books.isEmpty()) {
                displayArea.setText("No books available.");
            } else {
                displayArea.append("📚 Book List:\n\n");
                for (Book b : books) {
                    displayArea.append("• " + b.getTitle() + " by " + b.getAuthor() + " (" + b.getGenre() + ") [ISBN: " + b.getIsbn() + "]\n");
                }
            }

        } else if (e.getSource() == deleteButton) {
            String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of book to delete:");
            if (isbn == null || isbn.isEmpty()) return;

            boolean found = false;
            for (Book b : books) {
                if (b.getIsbn().equals(isbn)) {
                    books.remove(b);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                    found = true;
                    break;
                }
            }
            if (!found)
                JOptionPane.showMessageDialog(this, "Book not found!");

        } else if (e.getSource() == backButton) {
            dispose(); // close this window
            new LibrarianDashboard("Librarian"); // go back to dashboard
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        genreField.setText("");
    }

    public static void main(String[] args) {
        new BookManagement();
    }
}