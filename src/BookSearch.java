import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookSearch extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton, backButton;
    private JTextArea resultArea;
    private ArrayList<Book> books; // uses the Book class in Book.java

    public BookSearch(ArrayList<Book> sharedBooks) {
        // Allow passing in the real library list (preferred)
        this.books = (sharedBooks != null) ? sharedBooks : sampleBooks();

        setTitle("🔍 Book Search");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel searchLabel = new JLabel("Enter Title, Author, or Genre:");
        searchLabel.setBounds(30, 20, 250, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(280, 20, 220, 25);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(200, 60, 100, 30);
        searchButton.addActionListener(this);
        add(searchButton);

        backButton = new JButton("Back");
        backButton.setBounds(320, 60, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane sp = new JScrollPane(resultArea);
        sp.setBounds(30, 110, 520, 230);
        add(sp);

        getContentPane().setBackground(new Color(255, 250, 240));
        setVisible(true);
    }

    // fallback sample books if none passed
    private ArrayList<Book> sampleBooks() {
        ArrayList<Book> list = new ArrayList<>();
        list.add(new Book("Harry Potter", "J.K. Rowling", "101", "Fantasy"));
        list.add(new Book("Rich Dad Poor Dad", "Robert Kiyosaki", "102", "Finance"));
        list.add(new Book("The Alchemist", "Paulo Coelho", "103", "Adventure"));
        list.add(new Book("Java Programming", "Herbert Schildt", "104", "Education"));
        return list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String q = searchField.getText().trim().toLowerCase();
            resultArea.setText("");
            if (q.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a search term!");
                return;
            }

            boolean found = false;
            for (Book book : books) {
                // use book.getTitle(), book.getAuthor(), book.getGenre()
                String title = book.getTitle().toLowerCase();
                String author = book.getAuthor().toLowerCase();
                String genre = ""; // defensive: Book might not have getGenre; adapt if needed
                try {
                    // if your Book class has getGenre(), use it
                    genre = (String) Book.class.getMethod("getGenre").invoke(book);
                    genre = genre.toLowerCase();
                } catch (Exception ex) {
                    // ignore if no getGenre() — keep genre empty
                }

                if (title.contains(q) || author.contains(q) || genre.contains(q)) {
                    resultArea.append(book.toString() + "\n");
                    found = true;
                }
            }

            if (!found) resultArea.setText("No books found matching your search.");
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    // quick standalone test main (will use sample books)
    public static void main(String[] args) {
        new BookSearch(null);
    }
}
