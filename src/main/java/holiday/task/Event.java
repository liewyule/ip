package holiday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected LocalDateTime from;
    protected LocalDateTime by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(OUTPUT_FORMAT)
                +  " to: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String saveString() {
        return "E" + " | " + (this.isDone ? "1" : "0")  + " | " + this.description + " | "
                + this.from.format(INPUT_FORMAT) + " | " + this.by.format(INPUT_FORMAT);
    }
}