import java.util.ArrayList;
import java.util.Scanner;

public class Jojo {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(LINE);
        System.out.println("Hello! I'm Jojo\nWhat can I do for you?");
        System.out.println(LINE);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (input.equals("list")) {
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println(LINE);
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task t = tasks.get(index);
                t.markAsDone();
                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Task t = tasks.get(index);
                t.markAsNotDone();
                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + t);
                System.out.println(LINE);
            } else {
                if (input.startsWith("todo")) {
                    String description = input.substring(5).trim();
                    Task t = new Todo(description);
                    tasks.add(t);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                } else if (input.startsWith("deadline")) {
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
                } else if (input.startsWith("event")) {
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
            }
        }
        sc.close();
    }
}
