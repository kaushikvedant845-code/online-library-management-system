import java.util.ArrayList;

public class MemberManagement {
    private ArrayList<String> members = new ArrayList<>();

    public void addMember(String name) {
        if (!members.contains(name)) {
            members.add(name);
            System.out.println("Member added: " + name);
        } else {
            System.out.println("Member already exists!");
        }
    }

    public void removeMember(String name) {
        if (members.remove(name)) {
            System.out.println("Member removed: " + name);
        } else {
            System.out.println("Member not found!");
        }
    }

    public void listMembers() {
        System.out.println("Members:");
        for (String m : members) {
            System.out.println("- " + m);
        }
    }
}
