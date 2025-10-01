/**
 * Represents the main class for the Jojo chatbot.
 * Jojo is a task management chatbot that can add, list, find,
 * delete, and mark tasks, with persistent storage.
 */
public class Jojo {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_FIND = "find";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a new Jojo chatbot instance.
     * Loads previously saved tasks from the specified file path.
     */
    public Jojo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot loop until the user enters the bye command.
     * Commands are parsed and executed sequentially.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String rawInput = ui.readCommand();
            String input = Parser.parse(rawInput); // 🟡 Parser added here

            try {
                if (input.equals(CMD_BYE)) {
                    ui.showExitMessage();
                    storage.save(tasks.getAll());
                    isExit = true;
                } else if (input.equals(CMD_LIST)) {
                    tasks.showAll(ui);
                } else if (input.startsWith(CMD_TODO)) {
                    handleTodo(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_DEADLINE)) {
                    handleDeadline(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_EVENT)) {
                    handleEvent(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_MARK)) {
                    handleMark(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_UNMARK)) {
                    handleUnmark(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_DELETE)) {
                    handleDelete(input);
                    storage.save(tasks.getAll());
                } else if (input.startsWith(CMD_FIND)) {
                    handleFind(input);
                } else {
                    throw new DukeException("Sorry!!! I don’t understand the command: " + input);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    private void handleEvent(String input) throws DukeException {
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
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private void handleDeadline(String input) throws DukeException {
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
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private void handleTodo(String input) throws DukeException {
        if (input.length() <= 4) {
            throw new DukeException("The description of todo can't be empty :(");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of todo can't be empty :(");
        }
        Task t = new Todo(description);
        tasks.add(t);
        ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private void handleUnmark(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("You need provide a task number after 'unmark'");
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer");
        }
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task number is out of range");
        }

        Task t = tasks.get(index);
        t.markAsNotDone();
        ui.showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        ui.showLine();
    }

    private void handleMark(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new DukeException("You need provide a task number after 'unmark'");
        }
        int index;
        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number must be a valid integer");
        }
        if (index < 0 || index > tasks.size() - 1) {
            throw new DukeException("Task number is out of range");
        }

        Task t = tasks.get(index);
        t.markAsDone();
        ui.showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        ui.showLine();
    }

    private void handleDelete(String input) throws DukeException {
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
        ui.showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }

    private void handleFind(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new DukeException("You need to specify a keyword to search for.");
        }

        String keyword = parts[1].trim();
        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        tasks.find(keyword);
        ui.showLine();
    }

    public static void main(String[] args) {
        new Jojo().run();
    }
}
