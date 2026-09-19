import java.time.LocalDate;

public class Loan {

    int id;
    Member member;
    Book book;
    LocalDate startDate = LocalDate.now();
    LocalDate endDate = null;
    boolean isActive = true;

    // aggregation (weaker form of composition)
    // (weak "has-a" relationship in object-oriented programming)
    public Loan(int id, Member member, Book book) {
        this.id = id;
        this.member = member;
        this.book = book;
    }

    public void terminate() throws Exception {
        if (!this.isActive) {
            throw new Exception("This loan is already inactive.");
        }

        if (this.endDate != null) {
            throw new Exception("This loan has already ended.");
        }
        this.endDate = LocalDate.now();
        this.isActive = false;
    }

}
