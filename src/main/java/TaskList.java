import java.util.ArrayList;

/** Represents a list of tasks and provides operations to manage them. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList initialized with the given tasks.
     *
     * @param tasks Existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Creates an empty TaskList.*/
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index Index of the task.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index Index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return The task list.
     */
    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Displays all tasks in the list.
     *
     * @param ui Ui instance to show divider lines.
     */
    public void showAll(Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        ui.showLine();
    }

    /**
     * Finds and displays tasks whose description contains the given keyword.
     *
     * @param keyword Keyword to search for.
     */
    public void find(String keyword) {
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(count + "." + task);
                count++;
            }
        }
    }

}
