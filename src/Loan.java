import java.time.LocalDate;

public class Loan {

    Member member;
    Book book;
    LocalDate startDate;
    LocalDate endDate;
    boolean isActive;

    public Loan(Member member, Book book, LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.member = member;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

}
