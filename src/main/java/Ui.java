import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Jojo\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void showError(String message) {
        System.out.println(LINE);
        System.out.println("Oh no!!!! " + message);
        System.out.println(LINE);
    }

    public void close() {
        sc.close();
    }
}
