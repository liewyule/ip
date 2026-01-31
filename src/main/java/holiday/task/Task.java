package holiday.task;

/**
 * Represents a generic task with description and completion status.
 * <p>
 *     Specific task types such as ToDos, Deadline, and Event extends this class.
 * </p>
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return "X" if the task is done, blank otherwise.
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as  not complete.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to store in hard disk.
     * Subclasses usually override this method.
     *
     * @return Storage representation of the task.
     */
    public String saveString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}