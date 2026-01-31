package holiday.task;

/**
 * Represents a task with a description.
 * Extends form Task class.
 */
public class ToDos extends Task{

    /**
     * Constructs a todos task with the specific description.
     *
     * @param description Description of the task.
     */
    public ToDos(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveString() {
        return "T" + " | " + (this.isDone ? "1" : "0")
                + " | " + this.description;
    }
}
