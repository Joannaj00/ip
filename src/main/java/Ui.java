import java.util.Scanner;

/** Handles all interactions with the user, including displaying messages and reading input.*/
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner sc = new Scanner(System.in);

    /** Displays the welcome message when the program starts. */
    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Jojo\nWhat can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Reads a line of user input.
     *
     * @return The input string entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Displays a divider line to separate sections of output. */
    public void showLine() {
        System.out.println(LINE);
    }

    /** Displays the exit message when the program ends. */
    public void showExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Displays an error message in a formatted block.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(LINE);
        System.out.println("Oh no!!!! " + message);
        System.out.println(LINE);
    }

    /** Closes the scanner used to read user input. */
    public void close() {
        sc.close();
    }
}
