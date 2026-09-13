import java.util.Scanner;

public class Application {

    final int MAX_OPTIONS = 3;
    final int EXIT_OPTION_NUMBER = 3;
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
            System.out.println("3. Exit");

            if (sc.hasNextInt()) {
                int inp = sc.nextInt();
                sc.nextLine();

                if (inp >= 1 && inp <= MAX_OPTIONS) {
                    if (inp == EXIT_OPTION_NUMBER) {
                        appRunning = false;
                    } else {
                        this.processUserRequest(inp);
                    }
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

}
