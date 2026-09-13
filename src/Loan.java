import java.time.LocalDate;

public class Loan {

    int id;
    Member member;
    Book book;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = null;
    boolean isActive = true;

    public Loan(int id, Member member, Book book) {
        this.id = id;
        this.member = member;
        this.book = book;
        // this.startDate = startDate;
        // this.endDate = endDate;
        // this.isActive = isActive;
    }

}
