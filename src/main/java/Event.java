/** Represents an event task that has a start and end time.*/
public class Event extends Task {
    private String from;
    private String to;
    /**
     * Creates an Event with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from Start time of the event.
     * @param to End time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return String representation including [E] prefix and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return Start time string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start time of the event.
     *
     * @param from New start time string.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return End time string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end time of the event.
     *
     * @param to New end time string.
     */
    public void setTo(String to) {
        this.to = to;
    }
}
