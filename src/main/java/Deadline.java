import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    protected LocalDateTime by;

    public Deadline(String description, String deadline) {
        super(description);
        this.by = LocalDateTime.parse(deadline, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String saveString() {
        return "D" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description + " | " + this.by.format(INPUT_FORMAT);
    }
}