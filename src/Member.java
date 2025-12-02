public class Member {
    private String username;
    private String password;
    private String name;
    private int id;

    public Member(String username, String password, String name, int id) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return name + " (" + username + ")";
    }
}

