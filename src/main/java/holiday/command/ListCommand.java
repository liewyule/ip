package holiday.command;

import holiday.BotException;
import holiday.storage.Storage;
import holiday.task.TaskList;
import holiday.ui.Ui;

/**
 * Represents a command that list the task in the task list.
 */
public class ListCommand extends Command {

    /**
     * Execute the ListCommand and print out the task list.
     *
     * @param tasks Task list to be printed.
     * @param ui UI for displaying feedback.
     * @param storage Storage to save the task
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BotException {

        assert ui != null : "Ui cannot be null";

        return ui.printList(tasks);

    }
}
