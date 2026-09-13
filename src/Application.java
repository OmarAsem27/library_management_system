import java.util.Scanner;

public class Application {

    final int MAX_OPTIONS = 2;
    Scanner sc;

    Application() {
        this.sc = new Scanner(System.in);
        System.out.println(
                "\nWelcome to the Library management system.\n");
    }

    public void collectUserInput() {
        int inp = 0;

        while (true) {
            System.out.println("please, enter your wanted operation number between the following choices:");
            System.out.println("1. Add Member");
            System.out.println("2. Exit");
            if (sc.hasNextInt()) {
                inp = sc.nextInt();
                sc.nextLine(); // Clear the leftover newline from the buffer (user write 1 it will be 1\n)
                if (inp >= 1 && inp <= MAX_OPTIONS) {
                    break;
                }
            } else {
                sc.nextLine();
            }
            System.out.printf("Invalid choice! Please enter a number between 1 and %d.%n%n", MAX_OPTIONS);
        }
    }

}
