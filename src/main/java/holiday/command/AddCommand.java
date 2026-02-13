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

    private final String type;
    private final String description;
    private final String from;
    private final String to;

    /**
     * Construct a addcomand
     * @param type
     * @param description
     * @param from
     * @param to
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
     *     Creates different task base on the input task typr
     *     (e.g. "todo", "deadline", "event")
     * </p>
     *
     * @param tasks Task list to add the task to.
     * @param ui UI for displaying feedback.
     * @param storage
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        switch (this.type) {
        case "todo": {
            Task task = new ToDo(description);
            tasks.add(task);
            storage.saveTask(tasks);
            return ui.printAddedTask(task, tasks.size());
        }
        case "deadline": {

            assert to != null && !to.isBlank()
                    : "due date must be specify";

            Task task = new Deadline(description, to);
            tasks.add(task);
            storage.saveTask(tasks);
            return ui.printAddedTask(task, tasks.size());
        }
        case "event": {
            Task task = new Event(description, from, to);
            tasks.add(task);
            storage.saveTask(tasks);
            return ui.printAddedTask(task, tasks.size());
        }
        default: {
            return ui.printError();
        }
        }
    }
}
