package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represent a command that sort the tasklist
 */
public class SortCommand extends Command {

    private final String description;

    public SortCommand(String description) {
        this.description = description;
    }

    /**
     *  Execute the sorting follow by name or time
     * @param tasks Task list to mbe sorted.
     * @param ui UI for displaying feedback.
     * @param storage Storage to save the task
     * @return A list of task that is sorted
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        if (description.equals("name")) {
            tasks.sortByName();
            storage.saveTask(tasks);
            return ui.printList(tasks);
        } else if (description.equals("time")) {
            tasks.sortByTime();
            storage.saveTask(tasks);
            return ui.printList(tasks);
        } else {
            return ui.printNoSuchCommand();
        }
    }
}
