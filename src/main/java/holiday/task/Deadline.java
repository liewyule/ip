package holiday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific date and time.
 * Extends form Task class.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected LocalDateTime dueBy;

    /**
     * Constructs a deadline task with the specific description and deadline.
     *
     * @param description Description of the task.
     * @param deadline    Deadline of the task in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String deadline) {
        super(description);

        assert description != null && !description.isBlank()
                : "Description should not be empty";

        assert deadline != null && !deadline.isBlank()
                : "Deadline string should not be empty";

        this.dueBy = LocalDateTime.parse(deadline, INPUT_FORMAT);

        assert dueBy != null
                : "Time should not be null";
    }

    /**
     * Returns the formatted string of the deadline task.
     *
     * @return Deadline task formatted as "MMM dd yyyy HHmm".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueBy.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the formatted string of the deadline task to be saved into hard disk
     * .
     * @return Deadline task formatted as "MMM dd yyyy HHmm".
     */
    @Override
    public String stringSaveToFile() {
        return "D" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description + " | " + this.dueBy.format(INPUT_FORMAT);
    }
}
