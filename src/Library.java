import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Member> membersList = new ArrayList<>();
    List<Book> booksList = new ArrayList<>();
    List<Loan> loansList = new ArrayList<>();

    int nextMemberId = 0;
    int nextBookId = 0;
    int nextLoanId = 0;
    final int MAX_BOOKS_ALLOWED = 5;

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

    private Member findMemberById(int id) {
        return this.membersList.stream()
                .filter(member -> member.id == id)
                .findFirst()
                .orElse(null);
    }

    private Book findBookById(int id) {
        return this.booksList.stream()
                .filter(book -> book.id == id)
                .findFirst()
                .orElse(null);
    }

    public void borrow(int bookId, int memberId) {
        Book book = this.findBookById(bookId);
        Member member = this.findMemberById(memberId);
        this.createLoan(book, member);
        System.out.println("BORROWWWWWWW.");
    }

    private void createLoan(Book book, Member member) {
        // check if the book is available
        // check if the member allowed to borrow (hasn't exceeded the limit)
        // check if the member doesn't have an active loan with same book
        // check if there's any other reason to not create the loan

        // boolean hasLoan = this.hasActiveLoan(book, member);
        // if (!hasLoan) {

        // }
        Loan loan = new Loan(this.nextLoanId, member, book);
        this.loansList.add(loan);
        this.nextLoanId++;
        for (Loan bk : this.loansList) {
            System.out.printf("Loan of id %d: member id: %d,book id: %d,started at: %s, ends at: %s, acive: %s,%n",
                    bk.id,
                    bk.member.id,
                    bk.book.id,
                    bk.startDate,
                    bk.endDate,
                    bk.isActive);
        }
    }

    private void hasActiveLoan(Book book, Member member) throws Exception {
        Loan hasLoan = this.loansList.stream()
                .filter(loan -> loan.member.id == member.id && loan.book.id == book.id)
                .findFirst()
                .orElse(null);
        if (hasLoan == null) {
            throw new Exception("This member has an active loan with the same book.");
        }
    }

    private boolean exceededLimit(Member member) {

        return true;
    }

    private boolean bookLoaned(Book book) {

        return true;
    }

}
