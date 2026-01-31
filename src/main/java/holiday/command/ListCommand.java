package holiday.command;

import holiday.ui.Ui;
import holiday.task.TaskList;
import holiday.storage.Storage;
import holiday.BotException;

/**
 * Represents a command that list the task in the task list.
 */
public class ListCommand extends Command{

    /**
     * Execute the ListCommand and print out the task list.
     *
     * @param tasks Task list to be printed.
     * @param ui UI for displaying feedback.
     * @param storage
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        ui.printList();
        tasks.printList();
    }
}
