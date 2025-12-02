import java.util.ArrayList;

/**
 * Simple in-memory data holder for the app.
 * Keeps books, members and a single librarian account for demo use.
 */
public class LibraryData {
    // single instance (simple singleton)
    private static LibraryData instance = null;

    public ArrayList<Book> books;
    public ArrayList<Member> members;
    public String librarianUsername;
    public String librarianPassword;
    //ArrayList<Book> books = LibraryData.getInstance().books;
    private LibraryData() {
        books = new ArrayList<>();
        members = new ArrayList<>();

        // sample data
        books.add(new Book("Bhagavad Gita", "Srila Prabhupada", "ISBN001", "Spiritual"));
        books.add(new Book("Ramayana", "Valmiki", "ISBN002", "Epic"));

        // sample members (username, password, displayName, id)
        members.add(new Member("lavish", "1111", "Lavish", 101));
        members.add(new Member("radhika", "2222", "Radhika", 102));

        // single librarian account
        librarianUsername = "admin";
        librarianPassword = "1234";
    }

    public static LibraryData getInstance() {
        if (instance == null) instance = new LibraryData();
        return instance;
    }

    // Authenticate librarian
    public boolean authenticateLibrarian(String username, String password) {
        return librarianUsername.equals(username) && librarianPassword.equals(password);
    }

    // Authenticate member — returns Member object if ok, else null
    public Member authenticateMember(String username, String password) {
        for (Member m : members) {
            if (m.getUsername().equals(username) && m.getPassword().equals(password)) {
                return m;
            }
        }
        return null;
    }
}

