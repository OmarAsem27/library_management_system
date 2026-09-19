import java.util.List;
import java.util.Scanner;

public class Application {

    final int MAX_OPTIONS = 8;
    Scanner sc;
    Library library;

    Application() {
        this.sc = new Scanner(System.in);
        this.library = new Library();
        System.out.println(
                "\nWelcome to the Library management system.\n");
    }

    public void collectUserInput() {
        boolean appRunning = true;

        while (appRunning) {
            System.out.println("please, enter your wanted operation number between the following choices:");
            System.out.println("1. Add Member");
            System.out.println("2. Add Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Active Loans");
            System.out.println("6. Deactivate Member");
            System.out.println("7. Retire Book");
            System.out.println(MAX_OPTIONS + ". Exit");

            if (sc.hasNextInt()) {
                int inp = sc.nextInt();
                sc.nextLine();

                if (inp >= 1 && inp <= MAX_OPTIONS) {
                    if (inp == MAX_OPTIONS) {
                        appRunning = false;
                    }

                    this.processUserRequest(inp);
                    continue;
                }
            } else {
                sc.nextLine();
            }
            System.out.printf("Invalid choice! Please enter a number between 1 and %d.%n%n", MAX_OPTIONS);
        }
    }

    private void processUserRequest(int input) {
        switch (input) {
            case 1:
                this.addMember();
                break;
            case 2:
                this.addBook();
                break;
            case 3:
                this.borrowBook();
                break;
            case 4:
                this.finishBorrowing();
                break;
            case 5:
                this.printActiveLoans();
                break;
            case 6:
                this.endMembership();
                break;
            case 7:
                this.removeBook();
                break;
            case MAX_OPTIONS:
                this.exitApp();
                break;
            default:
                break;
        }
    }

    private void addMember() {
        System.out.println("please enter the name.");
        String name = this.sc.nextLine();

        try {
            this.library.registerMember(name);
            System.out.println(
                    "\nMember was added successfully.\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void exitApp() {
        System.out.println("Thank you for using our application.");
        System.exit(0);
    }

    private void addBook() {
        System.out.println("please enter the title.");
        String title = this.sc.nextLine();

        try {
            this.library.registerBook(title);
            System.out.println(
                    "\nBook was added successfully.\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void borrowBook() {
        int bookId = getValidInputId("book");
        int memberId = getValidInputId("member");

        try {
            this.library.borrow(bookId, memberId);
            System.out.println("Book borrowed successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int getValidInputId(String inputName) {
        int inp = -1;

        System.out.printf("Enter the ID of the %s.%n", inputName);
        while (inp < 0) {
            /*
             * Handling Bad Input: When the user enters a non-integer, the else block runs.
             * It prints your error message and calls sc.next() to clear the invalid token
             * from the scanner buffer so the program can ask again.
             */
            if (this.sc.hasNextInt()) {
                inp = this.sc.nextInt();
                this.sc.nextLine(); // Clear the leftover newline from buffer
                if (inp < 0) {
                    System.out.println("Error: The number must be greater or equal to 0.");
                }
            } else {
                System.out.println("Invalid input, please try again.");
                this.sc.next();
            }
        }
        return inp;
    }

    private void finishBorrowing() {
        int bookId = getValidInputId("book");
        int memberId = getValidInputId("member");

        try {
            this.library.returnBook(bookId, memberId);
            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void printActiveLoans() {
        List<Loan> activeLoans = this.library.getActiveLoans();

        if (activeLoans.isEmpty()) {
            System.out.println("No active loans found.");
        } else {
            for (int i = 0; i < activeLoans.size(); i++) {
                System.out.printf("Loan number: %d, id: %d, book id: %d, member id: %d, startDate: %s, endDate: %s.%n",
                        i,
                        activeLoans.get(i).id,
                        activeLoans.get(i).book.id,
                        activeLoans.get(i).member.id,
                        activeLoans.get(i).startDate,
                        activeLoans.get(i).endDate);
            }
        }
    }

    private void endMembership() {
        int memberId = getValidInputId("member");

        try {
            this.library.deactivateMember(memberId);
            System.out.println("The member deactivated successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void removeBook() {
        int memberId = getValidInputId("book");

        try {
            this.library.retireBook(memberId);
            System.out.println("The book removed successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
