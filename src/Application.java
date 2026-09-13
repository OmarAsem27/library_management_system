import java.util.Scanner;

public class Application {

    final int MAX_OPTIONS = 2;
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
            System.out.println("2. Exit");

            if (sc.hasNextInt()) {
                int inp = sc.nextInt();
                sc.nextLine();

                if (inp >= 1 && inp <= MAX_OPTIONS) {
                    if (inp == 2) {
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
                this.AddMember();
                break;
            case 2:
                this.exitApp();
                break;
            default:
                break;
        }
    }

    private void AddMember() {
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

}
