import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Member> membersList = new ArrayList<>();
    List<Book> booksList = new ArrayList<>();
    List<Loan> loansList = new ArrayList<>();

    int nextMemberId = 0;
    int nextBookId = 0;
    int nextLoanId = 0;

    public void registerMember(String name) throws Exception {
        try {
            Member member = new Member(nextMemberId, name);
            this.membersList.add(member);
            this.nextMemberId++;

            for (Member mem : this.membersList) {
                System.out.printf("Member of id %d: name: %s, status: %s%n", mem.id, mem.name, mem.isActive);
            }
        } catch (Exception e) {
            throw new Exception("\nFailed to create a new member: " + e.getMessage());
        }
    }

    public void registerBook(String title) throws Exception {
        try {
            Book book = new Book(nextBookId, title);
            this.booksList.add(book);
            this.nextBookId++;

            for (Book bk : this.booksList) {
                System.out.printf("Book of id %d: title: %s, status: %s%n", bk.id, bk.title, bk.isAvailable);
            }
        } catch (Exception e) {
            throw new Exception("\nFailed to create a new book: " + e.getMessage());
        }
    }

}
