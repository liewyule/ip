public class Event extends Task {

    protected String from;
    protected String by;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from +  " to: " + this.by + ")";
    }

    @Override
    public String saveString() {
        return "E" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description + " | " + this.from + " | " + this.by;
    }
}