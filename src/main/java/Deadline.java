/** Represents a deadline task with a specific due date. */
public class Deadline extends Task{
    private String by;
    /**
     * Creates a new Deadline task with the given description and due date.
     *
     * @param description Description of the task.
     * @param by Due date of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation including the [D] prefix and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the due date of the task.
     *
     * @return The due date string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets a new due date for the task.
     *
     * @param by New due date string.
     */
    public void setBy(String by) {
        this.by = by;
    }
}
