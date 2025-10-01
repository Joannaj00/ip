/** Represents a to-do task without a specific date/time. */
public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the to-do task.
     *
     * @return String representation including the [T] prefix.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
