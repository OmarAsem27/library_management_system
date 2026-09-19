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

        } catch (Exception e) {
            throw new Exception("\nFailed to create a new member: " + e.getMessage());
        }
    }

    public void registerBook(String title) throws Exception {
        try {
            Book book = new Book(nextBookId, title);
            this.booksList.add(book);
            this.nextBookId++;

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

    public void borrow(int bookId, int memberId) throws Exception {
        Book book = this.findBookById(bookId);
        Member member = this.findMemberById(memberId);
        if (book == null || member == null) {
            throw new Exception("member or book not found");
        }
        this.createLoan(book, member);
    }

    private void createLoan(Book book, Member member) throws Exception {
        this.hasActiveLoan(book, member);
        this.exceededLimit(member);
        // this.bookLoaned(book);

        Loan loan = new Loan(this.nextLoanId, member, book);
        this.loansList.add(loan);
        this.nextLoanId++;
        book.markUnavailable();
    }

    private void hasActiveLoan(Book book, Member member) throws Exception {
        Loan hasLoan = this.loansList.stream()
                .filter(loan -> loan.member.id == member.id && loan.book.id == book.id && loan.isActive)
                .findFirst()
                .orElse(null);
        if (hasLoan != null) {
            throw new Exception("This member has an active loan with the same book.");
        }
    }

    private void exceededLimit(Member member) throws Exception {
        long loansCount = this.loansList.stream()
                .filter(loan -> loan.member.id == member.id && loan.isActive)
                .count();
        if (loansCount >= MAX_BOOKS_ALLOWED) {
            throw new Exception("This member has exceeded the loans limit.");
        }
    }

    // private void bookLoaned(Book book) throws Exception {
    // if (!book.isAvailable) {
    // throw new Exception("This book is unavailable now.");
    // }
    // }

    public void returnBook(int bookId, int memberId) throws Exception {
        Book book = this.findBookById(bookId);
        Member member = this.findMemberById(memberId);
        if (book == null || member == null) {
            throw new Exception("Member or book not found");
        }
        Loan activeLoan = this.loansList.stream()
                .filter(loan -> loan.member.id == member.id && loan.book.id == book.id && loan.isActive)
                .findFirst()
                .orElse(null);

        if (activeLoan == null) {
            throw new Exception("Loan not found with this informations");
        }

        activeLoan.terminate();
        book.markAvailable();
    }

    public List<Loan> getActiveLoans() {
        return this.loansList.stream()
                .filter(loan -> loan.isActive)
                .toList(); // Converts Stream<Loan> to an unmodifiable List<Loan>
    }

    public void deactivateMember(int memberId) throws Exception {
        Member member = this.findMemberById(memberId);
        if (member == null) {
            throw new Exception("Member not found");
        }

        Loan activeLoan = this.loansList.stream()
                .filter(loan -> loan.member.id == member.id && loan.isActive)
                .findFirst()
                .orElse(null);

        if (activeLoan != null) {
            throw new Exception("Can not deactivate member while having active loans.");
        }

        member.markInactive();
    }

    public void retireBook(int bookId) throws Exception {
        Book book = this.findBookById(bookId);

        if (book == null) {
            throw new Exception("Book not found");
        }

        Loan activeLoan = this.loansList.stream()
                .filter(loan -> loan.book.id == book.id && loan.isActive)
                .findFirst()
                .orElse(null);

        if (activeLoan != null) {
            throw new Exception("Can not retire book while having active loans.");
        }

        book.markUnavailable();
    }
}
