import java.util.ArrayList;
import java.util.Scanner;

public class Jojo {
    private static final String LINE = "____________________________________________________________";

    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    
    private static final ArrayList<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(LINE);
        System.out.println("Hello! I'm Jojo\nWhat can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals(CMD_BYE)) {
                    showExitMessage();
                    break;
                } else if (input.equals(CMD_LIST)) {
                    showTaskList();
                } else if (input.startsWith(CMD_MARK)) {
                    handleMark(input);
                } else if (input.startsWith(CMD_UNMARK)) {
                    handleUnmark(input);
                } else if (input.startsWith(CMD_TODO)) {
                    handleTodo(input);
                } else if (input.startsWith(CMD_DEADLINE)) {
                    handleDeadline(input);
                } else if (input.startsWith(CMD_EVENT)) {
                    handleEvent(input);
                } else {
                    throw new DukeException("Sorry!!! I don’t understand the command: " + input);
                }
            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println("Oh no!!!! " + e.getMessage());
                System.out.println(LINE);
            }

        }
        sc.close();
    }

    private static void handleEvent(String input) {
        int fromPosition = input.indexOf("/from");
        int toPosition = input.indexOf("/to");
        String description = input.substring(6, fromPosition).trim();
        String from = input.substring(fromPosition + 6, toPosition).trim();
        String to = input.substring(toPosition + 4).trim();
        Task t = new Event(description, from, to);
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void handleDeadline(String input) {
        int byPosition = input.indexOf("/by");
        String description = input.substring(9, byPosition).trim();
        String by = input.substring(byPosition + 4).trim();
        Task t = new Deadline(description, by);
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void handleTodo(String input) throws DukeException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of todo can't be empty :(");
        }
        Task t = new Todo(description);
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void handleUnmark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task t = tasks.get(index);
        t.markAsNotDone();
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    private static void handleMark(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    private static void showTaskList() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static void showExitMessage() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
