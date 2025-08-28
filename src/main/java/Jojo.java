import java.util.Scanner;
public class Jojo {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(LINE);
        System.out.println(" " + "Hello! I'm Jojo\n What can I do for you?");
        System.out.println(LINE);

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }
            System.out.println(LINE);
            System.out.println(input);
            System.out.println(LINE);
        }
        sc.close();
    }
}
