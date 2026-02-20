package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.Deadline;
import holiday.task.Event;
import holiday.task.Task;
import holiday.task.TaskList;
import holiday.task.ToDo;
import holiday.ui.Ui;

/**
 * Represents a command that adds a new task to the task list.
 */
public class AddCommand extends Command {

    public static final String TODO_TYPE = "todo";
    public static final String DEADLINE_TYPE = "deadline";
    public static final String EVENT_TYPE = "event";

    private final String type;
    private final String description;
    private final String from;
    private final String to;

    /**
     * Construct a addcomand
     *
     * @param type Type of the task to be added (e.g. "todo", "deadline", "event")
     * @param description Description of the task to be added
     * @param from Start date of the task to be added (only for event)
     * @param to Due date of the task to be added (only for deadline and event)
     */
    public AddCommand(String type, String description, String from, String to) {
        this.type = type;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Execute the add command by creating the corresponding task.
     * and adding it to the task list.
     *
     * <p>
     * Creates different task base on the input task typr
     * (e.g. "todo", "deadline", "event")
     * </p>
     *
     * @param tasks   Task list to add the task to.
     * @param ui      UI for displaying feedback.
     * @param storage Storage to save the task
     * @throws BotException if there is an error during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        Task task = createTask();

        if (task == null) {
            return ui.printError();
        }

        tasks.add(task);
        storage.saveTask(tasks);
        return ui.printAddedTask(task, tasks.size());
    }

    /**
     * Create a task based on the input type and description.
     * @return A task object based on the input type and description, or null if the type is invalid.
     */
    private Task createTask() throws BotException {
        switch (type) {
        case TODO_TYPE:
            return new ToDo(description);
        case DEADLINE_TYPE:
            assert to != null && !to.isBlank()
                    : "due date must be specify";
            return new Deadline(description, to);
        case EVENT_TYPE:
            assert from != null && !from.isBlank()
                    : "start date must be specify";

            assert to != null && !to.isBlank()
                    : "end date must be specify";
            return new Event(description, from, to);
        default:
            return null;
        }
    }
}
