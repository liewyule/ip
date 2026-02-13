package holiday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that start at a specific time and
 * must be completed by a specific date and time.
 * Extends form Task class.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a event task with the specific description and time.
     *
     * @param description Description of the task.
     * @param from The starting time of the task.
     * @param to The deadline of the task.
     */
    public Event(String description, String from, String to) {
        super(description);

        assert description != null && !description.isBlank()
                : "Description should not be empty";

        assert from != null && !from.isBlank()
                : "Event start time should not be empty";

        assert to != null && !to.isBlank()
                : "Event end time should not be empty";

        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
    }


    /**
     * Returns the formatted string of the event task.
     *
     * @return event task formatted as "MMM dd yyyy HHmm".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT)
                + " to: " + this.to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the formatted string of the event task to be saved into hard disk
     * .
     * @return Event task formatted as "MMM dd yyyy HHmm".
     */
    @Override
    public String stringSaveToFile() {
        return "E" + " | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + this.from.format(INPUT_FORMAT) + " | " + this.to.format(INPUT_FORMAT);
    }

    @Override
    public LocalDateTime getTime() {
        return from;
    }
}
