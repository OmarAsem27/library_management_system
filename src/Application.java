import java.util.Scanner;

public class Application {

    final int MAX_OPTIONS = 5;
    final int EXIT_OPTION_NUMBER = 5;
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
            System.out.println(EXIT_OPTION_NUMBER + ". Exit");

            if (sc.hasNextInt()) {
                int inp = sc.nextInt();
                sc.nextLine();

                if (inp >= 1 && inp <= MAX_OPTIONS) {
                    if (inp == EXIT_OPTION_NUMBER) {
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
                this.returnBook();
                break;
            case EXIT_OPTION_NUMBER:
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

    private void returnBook() {
        int bookId = getValidInputId("book");
        int memberId = getValidInputId("member");

        try {
            this.library.finishBorrowing(bookId, memberId);
            System.out.println("Book returned successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
