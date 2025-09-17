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
    private static final String CMD_DELETE = "delete";
    
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
                } else if (input.startsWith(CMD_DELETE)) {
                    handleDelete(input);
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

    private static void handleEvent(String input) throws DukeException {
        int fromPosition = input.indexOf("/from");
        int toPosition = input.indexOf("/to");
        if (fromPosition == -1 && toPosition == -1) {
            throw new DukeException("An event must include both /from and /to");
        }
        String description = input.substring(6, fromPosition).trim();
        String from = input.substring(fromPosition + 6, toPosition).trim();
        String to = input.substring(toPosition + 4).trim();
        if (description.isEmpty() || to.isEmpty() || from.isEmpty()) {
            throw new DukeException("Event needs description, /from, and /to fields");
        }
        Task t = new Event(description, from, to);
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void handleDeadline(String input) throws DukeException {
        int byPosition = input.indexOf("/by");
        if (byPosition == -1) {
            throw new DukeException("An deadline must include /by");
        }
        String description = input.substring(9, byPosition).trim();
        String by = input.substring(byPosition + 4).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("Deadline needs description, /by");
        }
        Task t = new Deadline(description, by);
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private static void handleTodo(String input) throws DukeException {
        if (input.length() <= 4) {
            throw new DukeException("The description of todo can't be empty :(");
        }
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

    private static void handleUnmark(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("You need provide a task number after 'unmark'");
        }
        int index;
        try{
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer");
        }
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task number is out of range");
        }

        Task t = tasks.get(index);
        t.markAsNotDone();
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        System.out.println(LINE);
    }

    private static void handleMark(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("You need provide a task number after 'unmark'");
        }
        int index;
        try{
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer");
        }
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task number is out of range");
        }

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

    private static void handleDelete(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("You need to add a number after 'delete'");
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task number is out of range");
        }

        Task removed = tasks.remove(index);
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }
}
