package holiday.command;

import holiday.ui.Ui;
import holiday.storage.Storage;
import holiday.BotException;
import holiday.task.*;

/**
 * Represents a command that adds a new task to the task list.
 */
public class AddCommand extends Command{

    private final String type;
    private final String description;
    private final String from;
    private final String to;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        switch (this.type) {
        case "todo": {
            Task task = new ToDos(description);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        case "deadline": {
            Task task = new Deadline(description, to);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        case "event": {
            Task task = new Event(description, from, to);
            tasks.add(task);
            ui.printAddTask(task, tasks.size());
            break;
        }
        }
    }
}
