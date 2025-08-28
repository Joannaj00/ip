import java.util.ArrayList;
import java.util.Scanner;
public class Jojo {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println(LINE);
        System.out.println("Hello! I'm Jojo\n What can I do for you?");
        System.out.println(LINE);

        while(true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (input.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println(LINE);
            } else {
                tasks.add(input);
                System.out.println(LINE);
                System.out.println("added: " + input);
                System.out.println(LINE);
            }
        }
        sc.close();
    }
}
