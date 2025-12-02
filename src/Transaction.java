public class Transaction {
    private String memberName;
    private Book book;
    private String date;
    private String type; // "Issue" or "Return"

    public Transaction(String memberName, Book book, String date, String type) {
        this.memberName = memberName;
        this.book = book;
        this.date = date;
        this.type = type;
    }

    public String getMemberName() {
        return memberName;
    }

    public Book getBook() {
        return book;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return memberName + " " + type + "d " + book.getTitle() + " on " + date;
    }

}
