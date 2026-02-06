package holiday.task;

/**
 * Represents a task with a description.
 * Extends form Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a todos task with the specific description.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String stringSaveToFile() {
        return "T" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description;
    }
}
